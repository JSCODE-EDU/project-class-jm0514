package com.toyboard.jeongmin.domain;

import com.toyboard.jeongmin.exception.InvalidKeywordException;
import lombok.Getter;

@Getter
public class Keyword {

    private final String value;

    public Keyword(String value) {
        this.value = value;
    }

    public static Keyword validKeyword(String value) {
        if (value.trim().length() == 0) {
            throw new InvalidKeywordException();
        } else {
            return new Keyword(value);
        }
    }
}
