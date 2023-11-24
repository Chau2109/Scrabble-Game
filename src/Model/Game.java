package Model;

import java.util.List;

public class Game {
    private Board board;
    private LetterBag bag;
    private List<Player> players;
    private Player currentPlayer;
    private boolean started;

    public Game(List<Player> players) {
        this.players = players;
        if(!players.isEmpty()){
            currentPlayer = players.get(0);
        }
        this.started = false;
    }

    public Board getBoard() {
        return board;
    }

    public LetterBag getBag() {
        return bag;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setBag(LetterBag bag) {
        this.bag = bag;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isStarted(){
        return this.started;
    }
    public void startGame(){
        this.started = true;
    }
}
