package dev.acs.auth.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessLogigException extends Exception{

	private static final long serialVersionUID = -4964038519770891487L;

	public BusinessLogigException() {
		super();
	}

	public BusinessLogigException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public BusinessLogigException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BusinessLogigException(String arg0) {
		super(arg0);
	}

	public BusinessLogigException(Throwable arg0) {
		super(arg0);
	}
	
}
