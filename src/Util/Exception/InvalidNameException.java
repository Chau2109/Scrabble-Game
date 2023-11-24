package Util.Exception;

public class InvalidNameException extends ProtocolException {

	public InvalidNameException(String name) {
		super("E004",name + "is an invalid name");
	}

	private static final long serialVersionUID = 8139762022753344380L;

}
