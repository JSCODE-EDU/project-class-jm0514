package com.toyboard.jeongmin.advice;

import com.toyboard.jeongmin.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostExControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResult> badRequestExHandle(BadRequestException e) {
        return ResponseEntity.badRequest().body(new ErrorResult(e.getMessage(), "400"));
    }
}
