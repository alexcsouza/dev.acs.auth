package dev.acs.auth.core.exception;

public class RowNotFound extends Exception{

	private static final long serialVersionUID = -7404955662383180622L;

	public RowNotFound() {
		super();
	}

	public RowNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RowNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public RowNotFound(String message) {
		super(message);
	}

	public RowNotFound(Throwable cause) {
		super(cause);
	}
	
	
}
