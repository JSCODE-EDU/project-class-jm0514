package com.toyboard.jeongmin.response;

import com.toyboard.jeongmin.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse {

    private final Long id;

    private final String title;

    private final String content;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

}
