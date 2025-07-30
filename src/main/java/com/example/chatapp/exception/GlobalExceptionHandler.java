package com.example.chatapp.exception;

import com.example.chatapp.response.ErrorResponse;
import com.example.chatapp.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException appException){
        final ErrorResponse errorResponse = new ErrorResponse(
                appException.getHttpStatus(),
                appException.getErrorCode(),
                appException.getMessage()
        );
        return new ResponseEntity<>(errorResponse,appException.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<Map<String, String>>> methodArgumentNotValidException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {
        final List<FieldError> fieldErrors = methodArgumentNotValidException.getFieldErrors();
        final Map<String, String> errors = new HashMap<>();

        fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponse.error(errors));
    }
}
