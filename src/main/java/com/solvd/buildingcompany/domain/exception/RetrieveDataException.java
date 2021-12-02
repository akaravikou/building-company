package com.solvd.buildingcompany.domain.exception;

public class RetrieveDataException extends Exception{

    public RetrieveDataException() {
        super();
    }

    public RetrieveDataException(String message) {
        super(message);
    }

    public RetrieveDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetrieveDataException(Throwable cause) {
        super(cause);
    }

    protected RetrieveDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
