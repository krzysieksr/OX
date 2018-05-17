package xoGame.xoGameExceptions;

public class MyExceptionHandler {

    public void handle(Exception e) {
        System.out.println(e.getClass().getName());
    }
}
