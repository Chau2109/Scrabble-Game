package Controller;

import Model.Alignment;
import Model.Board;
import Model.Constants;
import Model.Game;
import Model.LetterTile;
import Model.Player;
import Model.PlayerInput;
import Model.Square;
import Model.SquareType;
import Util.Exception.CantPlaceWordHereException;
import Util.Exception.NotOwnedTileException;
import Util.Exception.UnknownTileException;
import Util.Exception.WrongCoordinateException;
import Util.Library.WordChecker;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameController {
    private LetterBagController letterBagController;
    private BoardController boardController;
    private ArrayList<LetterTile> tileBag = new ArrayList<LetterTile>();
    private ArrayList<Player> players = new ArrayList<Player>();

    /**
     * Create controllers
     */

    public GameController() {
        letterBagController = new LetterBagController();
        boardController = new BoardController();
    }

    /**
     * Generates a game and distribute the tiles after generating game
     * @requrires List<Player>.size() >= 2
     * @param players list of players
     * @return game object
     *
     */

    public Game generateGame(List<Player> players) {
        Game game = new Game(players);
        game.setBoard(boardController.generateGameBoard(Constants.BOARD_SIZE));
        game.setBag(letterBagController.generateLetterBag());
        return game;
    }
    /**
     *
     * @param game specific game
     * @return true if the game is finished, false otherwise
     */
    public boolean isGameOver(Game game) {
            if (game.getCurrentPlayer().getRack().size() == 0)
                letterBagController.fullFillRackForPlayer(game.getBag(), game.getCurrentPlayer());
            if (letterBagController.isOutOfTiles(game.getBag()) && game.getCurrentPlayer().getRack().size() == 0) {
                for (int i = 0; i < players.size(); i++)
                    if (players.get(i) != game.getCurrentPlayer()) {
                        int total = 0;
                        for (int j = 0; j < players.get(i).getRack().size(); j++)
                            total += Integer.parseInt(players.get(i).getRack().get(j).getValue());
                        players.get(i).setScore(players.get(i).getScore() - total);
                        game.getCurrentPlayer().setScore(game.getCurrentPlayer().getScore() + total);
                    }
                return true;
            }
        return false;
        }



    /**
     * submits the move of particular player
     * @param game particular game
     * @param input user input
     * @return calculated score
     * @throws CantPlaceWordHereException
     * @throws WrongCoordinateException
     * @throws NotOwnedTileException
     */
    public int submitMove(Game game, PlayerInput input) throws CantPlaceWordHereException, WrongCoordinateException, NotOwnedTileException, UnknownTileException {
        int score = 0;
        int countScoreTile = 0;
        WordChecker check = new WordChecker();
        if(boardController.isEmptyBoard(game.getBoard()) && input.getCoordinate().getRow() != 7 && input.getCoordinate().getCol() != 7)
            throw new CantPlaceWordHereException();
        Board clonedBoard = boardController.clone(game.getBoard());
        if(check.isValidWord(input.getWord()) != null){
            int curRow = input.getCoordinate().getRow();
            int curCol = input.getCoordinate().getCol();
            boolean isDouble = false;
            boolean isTriple = false;
            List<LetterTile> cloneRack = new ArrayList<>(game.getCurrentPlayer().getRack());
            for (String c: input.getWord().split("")) {
                LetterTile tile = new LetterTile(c);
                Square square = game.getBoard().getSquare(curRow, curCol);
                if(square.getTile() != null){
                    if(square.getTile().getValue().equals(tile.getValue())){
                        score += square.getTile().getScore();
                    }else{
                        throw new UnknownTileException(tile.getValue());
                    }
                }else{
                    LetterTile tileInRack =
                            cloneRack.stream().filter(t -> t.getValue().equals(tile.getValue())).findFirst().orElseThrow(() -> new NotOwnedTileException(tile.getValue()));
                    switch (square.getType()){
                        case DOUBLE_WORD:
                        case CENTER_SQUARE:
                            isDouble = true;
                            score += tile.getScore();
                            break;
                        case TRIPLE_WORD:
                            isTriple = true;
                            score += tile.getScore();
                            break;
                        case TRIPLE_LETTER:
                            score += tile.getScore()*3;
                            break;
                        case DOUBLE_LETTER:
                            score += tile.getScore()*2;
                            break;
                        default:
                            score += tile.getScore();
                    }
                    cloneRack.remove(tileInRack);
                    clonedBoard.getSquares()[curRow][curCol].setTile(tileInRack);
                    countScoreTile++;
                }
                if(input.getAlignment() == Alignment.HORIZONTAL){
                    curCol++;
                }else{
                    curRow++;
                }
            }
            if(isDouble){
                score*=2;
            }
            if(isTriple){
                score*=3;
            }
            if(countScoreTile == Constants.LIMIT_TILE_PER_ROUND){
                // BINGO
                score = 50;
            }
            game.getCurrentPlayer().setRack(cloneRack);
            game.setBoard(clonedBoard);
        }
        return score;
    }

    /**
     * Check the winner by comparing score
     * @param game specific game
     * @return winner - player with the highest score
     */

    public Player getWinner(Game game) {
        Player winner = game.getPlayers().get(0);
        for (Player player: game.getPlayers()) {
            if(player.getScore() > winner.getScore()){
                winner = player;
            }else if(player.getScore() == winner.getScore()){
                int playerRackScore = getRackScore(player.getRack());
                int winnerRackScore = getRackScore(winner.getRack());
                if(playerRackScore < winnerRackScore){
                    winner = player;
                }
            }
        }
        return winner;
    }

    /**
     *
     * @param rack player rack
     * @return calculated score of all tiles
     */

    private int getRackScore(List<LetterTile> rack) {
        int score = 0;
        for (LetterTile tile: rack) {
            score += tile.getScore();
        }
        return score;
    }

    /**
     * Switch the turn of the player
     * @param game specific game
     * @ensures currentPlayer = game.setCurrentPlayer(game.getPlayers().get(nextPlayerIndex));
     *
     */

    public void switchPlayer(Game game){
        Player currentPlayer = game.getCurrentPlayer();
        for (int i = 0; i < Constants.NUMBER_PLAYER; i++) {
            if(currentPlayer.getId() == game.getPlayers().get(i).getId()){
                int nextPlayerIndex = i == Constants.NUMBER_PLAYER - 1 ? 0 : i+1;
                game.setCurrentPlayer(game.getPlayers().get(nextPlayerIndex));
            }
        }
    }

    public boolean isGameStarted(Game game){
        return game.isStarted();
    }

    public void startGame(Game game) {
        game.startGame();
    }
}
