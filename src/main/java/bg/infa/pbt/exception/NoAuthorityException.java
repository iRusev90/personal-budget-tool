package bg.infa.pbt.exception;

public class NoAuthorityException extends ApplicationException {
	private static final long serialVersionUID = -209086299000931382L;
	
	public NoAuthorityException(String message) {
		super(message);
	}
	
	public NoAuthorityException(String username, String role) {
		super(String.format("User %s does not have role %s.", username, role));
	}
}


