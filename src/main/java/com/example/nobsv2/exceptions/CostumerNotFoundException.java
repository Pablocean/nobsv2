package com.example.nobsv2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CostumerNotFoundException extends RuntimeException{
    public CostumerNotFoundException() {
        super(ErrorMessages.COSTUMER_NOT_FOUND.getMessage());
    }
}
