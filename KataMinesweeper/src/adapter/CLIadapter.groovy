package adapter

/**
 * Created by ralfw on 06.01.15.
 */
class CLIadapter {
    static def kommandozeilenparameter_lesen(String[] args) {
        return [args[0], args[1]]
    }
}
