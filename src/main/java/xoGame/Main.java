package xoGame;

import xoGame.gameStates.InitialState;
import xoGame.xoGameExceptions.MyExceptionHandler;

import java.util.Scanner;

import static xoGame.gameStates.GameStateDecorator.decorator;

public class Main {

    public static void main(String[] args) {
        new XOGame(
                decorator(new InitialState(), new MyExceptionHandler()::handle),
                new Scanner(System.in)::nextLine,
                System.out::println)
                .start();
    }
}
