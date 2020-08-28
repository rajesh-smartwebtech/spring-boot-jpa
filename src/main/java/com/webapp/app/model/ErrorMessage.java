package com.webapp.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorMessage {

    boolean error;
    String message;

    public ErrorMessage(boolean error,String message){

        this.error=error;
        this.message=message;
    }
}
