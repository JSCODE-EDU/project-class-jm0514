package com.toyboard.jeongmin.post.response;

import com.toyboard.jeongmin.comment.dto.CommentResponse;
import com.toyboard.jeongmin.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponse {

    @Schema(description = "게시판 id")
    private final Long id;

    @Schema(description = "게시판 제목")
    private final String title;

    @Schema(description = "게시판 내용")
    private final String content;

    @Schema(description = "게시판 생성일자")
    private final LocalDateTime regTime;

    private final List<CommentResponse> comments;

    @Builder
    public PostResponse(Long id, String title, String content, LocalDateTime regTime, List<CommentResponse> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regTime = regTime;
        this.comments = comments;
    }

    public static PostResponse read(Post post, List<CommentResponse> comments) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle().getValue())
                .content(post.getContent().getValue())
                .regTime(post.getRegTime())
                .comments(comments)
                .build();
    }

    public static PostResponse of(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle().getValue())
                .content(post.getContent().getValue())
                .regTime(post.getRegTime())
                .build();
    }


}
