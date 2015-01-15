package domain

import daten.*

/**
 * Created by ralfw on 06.01.15.
 */
class Mogler {
    static void mogeln(String[] spielfeldPM, beiMogelzelle) {
        zellen_klassifizieren(spielfeldPM,
            {Spielfeldkoordinate minenzelle ->
                def mogelzelle = behandle_Mine(minenzelle)
                beiMogelzelle(mogelzelle)
            },
            {Spielfeldkoordinate leereZelle ->
                def mogelzelle = ermittle_Anzahl_umliegender_Minen(leereZelle)
                beiMogelzelle(mogelzelle)
            })
    }

    private static void zellen_klassifizieren(String[] spielfeldPM, beiMinenzelle, beiLeererZelle) {
        def y = 0
        spielfeldPM.each {zeile ->
            def x = 0
            zeile.chars.each {feld ->
                if (feld == "*")
                    beiMinenzelle(new Spielfeldkoordinate(x:x, y:y))
                else
                    beiLeererZelle(new Spielfeldkoordinate(x:x, y:y))
                x++
            }
            y++
        }
    }

    private static Mogelzelle behandle_Mine(Spielfeldkoordinate koord) {
        new Minenmogelzelle(koordinate:koord)
    }

    private static Mogelzelle ermittle_Anzahl_umliegender_Minen(Spielfeldkoordinate koord) {
        new Hinweismogelzelle(koordinate:koord, anzahlUmliegenderMinen:2)
    }
}
