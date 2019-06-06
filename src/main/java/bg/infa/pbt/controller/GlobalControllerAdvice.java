package bg.infa.pbt.controller;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import bg.infa.pbt.dto.ApplicationExceptionDto;
import bg.infa.pbt.dto.ValidationErrorDto;
import bg.infa.pbt.exception.ApplicationException;
import bg.infa.pbt.exception.NoAuthorityException;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalControllerAdvice.class);
	private static final String NO_MESSAGE = "_NO_MESSAGE_";
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		ValidationErrorDto dto = createValidationErrorDto(bindingResult, request.getLocale());
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, dto, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		ValidationErrorDto dto = createValidationErrorDto(bindingResult, request.getLocale());
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, dto, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		logger.debug("", ex);
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private ValidationErrorDto createValidationErrorDto(BindingResult bindingResult, Locale locale) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		List<ObjectError> globalErrors = bindingResult.getGlobalErrors();
		ValidationErrorDto dto = new ValidationErrorDto();
		for (FieldError fieldError : fieldErrors) {
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			if (fieldError.isBindingFailure()) {
				message = NO_MESSAGE;
			}
			dto.addError(field, message);
		}
		for (ObjectError globalError : globalErrors) {
			dto.addError(globalError.getObjectName(), globalError.getDefaultMessage());
		}
		return dto;
	}
	
	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ApplicationExceptionDto handleApplicationException(ApplicationException ex, HttpServletRequest request) {
		return convertException(ex, request);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ApplicationExceptionDto handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
		setResponseTypeToJson(request);
		ApplicationExceptionDto dto = new ApplicationExceptionDto();
		dto.setError("AccessDeniedException");
		return dto;
	}
	

	@ExceptionHandler(NoAuthorityException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ApplicationExceptionDto handleNoAuthorityException(NoAuthorityException ex, HttpServletRequest request) {
		setResponseTypeToJson(request);
		ApplicationExceptionDto dto = new ApplicationExceptionDto();
		dto.setError(NoAuthorityException.class.getSimpleName());
		dto.setMessage(ex.getMessage());
		return dto;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApplicationExceptionDto handleUnhandledExceptions(Exception ex, HttpServletRequest request) {
		if (!ex.getClass().getCanonicalName().equals("org.apache.catalina.connector.ClientAbortException")) {
			logger.error("Caught unhandled exception: ", ex);
		}
		setResponseTypeToJson(request);
		ApplicationExceptionDto dto = new ApplicationExceptionDto();
		dto.setError("Exception");
		return dto;
	}
	
	private void setResponseTypeToJson(HttpServletRequest request) {
		request.setAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, Collections.singleton(MediaType.APPLICATION_JSON));
	}
	
	private ApplicationExceptionDto convertException(ApplicationException ex, HttpServletRequest request) {
		setResponseTypeToJson(request);
		ApplicationExceptionDto dto = new ApplicationExceptionDto();
		dto.setError(ex.getError());
		String message = ex.getMessage();
		dto.setMessage(message);
		return dto;
	}
}

