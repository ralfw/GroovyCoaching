package adapter

/**
 * Created by ralfw on 06.01.15.
 */
class Dateiadapter {
    static void schreibe_Mogelzettel(String dateiname, List<String> mogelzettel) {
        def f = new File(dateiname)
        f.withWriter { w -> mogelzettel.each { l -> w << "$l\n" } }
    }

    static String[] lese_Spielfeld(String dateiname) {
        def f = new File(dateiname)
        return f.readLines().toArray()
    }
}
