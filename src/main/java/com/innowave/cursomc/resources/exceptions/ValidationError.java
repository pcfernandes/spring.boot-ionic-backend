package com.innowave.cursomc.resources.exceptions;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName,message));
    }
}
