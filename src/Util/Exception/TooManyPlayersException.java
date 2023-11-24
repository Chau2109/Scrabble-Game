package Util.Exception;

public class TooManyPlayersException extends ProtocolException {

	public TooManyPlayersException(int amount, int max) {
		super("E010",amount + " is more than the maximum amount of players: " + max);
	}

	private static final long serialVersionUID = -8878960125315097073L;

}
