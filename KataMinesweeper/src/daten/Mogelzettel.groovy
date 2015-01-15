package daten

import javax.swing.text.html.Option

/**
 * Created by ralfw on 06.01.15.
 */
class Mogelzelle {
    Spielfeldkoordinate koordinate
}

class Minenmogelzelle extends Mogelzelle {
    String mogelwert() {"*"}
}

class Hinweismogelzelle extends Mogelzelle {
    int anzahlUmliegenderMinen

    String mogelwert() {anzahlUmliegenderMinen}
}


class Mogelzettel {
    private List<Mogelzelle> mogelzellen = []

    void hinzufügen(Mogelzelle zelle) {
        this.mogelzellen.add(zelle)
    }

    List<String> serialisieren() {
        mogelzellen.sort {z -> z.koordinate.y * 100 + z.koordinate.x}
        def grouped = mogelzellen.groupBy {it.koordinate.y}
        return grouped.collect {g -> g.value.inject("", {s, z -> s += z.mogelwert()})}
    }
}
