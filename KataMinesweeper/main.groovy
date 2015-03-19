/**
 * Created by ralfw on 30.12.14.
 */
import daten.*
import adapter.*
import domain.*


def (String spielfelddateiname, String mogelzetteldateiname) = CLIadapter.kommandozeilenparameter_lesen(args)
def spielfeldPM = Dateiadapter.lese_Spielfeld(spielfelddateiname)
def mogelzettelPM = berechne_Mogelzettel(spielfeldPM)
Dateiadapter.schreibe_Mogelzettel(mogelzetteldateiname, mogelzettelPM)

println "Spielfeld:\n${new File(spielfelddateiname).text}"
println "Mogelzettel:"
new File(mogelzetteldateiname).readLines()
                              .each {l -> println ">$l"}

def berechne_Mogelzettel(String[] spielfeldPM) {
    def mogler = new Mogler(spielfeldPM: spielfeldPM)

    def mogelzettel = new Mogelzettel()
    mogler.mogeln(
        mogelzettel.&hinzufügen)
    return mogelzettel.serialisieren()
}