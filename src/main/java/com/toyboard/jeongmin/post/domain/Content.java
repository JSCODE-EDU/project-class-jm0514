package com.toyboard.jeongmin.post.domain;

import com.toyboard.jeongmin.post.exception.InvalidContentException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    private final static int CONTENT_MAX_LENGTH = 1000;

    @Column(name = "content", nullable = false)
    @Lob
    private String value;

    public Content(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null) {
            throw new InvalidContentException();
        }
        if (value.length() > CONTENT_MAX_LENGTH) {
            throw new InvalidContentException();
        }
    }

}
