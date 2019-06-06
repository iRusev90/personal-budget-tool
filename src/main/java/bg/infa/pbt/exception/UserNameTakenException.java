package bg.infa.pbt.exception;

public class UserNameTakenException extends ApplicationException {

	private static final long serialVersionUID = -6215602166624581079L;

	public UserNameTakenException(String username) {
		super(String.format("Username %s is already taken.", username));
	}
	
}
