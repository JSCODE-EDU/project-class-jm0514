package com.toyboard.jeongmin.domain;

import com.toyboard.jeongmin.exception.InvalidTitleException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Title {

    private final static int TITLE_MAX_LENGTH = 15;

    @Column(name = "title", nullable = false)
    private String value;

    public Title(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidTitleException();
        }
        if (value.length() > TITLE_MAX_LENGTH) {
            throw new InvalidTitleException();
        }
    }

}
