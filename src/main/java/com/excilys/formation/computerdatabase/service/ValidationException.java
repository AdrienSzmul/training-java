package com.excilys.formation.computerdatabase.service;

public class ValidationException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 9079103780861105095L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
