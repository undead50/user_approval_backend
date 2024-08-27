package com.ebl.userapproval.execption;

import org.springframework.http.HttpStatus;

public class EblException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;
    private String timestamp = String.valueOf(System.currentTimeMillis());
    private String errorCode;

    public EblException(HttpStatus httpStatus, String message) {
        super();
        this.message = message;
        this.httpStatus = httpStatus;

    }
}
