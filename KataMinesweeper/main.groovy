/**
 * Created by ralfw on 30.12.14.
 */
void Schreibe_Mogelzettel(String dateiname, List<String> mogelzettel) {
    def f = new File(dateiname)
    f.withWriter {w -> mogelzettel.each {l -> w << "$l\n"}}
}

def Lese_Spielfeld(dateiname) {
    def f = new File(dateiname)
    return f.readLines()
}

def Kommandozeilenparameter_parsen(String[] args) {
    return ["spielfelddateiname":args[0], "mogelzetteldateiname":args[1]]
}


def Berechne_Mogelzettel(spielfeld) {
    return ["0"].plus(spielfeld)
}


def dateinamen = Kommandozeilenparameter_parsen(args)
def spielfeld = Lese_Spielfeld(dateinamen["spielfelddateiname"])
def mogelzettel = Berechne_Mogelzettel(spielfeld)
Schreibe_Mogelzettel(dateinamen["mogelzetteldateiname"], mogelzettel)

