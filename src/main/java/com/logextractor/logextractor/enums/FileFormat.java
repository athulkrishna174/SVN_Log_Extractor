package com.logextractor.logextractor.enums;

public enum FileFormat {
	CSV(".csv"),
    TXT(".txt"),
    XLSX(".xlsx");

    private final String format;

    FileFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
