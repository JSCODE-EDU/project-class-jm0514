package com.toyboard.jeongmin.comment.dto;

import com.toyboard.jeongmin.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private final Long id;
    private final String content;
    private final String email;
    private final LocalDateTime regTime;

    @Builder
    private CommentResponse(Long id, String content, String email, LocalDateTime regTime) {
        this.id = id;
        this.content = content;
        this.email = email;
        this.regTime = regTime;
    }

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .email(comment.getMember().getEmail().getValue())
                .regTime(comment.getRegTime())
                .build();
    }
}
