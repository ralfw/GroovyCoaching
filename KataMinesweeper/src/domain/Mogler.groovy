package domain

import daten.*

/**
 * Created by ralfw on 06.01.15.
 */
class Mogler {
    private String[] spielfeldPM;

    void mogeln(beiMogelzelle) {
        zellen_klassifizieren(
            {Spielfeldkoordinate minenzelle ->
                def mogelzelle = behandle_Mine(minenzelle)
                beiMogelzelle(mogelzelle)
            },
            {Spielfeldkoordinate leereZelle ->
                def mogelzelle = ermittle_Anzahl_umliegender_Minen(leereZelle)
                beiMogelzelle(mogelzelle)
            })
    }

    private void zellen_klassifizieren(beiMinenzelle, beiLeererZelle) {
        def y = 0
        this.spielfeldPM.each {zeile ->
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

    private Mogelzelle ermittle_Anzahl_umliegender_Minen(Spielfeldkoordinate koord) {
        def anzahlMinen = 0

        [-1,0,1].each {dy ->
            [-1,0,1].each {dx ->
                if (dy == 0 && dx == 0) return

                def y = koord.y + dy
                def x = koord.x + dx
                if (y < 0 || x < 0) return
                if (y >= this.spielfeldPM.size() || x >= this.spielfeldPM.first().size()) return

                if (this.spielfeldPM[y][x] == "*") anzahlMinen++
            }
        }

        new Hinweismogelzelle(koordinate:koord, anzahlUmliegenderMinen:anzahlMinen)
    }
}
