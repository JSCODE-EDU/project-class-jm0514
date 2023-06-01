package com.toyboard.jeongmin.member.exception;

import com.toyboard.jeongmin.advice.BadRequestException;

public class DuplicateEmailException extends BadRequestException {

    private final static String MESSAGE = "중복된 이메일 입니다.";

    public DuplicateEmailException() {
        super(MESSAGE);
    }
}
