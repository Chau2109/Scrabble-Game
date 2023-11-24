package View;

import Model.Board;
import Model.LetterTile;

public interface UserInterface {
    void printState(Board board);
    void informInputRow();
    void informInputCol();
    void informInputDir();
    void informInputWord();
    void informWaiting();
    void informYourTurn();
    void informInvalidMove();
    void informFinishMove(int scoreEarned);
    void informWinner(String playerId);
    void informWin();
    void informLose();
    void printRack(String rackData);
    void printMenu();
    void informInvalidChoice();
    void informChangeTiles(LetterTile tiles);
    void informTenSecLeft();
    void informTimeUp();
    void systemMessage(String command);
    void connectConfirmation();
    void informScore(String data);
}
