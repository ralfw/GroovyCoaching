/**
 * Created by ralfw on 08.03.15.
 *
 * using: http://jline.sourceforge.net
 */


import jline.console.ConsoleReader

print "char: "

def ch = (char)new ConsoleReader().readCharacter()

println "typed: $ch"

