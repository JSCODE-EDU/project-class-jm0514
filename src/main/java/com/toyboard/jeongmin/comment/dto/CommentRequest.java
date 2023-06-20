package com.toyboard.jeongmin.comment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

    @NotBlank(message = "댓글은 1자 이상이어야 합니다.")
    private String content;

    public CommentRequest(String content) {
        this.content = content;
    }

}
