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
    Mogler.zellen_klassifizieren(spielfeldPM,
            {Spielfeldzelle minenzelle ->
                def mogelzelle = Mogler.behandle_Mine(minenzelle)
                mogelzettel.hinzufügen(mogelzelle)
            },
            {Spielfeldzelle leereZelle ->
                def mogelzelle = Mogler.ermittle_Anzahl_umliegender_Minen(leereZelle)
                mogelzettel.hinzufügen(mogelzelle)
            }
    )
    return mogelzettel.serialisieren()
}