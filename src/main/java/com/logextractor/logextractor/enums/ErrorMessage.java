package com.logextractor.logextractor.enums;

public enum ErrorMessage {
	ERROR_OCCURRED("An error occurred: {}");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
