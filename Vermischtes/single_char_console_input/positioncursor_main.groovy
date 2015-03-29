/**
 * Created by ralfw on 19.03.15.
 */
import jline.*
import jline.console.ConsoleReader

AnsiWindowsTerminal t = new AnsiWindowsTerminal();
t.init();
def r = new ConsoleReader(System.in, System.out, t);

while (true) {
    r.println("Hello World!");
    r.flush();

    char input = (char) r.readCharacter();

    if ('c' == input)
        r.clearScreen();
    else if ('e' == input)
        return;
    else
        System.out.println("You typed '" + input + "'.");

}