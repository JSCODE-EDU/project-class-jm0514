package com.toyboard.jeongmin.exception;

import com.toyboard.jeongmin.advice.BadRequestException;

public class InvalidTitleException extends BadRequestException {

    private static final String MESSAGE = "제목은 1자 이상 15자 이하여야 합니다.";

    public InvalidTitleException() {
        super(MESSAGE);
    }
}
