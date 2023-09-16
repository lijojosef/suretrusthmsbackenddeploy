package com.suretrust.hmsfullstackbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DrugNotFoundException extends RuntimeException {
    public DrugNotFoundException(Long id){
        super("Could not found the medicine with id : "+id);
    }
    public DrugNotFoundException(String message) {
        super(message);
    }
}
