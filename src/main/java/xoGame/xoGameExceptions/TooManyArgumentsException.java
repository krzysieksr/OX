package xoGame.xoGameExceptions;

public class TooManyArgumentsException extends Exception {
    String arguments;

    public TooManyArgumentsException(String arguments) {
        this.arguments = arguments;
    }

    public String getArguments() {
        return arguments;
    }
}
