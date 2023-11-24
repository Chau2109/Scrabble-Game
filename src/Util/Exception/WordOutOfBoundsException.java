package Util.Exception;

public class WordOutOfBoundsException extends ProtocolException {

	public WordOutOfBoundsException() {
		super("E014","Your move is exceeding the board boundaries");
	}

	private static final long serialVersionUID = 84606001593685234L;

}
