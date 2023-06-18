package com.toyboard.jeongmin.post.domain;

import com.toyboard.jeongmin.post.exception.InvalidKeywordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class KeywordTest {

    @DisplayName("검색 키워드는 1단어 이상이면 정상적으로 검색된다.")
    @Test
    void correctKeyword(){
        // given
        String testKeyword = "검색어";

        // when
        Keyword keyword = new Keyword(testKeyword);

        // then
        assertThat(keyword.getValue()).isEqualTo("검색어");

    }

//    @DisplayName("검색 키워드가 비어있으면 예외가 발생한다.")
//    @Test
//    void keywordEmptyException(){
//        // given
//        String testKeyword = "";
//
//        // then
//        assertThatThrownBy(() -> new Keyword(testKeyword))
//                .isInstanceOf(InvalidKeywordException.class);
//    }

}