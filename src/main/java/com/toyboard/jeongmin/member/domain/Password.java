package com.toyboard.jeongmin.member.domain;

import com.toyboard.jeongmin.member.domain.encryptor.EncryptorI;
import com.toyboard.jeongmin.member.exception.InvalidPasswordException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    private static final String PASSWORD_REGEX = "^(?!.*\\s).{8,15}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @Column(name = "password")
    private String value;

    public static Password of(EncryptorI encryptor, String value) {
        validate(value);
        return new Password(encryptor.encrypt(value));
    }

    public Password(String value) {
        this.value = value;
    }

    public static void validate(String value) {

        if (!PASSWORD_PATTERN.matcher(value).matches()) {
            throw new InvalidPasswordException();
        }
    }
}
