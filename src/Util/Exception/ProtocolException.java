package Util.Exception;

public class ProtocolException extends Exception {

	private static final long serialVersionUID = 1354546201321958246L;
	private String id;

	public ProtocolException(String error_code, String msg) {
		super(msg);
		this.id = error_code;
	}
	
	public String getId() {
		return id;
	}
}
