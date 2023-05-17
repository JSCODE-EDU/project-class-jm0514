package com.toyboard.jeongmin.exception;

public class InvalidKeywordException extends BadRequestException {

    private final static String MESSAGE = "검색 키워드는 빈칸 없이 1개 이상의 단어를 입력해 주세요";

    public InvalidKeywordException() {
        super(MESSAGE);
    }
}
