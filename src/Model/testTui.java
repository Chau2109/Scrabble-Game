package Model;

import Controller.BoardController;
import View.BoardPrinter;

public class testTui {
    public static void main(String[] args) {
        System.out.println(BoardPrinter.generateBoard(BoardController.generateGameBoard(15)));
    }
}
