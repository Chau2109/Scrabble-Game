package Util.Exception;

public class WrongCoordinateException extends ProtocolException {

	public WrongCoordinateException(String coordinate) {
		super("E005","Cordinate \"" + coordinate + "\" doesn't fit or match with anything");
	}

	private static final long serialVersionUID = 6988900144002756968L;

}
