package com.logextractor.logextractor.enums;

public enum FileName {
	EXCEL_FILE("ExtractedLogs");

    private final String name;

    FileName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
