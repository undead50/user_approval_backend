package com.ebl.userapproval.custom;

public class BlacklistedTokenException extends RuntimeException {

    public BlacklistedTokenException(String message) {
        super(message);
    }
}