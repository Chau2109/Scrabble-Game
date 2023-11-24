package Util.Interfaces;

import Util.Exception.*;
import Util.Protocol.*;

public interface IClientHandler {
	
	/**
	 * Called when a new room creation request is done
	 * @param player_number
	 * @throws NumberFormatException
	 * @throws TooManyPlayersException
	 * @throws TooFewPlayersException
	 * @ensures it calls HandleJoinRoom
	 */
/**/public void handleCreateRoom(String player_number) throws NumberFormatException, TooManyPlayersException, TooFewPlayersException;
	
	/**
	 * Called when new connection is established
	 * @param name
	 * @throws InvalidNameException
	 */
/**/public void handleConnect(String name) throws InvalidNameException;
	
	/**
	 * Called when a player request to join a room
	 * @param room_id
	 * @throws RoomFullException
	 * @throws UnknownRoomException
	 * @send an initialization of the room (table, players that are already in the game);
	 */
	public void handleJoinRoom(String room_id) throws RoomFullException, UnknownRoomException;
	
	/**
	 * Called when a player tries a move
	 * @param alignment
	 * @param coordinates
	 * @param word
	 * @throws NotOwnedTileException
	 * @throws NotTurnException
	 * @throws WordOutOfBoundsException
	 * @throws UnknownTileException
	 * @throws WrongCoordinateException
	 * @throws CantPlaceWordHereException
	 * @send send new tiles to the user
	 * @ensures it calls updateScore in {@link ServerProtocol}
	 */
	public void handleMove(String alignment, String coordinates, String word) throws NotOwnedTileException, NotTurnException, WordOutOfBoundsException, UnknownTileException, WrongCoordinateException, CantPlaceWordHereException;

	/**
	 * Called when a player want to skip his turn
	 */
	public void handleSkip() throws NotTurnException;
	
	/**
	 * Called when a player want to replace some tiles
	 * @param tiles - tiles to be replaced
	 * @throws NotOwnedTileException
	 * @send send new tiles to the user
	 */
	public void handleReplaceTiles(String tiles) throws NotOwnedTileException;
	
}
