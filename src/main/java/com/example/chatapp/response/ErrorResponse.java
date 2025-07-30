package com.example.chatapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;
}
