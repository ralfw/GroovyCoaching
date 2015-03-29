/**
 * Created by ralfw on 26.03.15.
 */

Erste_Seite_aufblättern()
Kommando_erwarten(
        this.&Erste_Seite_aufblättern,
        this.&Letzte_Seite_aufblättern
)

def Kommando_erwarten(beiF, beiL) {
    while(true) {
        def cmd = System.console().readLine()
        if (cmd == "f")
            beiF()
        else if (cmd == "l")
            beiL()
        else if (cmd == "x")
            break;
    }
}


def Erste_Seite_aufblättern() {
    println("Erste Seite!")
    print("F, L, N, P, X: ")
}

def Letzte_Seite_aufblättern() {
    println("Letzte Seite!")
    print("F, L, N, P, X: ")
}