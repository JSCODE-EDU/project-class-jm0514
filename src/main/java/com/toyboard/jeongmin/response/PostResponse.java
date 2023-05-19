package com.toyboard.jeongmin.response;

import com.toyboard.jeongmin.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

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

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle().getValue();
        this.content = post.getContent().getValue();
        this.regTime = post.getRegTime();
    }


}
