package com.logextractor.logextractor.enums;

public enum HeaderType {
	CONTENT_DISPOSITION("Content-Disposition");

    private final String type;

    HeaderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
