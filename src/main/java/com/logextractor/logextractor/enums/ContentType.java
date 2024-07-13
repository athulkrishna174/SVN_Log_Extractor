package com.logextractor.logextractor.enums;

public enum ContentType {
	TEXT("text/plain"),
    SPREADSHEET("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
