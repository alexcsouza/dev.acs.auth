package dev.acs.auth.core.exception;

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
