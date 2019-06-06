package bg.infa.pbt.dto;

public class ApplicationExceptionDto {
	
	private String error = null;
	
	private String message = null;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}