package xoGame;

import java.util.Scanner;
import java.util.function.Supplier;

import static xoGame.GameStateDecorator.decorator;

public class Main {

    public static void main(String[] args) {
//        Supplier<String> userInputProvider=new Scanner(System.in)::nextLine;
        new XOGame(
                decorator(new InitialState(), new MyExceptionHandler()::handle),
                new Scanner(System.in)::nextLine,
                System.out::println)
                .start();
    }
}
