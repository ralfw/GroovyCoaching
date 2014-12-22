import klassen.*

/**
 * Created by ralfw on 22.12.14.
*/

print "name: "
def name = System.console().readLine()

def p = new Person2()
p.name = name

def greeting = "hello, $p.name ${'<' + name + '>'}"

println greeting
println args[0]