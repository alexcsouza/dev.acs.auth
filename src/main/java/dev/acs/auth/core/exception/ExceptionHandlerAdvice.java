package dev.acs.auth.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler{
//	
	@ExceptionHandler(value 
		      = { RuntimeException.class, IllegalStateException.class })
	public ResponseEntity<Object> handleException(RuntimeException ex){
		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
//	
//	
//	@ExceptionHandler(value = UsernameNotFoundException.class)
//	protected ResponseEntity<Object> handleException(UsernameNotFoundException ex, WebRequest request){
//		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
//		return response;
//	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> handleException(Throwable ex){
		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleException(UsernameNotFoundException ex){
		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		return response;
	}	
}
