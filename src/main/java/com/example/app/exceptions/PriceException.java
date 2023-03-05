package com.example.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PriceException extends ResponseStatusException {

    private String code;
    private String text;

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public PriceException(HttpStatus status, String reason, String code, String text) {
        super(status, reason);
        this.code = code;
        this.text = text;
    }


}
