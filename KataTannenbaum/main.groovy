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


class Tannenbaumfabrik {
    private String[] Äste_bauen(int höhe) {
        List<String> äste = []
        def breite = 1
        for(int i=1; i<=höhe; i++) {
            def ast = "".padRight(breite, "X")
            ast = "".padRight(höhe-i, " ") + ast
            äste.add(ast)
            breite += 2
        }
        return äste.toArray()
    }

    private String Stamm_bauen(int höhe) {
        return "".padRight(höhe-1, " ") + "I"
    }


    String bauen(int höhe) {
        def äste = Äste_bauen(höhe)
        def stamm = Stamm_bauen(höhe)
        return String.join("\n", äste) + "\n$stamm"
    }
}


def display = new Display()
def t = new Tannenbaumfabrik()

def baum = t.bauen(5)
display.ausgeben(baum)