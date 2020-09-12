package com.finra.rest.Utility;

public class DuplicateFileException extends RuntimeException {
    public DuplicateFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateFileException(String message) {
        super(message);
    }

    public DuplicateFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
