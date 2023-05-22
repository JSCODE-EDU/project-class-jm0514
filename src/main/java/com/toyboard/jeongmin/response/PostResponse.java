package com.toyboard.jeongmin.response;

import com.toyboard.jeongmin.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final LocalDateTime regTime;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle().getValue();
        this.content = post.getContent().getValue();
        this.regTime = post.getRegTime();
    }


}
