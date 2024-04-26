package com.example.springcacheredis.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvoiceNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = -8299696104060804659L;

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
