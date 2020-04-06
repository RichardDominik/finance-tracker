package com.financetracker.server.controller.response;

public class DefaultResponse {

    private Exception e;
    private String message;

    public DefaultResponse(Exception e, String message){
        this.e = e;
        this.message = message;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
