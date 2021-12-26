/**
 * 
 */
package com.hcl.ms.core.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author santosh.kushwah
 *
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex) {

		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.NOT_ACCEPTABLE,
				"Arithmetic com.hcl.ms.core.exception: ", ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {

		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE,
				"Null pointer com.hcl.ms.core.exception: ", ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(ApplicationException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(ApplicationException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getErrorMoreInfo());
		ExceptionMessage error = new ExceptionMessage(ex.getStatus().value(), ex.getStatus(), ex.getErrorDetails(),
				details.get(0));
		return new ResponseEntity<Object>(error, ex.getStatus());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				"Validation Failed",
				ex.getConstraintViolations().stream()
						.map(cv -> cv == null ? "null" : cv.getPropertyPath() + " " + cv.getMessage())
						.collect(Collectors.toList()));
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				"Validation Failed",
				ex.getAllErrors().stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList()));
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.METHOD_NOT_ALLOWED.value(),
				HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE,
				ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				"Validation Error",
				ex.getAllErrors().stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList()));
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
				ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.SERVICE_UNAVAILABLE.value(),
				HttpStatus.SERVICE_UNAVAILABLE, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
