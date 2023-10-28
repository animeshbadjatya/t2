package com.ecommerce.teamviewerecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class APIException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public APIException(String message) {
        super(message);
    }

    public APIException(String resourceName, String fieldName, String fieldValue){
        super(String.format("Record in the %s already exists with %s  : '%s'", resourceName, fieldName,fieldValue));
    }


}
