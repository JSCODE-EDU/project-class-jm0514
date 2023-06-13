package com.toyboard.jeongmin.post.domain;

import com.toyboard.jeongmin.post.exception.InvalidContentException;
import com.toyboard.jeongmin.post.exception.InvalidTitleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

    @DisplayName("글 내용은 1000자 이내면 정상적으로 작성됩니다.")
    @Test
    void correctContent(){
        // given
        String testContent = "내용입니다.";

        // when
        Content content = new Content(testContent);

        // then
        assertThat(content.getValue()).isEqualTo("내용입니다.");
    }

    @DisplayName("글 내용은 1000자를 초과하면 예외가 발생합니다.")
    @Test
    void contentLengthException(){
        // given
        String testContent = "1".repeat(1001);

        // then
        assertThatThrownBy(() -> new Content(testContent))
                .isInstanceOf(InvalidContentException.class);
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