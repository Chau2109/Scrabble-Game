
package Service;

import Controller.BoardController;
import Controller.GameController;
import Controller.LetterBagController;
import Model.Alignment;
import Model.Commands;
import Model.Constants;
import Model.Game;
import Model.Location;
import Model.Player;
import Model.PlayerInput;
import Util.Exception.CantPlaceWordHereException;
import Util.Exception.NotOwnedTileException;
import Util.Exception.UnknownTileException;
import Util.Exception.WrongCoordinateException;

import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Handler implements Runnable {

  private Socket socket;
  private Game game;
  private Player player;
  private GameController gameController;
  private BoardController boardController;
  private LetterBagController letterBagController;

  public Handler(Socket socket, Game game, Player player) {
    this.socket = socket;
    this.game = game;
    this.player = player;
    this.gameController = new GameController();
    this.boardController = new BoardController();
    this.letterBagController = new LetterBagController();
  }

  public void run() {
    try {
      BufferedReader playerInput = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      player.setName(playerInput.readLine());
      sendMessage(Commands.CONFIRM_CONNECTION + ">" + true);
      while (!this.gameController.isGameStarted(game)){
        Thread.sleep(500);
      }
      while (!this.gameController.isGameOver(game)) {
        if(isMyTurn()) {
          letterBagController.fullFillRackForPlayer(game.getBag(), game.getCurrentPlayer());
          sendMessage(Commands.ASK_MOVE);
          boolean[] isTimeUp = {false};
          Timer timer = new Timer();
          timer.scheduleAtFixedRate(new TimerTask() {
            int i = Constants.TIME_BOX;
            public void run() {
              i--;
              if(i == 10) {
                sendMessage(Commands.TEN_SEC_LEFT);
              }
              if (i < 0) {
                timer.cancel();
                sendMessage(Commands.TIME_UP);
                isTimeUp[0] = true;
                gameController.switchPlayer(game);
              }
            }
          }, 0, 1000);
          while(true){
            String request = playerInput.readLine().trim();
            String[] data = request.split(" ");
            String command = data[0];
            if(isTimeUp[0]){
              break;
            }
            if(command.equals(Commands.MAKE_MOVE)){
              try {
                PlayerInput input = convertToInputFromRequestData(data);
                int score = gameController.submitMove(game, input);
                player.setScore(player.getScore()+score);
                sendMessage(Commands.FINISH_MOVE + ">" + score);
              }catch (CantPlaceWordHereException | WrongCoordinateException | NotOwnedTileException | UnknownTileException e) {
                sendMessage(e.getMessage());
              }
              this.gameController.switchPlayer(game);
              break;
            }else if(command.equals(Commands.SKIP_TURN)){
              this.gameController.switchPlayer(game);
              break;
            }else if(command.equals(Commands.UPDATE_TABLE)){
              sendMessage(Commands.PRINT_STATE + ">" + boardController.toString(game.getBoard()));
            }else if(command.equals(Commands.UPDATE_SCORE)){
              sendMessage(Commands.PRINT_SCORE + ">" + this.player.getScore());
            }else if(command.equals(Commands.REPLACE_TILES)){
              List<String> changeTiles = convertToTilesFromRequestData(data);
              try {
                letterBagController.changeTiles(game.getBag(), player, changeTiles);
                this.gameController.switchPlayer(game);
                break;
              }catch (NotOwnedTileException e){
                sendMessage(e.getMessage());
              }
            }else if(command.equals(Commands.GIVE_TILES)){
              sendMessage(Commands.PRINT_RACK + ">" + game.getCurrentPlayer().getBRack());
            }
          }
          timer.cancel();
        }else{
          sendMessage(Commands.WAIT + ">" + this.game.getCurrentPlayer().getId());
          while (!isMyTurn()) {
            Thread.sleep(500);
          }
        }
      }
      Player winner = gameController.getWinner(game);
      sendMessage(Commands.FINISH_GAME + ">" + winner.getName() + ">" + winner.getScore());
    } catch (IOException | InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }

  private PlayerInput convertToInputFromRequestData(String[] data) throws WrongCoordinateException {
    Alignment alignment = Alignment.fromValue(data[1]);
    Location coordinate;
    int row = Integer.parseInt(String.valueOf(data[2].charAt(1))) - 1;
    int col = (int) data[2].charAt(0) - 65;
    if(col < 0 || col >= Constants.BOARD_SIZE || row < 0 || row >= Constants.BOARD_SIZE)
      throw new WrongCoordinateException(data[2]);
    else
      coordinate = new Location(row, col);
    String word = data[3];
    return new PlayerInput(alignment, coordinate, word);
  }

  private List<String> convertToTilesFromRequestData(String[] data) {
    List<String> changeTiles = new ArrayList<>();
    for (int i = 1; i < data.length; i++) {
      changeTiles.add(data[i]);
    }
    return changeTiles;
  }

  private void sendMessage(String message) {
    try {
      DataOutputStream clientOutput = new DataOutputStream(this.socket.getOutputStream());
      clientOutput.writeBytes(message + "\r\n");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private boolean isMyTurn(){
    return this.player.getId() == game.getCurrentPlayer().getId();
  }
}

