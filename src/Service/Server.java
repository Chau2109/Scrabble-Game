
package Service;

import Controller.GameController;
import Model.Constants;
import Model.Game;
import Model.Player;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server
{
	private void getConnection() {
		GameController gameController = new GameController();
		try {
			System.out.println("Waiting for player connections on port " + Constants.SERVER_PORT);
			ServerSocket serverSock = new ServerSocket(Constants.SERVER_PORT);
			List<Player> players = new ArrayList<>();
			for (int i = 0; i < Constants.NUMBER_PLAYER; i++) {
				players.add(new Player(UUID.randomUUID().toString()));
			}
			Game game = gameController.generateGame(players);
			for (Player player: players) {
				Socket socket = serverSock.accept();
				System.out.println("Player " + player.getId() + " connected successfully.");
				Handler handler = new Handler(socket, game, player);
				Thread theThread = new Thread(handler);
				theThread.start();
			}
			gameController.startGame(game);
			System.out.println("Game running.");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.getConnection();
	}
}
