package com.finra.rest.Utility;

public class FileNotExistException extends RuntimeException {
    public FileNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotExistException(String message) {
        super(message);
    }

    public FileNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
