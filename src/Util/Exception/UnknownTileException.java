package Util.Exception;

public class UnknownTileException extends ProtocolException {

	public UnknownTileException(String tile) {
		super("E015","Tile \"" + tile + "\" is unknown");
	}

	private static final long serialVersionUID = -8020561333174075025L;

}
