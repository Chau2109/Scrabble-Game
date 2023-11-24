package Util.Exception;

public class NotOwnedTileException extends ProtocolException {

	public NotOwnedTileException(String tile) {
		super("E012","You are not owner of all this tile: " + tile);
	}

	private static final long serialVersionUID = 7617379268202609185L;

}
