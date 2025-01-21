package com.example.nobsv2.exceptions;

import lombok.Getter;

//@Getter
public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found"),
    NAME_REQUIRED("Name is required"),
    DESCRIPTION_LENGTH("Description must be 20 characters"),
    PRICE_CANNOT_BE_NEGATIVE("Price cannot be negative"),
    COSTUMER_NOT_FOUND("Costumer Not Found");

    private final String message;

    ErrorMessages(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}