package com.toyboard.jeongmin.comment.controller;

import com.toyboard.jeongmin.comment.dto.CommentRequest;
import com.toyboard.jeongmin.comment.service.CommentService;
import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.jwt.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/boards/{postId}/comment")
    public ResponseEntity<Long> writeComment(@Login Member member,
                                             @PathVariable Long postId,
                                             @Valid @RequestBody CommentRequest commentRequest){
    Long id = commentService.writeComment(member, postId, commentRequest);
    return ResponseEntity.ok(id);
    }

}
