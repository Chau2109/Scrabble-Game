package Util.Exception;

public class TooFewPlayersException extends ProtocolException {

	public TooFewPlayersException(int amount, int min) {
		super("E011",amount + " is less than the minimum amount of players: " + min);
	}

	private static final long serialVersionUID = -6920715739515806337L;

}
