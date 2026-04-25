package com.example.demo1.exception;

import com.example.demo1.pojo.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .collect(Collectors.joining(","));
        return Result.fail(400, "Validation failed: " + message);
    }

    @ExceptionHandler(BindException.class)
    public Result<Void> handleBindException(BindException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .collect(Collectors.joining(","));
        return Result.fail(400, "Binding failed: " + message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        if (message != null && message.contains("Required request body is missing")) {
            return Result.fail(400, "Request body is required");
        }
        return Result.fail(400, "Request body format is invalid: " + message);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public Result<Void> handleServletRequestBindingException(ServletRequestBindingException e) {
        return Result.fail(400, "Request binding failed: " + e.getMessage());
    }

//    @ExceptionHandler(RuntimeException.class)
//    public Result<Void> handleRuntimeException(RuntimeException e) {
//        return Result.fail(400, e.getMessage());
//    }

    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        return Result.fail(400, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        e.printStackTrace();
        return Result.fail("Server error: " + e.getMessage());
    }
}
