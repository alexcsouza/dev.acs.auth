package dev.acs.auth.module.login;

public class ExpiredJWTException extends Exception {
    public ExpiredJWTException() {
    }

    public ExpiredJWTException(String message) {
        super(message);
    }

    public ExpiredJWTException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredJWTException(Throwable cause) {
        super(cause);
    }

    public ExpiredJWTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
