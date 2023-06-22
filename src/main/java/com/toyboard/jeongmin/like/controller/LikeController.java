package com.toyboard.jeongmin.like.controller;

import com.toyboard.jeongmin.like.dto.LikeResponse;
import com.toyboard.jeongmin.like.service.PostLikeService;
import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.jwt.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final PostLikeService postLikeService;

    @PutMapping("boards/{postId}/like")
    public ResponseEntity<LikeResponse> postLike(@PathVariable("postId") Long postId,
                                                 @Login Member member) {
        LikeResponse likeResponse = postLikeService.PostLike(postId, member);
        return ResponseEntity.ok(likeResponse);
    }
}
