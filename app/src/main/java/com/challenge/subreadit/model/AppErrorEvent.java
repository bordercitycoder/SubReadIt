package com.challenge.subreadit.model;

public class AppErrorEvent {

    private String errorMessage;

    public AppErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
