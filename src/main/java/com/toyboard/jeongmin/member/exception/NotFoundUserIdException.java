package com.toyboard.jeongmin.member.exception;

import com.toyboard.jeongmin.advice.BadRequestException;

public class NotFoundUserIdException extends BadRequestException {

    private static final String MESSAGE = "해당 id로 조회되는 유저가 없습니다.";

    public NotFoundUserIdException() {
        super(MESSAGE);
    }
}
