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


def berechne_Mogelzettel(String[] spielfeldPM) {
    def mogelzettel = new Mogelzettel()
    Mogler.mogeln(spielfeldPM,
        mogelzettel.&hinzufügen)
    return mogelzettel.serialisieren()
}