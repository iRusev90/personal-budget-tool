package bg.infa.pbt.dto;

import java.util.LinkedList;
import java.util.List;

public class ValidationErrorDto {
	
	private List<FieldErrorDto> errors = new LinkedList<>();

	public void addError(String field, String error) {
		errors.add(new FieldErrorDto(field, error));
	}

	public List<FieldErrorDto> getErrors() {
		return errors;
	}

	private static final class FieldErrorDto {
		private String field;
		private String error;

		public FieldErrorDto(String field, String error) {
			this.field = field;
			this.error = error;
		}

		@SuppressWarnings("unused")
		public String getField() {
			return field;
		}

		@SuppressWarnings("unused")
		public String getError() {
			return error;
		}
	}
}