package com.toyboard.jeongmin.post.domain;

import com.toyboard.jeongmin.post.exception.InvalidTitleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TitleTest {

    @DisplayName("제목은 1글자 이상 15글자 이하면 정상적으로 작성됩니다.")
    @Test
    void correctTitle(){
        // given
        String testTitle = "제목입니다.";

        // when
        Title title = new Title(testTitle);

        // then
        assertThat(title.getValue()).isEqualTo("제목입니다.");

    }

    @DisplayName("제목은 15글자를 초과했을 때 예외가 발생한다.")
    @Test
    void titleLengthException(){
        // given
        String testTitle = "1234567890123456";

        // then
        assertThatThrownBy(() -> new Title(testTitle))
                .isInstanceOf(InvalidTitleException.class);
    }

    @DisplayName("제목이 공백일 때 예외가 발생한다.")
    @Test
    void titleEmptyException(){
        // given
        String testTitle = "";

        // then
        assertThatThrownBy(() -> new Title(testTitle))
                .isInstanceOf(InvalidTitleException.class);
    }

}