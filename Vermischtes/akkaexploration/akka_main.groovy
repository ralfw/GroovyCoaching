// @Grab( 'com.typesafe.akka:akka-actor_2.10:2.3.2' )
// @Grab( 'com.typesafe:config:1.2.0' )
import groovy.transform.Immutable
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.UntypedActor
import akka.actor.UntypedActorFactory
import akka.routing.RoundRobinRouter
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import com.typesafe.config.ConfigFactory

// Message classes
@Immutable                               class Calculate {}
@Immutable class Work { int start, nrOfElements }
@Immutable                               class Result { double value }
@Immutable(knownImmutables=['duration']) class PiApproximation { double pi ; Duration duration }

// Main worker
class Worker extends UntypedActor {

    private double calculatePiFor( int start, int nrOfElements ) {
        ((start * nrOfElements)..((start + 1) * nrOfElements - 1)).inject( 0.0 ) { acc, i ->
            acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
        }
    }

    void onReceive( message ) {
        switch( message ) {
            case Work:
                sender.tell( new Result( calculatePiFor( message.start, message.nrOfElements ) ), self )
                break
            default:
                unhandled( message )
        }
    }
}

class Master extends UntypedActor {
    private final int nrOfMessages
    private final int nrOfElements

    private double pi
    private int nrOfResults
    private final long start = System.currentTimeMillis()

    private final ActorRef listener
    private final ActorRef workerRouter

    public Master( int nrOfWorkers, int nrOfMessages, int nrOfElements, ActorRef listener ) {
        this.nrOfMessages = nrOfMessages
        this.nrOfElements = nrOfElements
        this.listener = listener
        workerRouter = context.actorOf( Props.create( Worker ).withRouter( new RoundRobinRouter( nrOfWorkers ) ), "workerRouter" )
    }

    void onReceive( message ) {
        switch( message ) {
            case Calculate:
                nrOfMessages.times { workerRouter.tell( new Work( it, nrOfElements ), self ) }
                break
            case Result:
                pi += message.value
                if( ++nrOfResults >= nrOfMessages ) {
                    listener.tell new PiApproximation( pi, Duration.create( System.currentTimeMillis() - start, TimeUnit.MILLISECONDS ) ), self
                    context.stop( self )
                }
            default:
                unhandled( message )
        }
    }
}

class Listener extends UntypedActor {
    void onReceive( message ) {
        switch( message ) {
            case PiApproximation:
                println "Pi approx $message.pi in $message.duration"
                context.system().shutdown()
                break
            default:
                unhandled( message )
        }
    }
}

def cl = this.class.classLoader
ActorSystem.create( 'PiSystem', ConfigFactory.load( cl ), cl ).with { system ->
    system.actorOf( Props.create( Listener ), 'listener' ).with { listener ->
        system.actorOf(
                Props.create(
                        [ create:{ -> new Master( 4, 1000, 1000, listener ) } ] as UntypedActorFactory
                ), 'master' ).with { master ->
            master.tell new Calculate(), listener
        }
    }
    system.awaitTermination()
}