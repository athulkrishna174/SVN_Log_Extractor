package com.logextractor.logextractor.enums;

public enum ExtractorAction {
	SORT_FILE("SORT_FILE"),
    REMOVE_DUPLICATE("REMOVE_DUPLICATE"),
    REMOVE_UNWANTED("REMOVE_UNWANTED");

    private final String action;

    ExtractorAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
