package com.toyboard.jeongmin.like.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeResponse {

    private int likeCount;
    private boolean like;

    @Builder
    public LikeResponse(int likeCount, boolean like) {
        this.likeCount = likeCount;
        this.like = like;
    }
}
