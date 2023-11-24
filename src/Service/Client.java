package Service;

import Model.Commands;
import Model.Constants;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket;
			DataOutputStream serverOutput = null;
			Scanner keyboard = new Scanner(System.in);
			Listener listener;
			Thread theThread;
			printMenu();
			while (true){
				String input = keyboard.nextLine();
				String[] data = input.split(" ");
				switch (data[0]){
					case Commands.CONNECT:
						socket = new Socket(Constants.HOST_NAME, Constants.SERVER_PORT);
						serverOutput = new DataOutputStream(socket.getOutputStream());
						listener = new Listener(socket, serverOutput);
						theThread = new Thread(listener);
						theThread.start();
						serverOutput.writeBytes(data[1] + "\n");
						break;
					case Commands.DISCONNECT:
						if(serverOutput != null){
							serverOutput.close();
							serverOutput = null;
						}else{
							System.out.println("You haven't connect to server yet!");
						}
						break;
					case Commands.MAKE_MOVE:
					case Commands.SKIP_TURN:
					case Commands.UPDATE_TABLE:
					case Commands.UPDATE_SCORE:
					case Commands.REPLACE_TILES:
					case Commands.GIVE_TILES:
						if(serverOutput != null){
							serverOutput.writeBytes(input + "\n");
						}else{
							System.out.println("You haven't connect to server yet!");
						}
						break;
					default:
						System.out.println("Invalid choice!");
						printMenu();
						break;
				}
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printMenu() {
		System.out.println("==========================");
		System.out.println("C  : Connect");
		System.out.println("D  : Confirm connection");
		System.out.println("M  : Make move");
		System.out.println("S  : Skip turn");
		System.out.println("UT : Update table");
		System.out.println("US : Update score");
		System.out.println("R  : Replace tiles");
		System.out.println("GT : Give tiles");
		System.out.println("==========================");
		System.out.println("Enter your choice!");
	}
}
