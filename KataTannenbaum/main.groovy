/**
 * Created by ralfw on 24.12.14.
 * Coding Dojo der CCD SchooL: http://ccd-school.de/coding-dojo/
 * Kata-Beschreibung: https://app.box.com/files/0/f/885439646/1/f_15903236703
 */

def Äste_zeichnen(int höhe) {
    def breite = 1
    for(int i=1; i<=höhe; i++) {
        def ast = "".padRight(breite, "X")
        ast = "".padRight(höhe-i, " ") + ast
        println ast
        breite += 2
    }
    return breite
}

def Stamm_zeichnen(int breite) {
    def stamm = "".padRight(breite/2-1, " ") + "I"
    println stamm
}

def Tannenbaum_zeichnen(int höhe) {
    def breite = Äste_zeichnen(höhe)
    Stamm_zeichnen(breite)
}

Tannenbaum_zeichnen(5)

