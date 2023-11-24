package Service;

import Controller.BoardController;
import Model.Commands;
import Model.Constants;
import Model.LetterTile;
import View.BoardPrinter;
import View.UserInterface;

import java.io.DataOutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Listener implements Runnable {
	private Socket socket;
	private UserInterface ui;
	private BoardController boardController;
	private DataOutputStream serverOutput;

	Listener(Socket sock, DataOutputStream serverOutput) {
		this.socket = sock;
		this.ui = new BoardPrinter();
		this.boardController = new BoardController();
		this.serverOutput = serverOutput;
	}

	public void run() {
		try {
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true)
			{
				if (serverInput == null) {
					System.out.println("Closing connection for socket " + socket);
					socket.close();
					break;
				}
				String[] serverTexts = serverInput.readLine().split(">");
				String command = serverTexts[0];
				String data;
				LetterTile tiles;
				switch (command){
					case Commands.CONFIRM_CONNECTION:
						data = serverTexts[1];
						if(data.equals("true")){
							ui.connectConfirmation();
						}else{
							ui.systemMessage("Your connection was rejected!");
						}
						break;
					case Commands.PRINT_STATE:
						data = serverTexts[1];
						ui.printState(boardController.toBoard(data));
						break;
					case Commands.ASK_MOVE:
						ui.informYourTurn();
						break;
					case Commands.PRINT_SCORE:
						data = serverTexts[1];
						ui.informScore(data);
						break;
					case Commands.INPUT_ROW:
						ui.informInputRow();
						break;
					case Commands.INPUT_COL:
						ui.informInputCol();
						break;
					case Commands.INPUT_WORD:
						ui.informInputWord();
						break;
					case Commands.WAIT:
						ui.informWaiting();
						break;
					case Commands.INVALID_MOVE:
						ui.informInvalidMove();
						break;
					case Commands.FINISH_MOVE:
						data = serverTexts[1];
						ui.informFinishMove(Integer.valueOf(data));
						break;
					case Commands.WINNER:
						data = serverTexts[1];
						ui.informWinner(data);
						break;
					case Commands.WIN:
						ui.informWin();
						break;
					case Commands.LOSE:
						ui.informLose();
						break;
					case Commands.PRINT_RACK:
						data = serverTexts[1];
						ui.printRack(data);
						break;
					case Commands.PRINT_MENU:
						ui.printMenu();
						break;
					case Commands.INVALID_CHOICE:
						ui.informInvalidChoice();
						break;
					case Commands.CHANGE_TILES:
						ui.informChangeTiles(new LetterTile("A"));
						break;
					case Commands.TEN_SEC_LEFT:
						ui.informTenSecLeft();
						break;
					case Commands.TIME_UP:
						ui.informTimeUp();
						serverOutput.writeBytes("Pass idle \n");
						break;
					default:
						ui.systemMessage(command);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
