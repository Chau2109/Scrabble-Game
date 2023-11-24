package Util.Interfaces;

public interface IServerHandler {

	/**
	 * Called when the server sends tiles for replacement or at game initialization
	 * @param letters
	 */
	public void handleGiveTile(String letters);
	
	/**
	 * called when a player score needs to be updated
	 * @param identifier
	 * @param score
	 */
	public void handleUpdateScore(String identifier, String score);
	
	/**
	 * called when the server sends a feedback
	 * @param feedback
	 */
	public void handleFeedback(String feedback);
	
	/**
	 * Called when a player is added to the current room
	 * @param identifier
	 */
	public void handlePlayerJoin(String identifier);
	
	/**
	 * called when the board initialization is sent
	 * @param board
	 * @param players
	 */
	public void handleInitiategame(String board, String players);
	
	/**
	 * called when a table update is sent
	 * @param table
	 */
	public void handleUpdateTable(String table);
	
	/**
	 * called at the end of the game
	 * @param player
	 * @param score
	 */
	public void handleFinishGame(String player, String score);
}
