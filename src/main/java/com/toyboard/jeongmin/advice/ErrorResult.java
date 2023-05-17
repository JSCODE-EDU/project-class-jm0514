package com.toyboard.jeongmin.advice;

import lombok.Getter;

@Getter
public class ErrorResult {

    private String error_code;
    private final String message;

    public ErrorResult(String message, String error_code) {
        this.message = message;
        this.error_code = error_code;
    }
}
