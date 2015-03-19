/**
 * Created by ralfw on 19.03.15.
 */
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.UntypedActor


public class Greeting implements Serializable {
    public final String who;
    public Greeting(String who) { this.who = who; }
}

public class GreetingActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting)
            log.info("Hello " + ((Greeting) message).who);
    }
}

ActorSystem system = ActorSystem.create("MySystem");
ActorRef greeter = system.actorOf(Props.create(GreetingActor.class), "greeter");
greeter.tell(new Greeting("Charlie Parker"), ActorRef.noSender());