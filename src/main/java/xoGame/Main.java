package xoGame;

import java.util.Scanner;

import static xoGame.GameStateDecorator.decorator;

public class Main {

    public static void main(String[] args) {
        new XOGame(
                decorator(new InitialState(), new MyExceptionHandler()::handle),
                new Scanner(System.in)::nextLine,
                System.out::println)
                .start();
    }
}
