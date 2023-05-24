package com.toyboard.jeongmin.advice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ErrorResult {

    @Schema(description = "오류 코드")
    private final Integer error_code;

    @Schema(description = "오류 메시지")
    private final String message;

    public ErrorResult(String message, Integer error_code) {
        this.message = message;
        this.error_code = error_code;
    }

}
