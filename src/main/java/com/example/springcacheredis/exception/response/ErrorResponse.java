package com.example.springcacheredis.exception.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorResponse {
    private HttpStatus status;
    private int statusCode;
    private String message;
}
