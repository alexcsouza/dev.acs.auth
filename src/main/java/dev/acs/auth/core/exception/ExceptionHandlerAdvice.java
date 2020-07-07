package dev.acs.auth.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
@RestController
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value 
		      = { RuntimeException.class, IllegalStateException.class })
	public ResponseEntity<?> handleException(RuntimeException ex){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> handleException(Throwable ex){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handleException(UsernameNotFoundException ex){
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}	
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<?> handleException(ExpiredJwtException ex){
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
}
