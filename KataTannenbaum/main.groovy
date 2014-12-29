/**
 * Created by ralfw on 24.12.14.
 * Coding Dojo der CCD SchooL: http://ccd-school.de/coding-dojo/
 * Kata-Beschreibung: https://app.box.com/files/0/f/885439646/1/f_15903236703
 */

class Display {
    void ausgeben(String text) {
        println text
    }
}


class Tannenbaum {
    private int _höhe;

    Tannenbaum(int höhe) {
        _höhe = höhe;
    }


    private String[] Äste_herrichten() {
        List<String> äste = []
        def breite = 1
        (1.._höhe).forEach({i ->
            def ast = "".padRight(breite, "X")
            ast = "".padRight(_höhe-i, " ") + ast
            äste.add(ast)
            breite += 2
        })

        return äste.toArray()
    }

    private String Stamm_schnitzen() {
        return "".padRight(_höhe-1, " ") + "I"
    }

    private String zusammenstecken(String[] äste, String stamm) {
        return String.join("\n", äste) + "\n$stamm"
    }


    String bauen() {
        def äste = Äste_herrichten()
        def stamm = Stamm_schnitzen()
        return zusammenstecken(äste, stamm)
    }
}


print "Anzahl der Äste (1..n): "
def astzahl = Integer.parseInt(new Scanner(System.in).nextLine())

def display = new Display()
def t = new Tannenbaum(astzahl)

def baum = t.bauen()
display.ausgeben(baum)