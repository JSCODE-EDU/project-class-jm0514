package com.toyboard.jeongmin.post.response;

import com.toyboard.jeongmin.comment.domain.Comment;
import com.toyboard.jeongmin.comment.dto.CommentRequest;
import com.toyboard.jeongmin.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private final List<Comment> comments;


    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle().getValue();
        this.content = post.getContent().getValue();
        this.regTime = post.getRegTime();
        for(Comment c : content){

        }
        this.comments = post.getComments()
    }


}
