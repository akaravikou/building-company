package com.solvd.buildingcompany.domain.exception;

public class ConnectException extends Exception {

    public ConnectException() {
        super();
    }

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectException(Throwable cause) {
        super(cause);
    }

    protected ConnectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
