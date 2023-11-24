package Util.Exception;

public class RoomFullException extends ProtocolException {

	public RoomFullException(int room) {
		super("E003","Room \"" + room + "\" is full");
	}

	private static final long serialVersionUID = -8445168959281065260L;
}
