package Util.Exception;

public class NotTurnException extends ProtocolException {

	public NotTurnException() {
		super("E013","It isn't you turn yet");
	}

	private static final long serialVersionUID = 2135295342394912372L;

}
