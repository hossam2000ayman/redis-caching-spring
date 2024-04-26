package com.example.springcacheredis.exception.handler;

import com.example.springcacheredis.exception.InvoiceNotFoundException;
import com.example.springcacheredis.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvoiceNotFoundException(InvoiceNotFoundException invoiceNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(invoiceNotFoundException.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
