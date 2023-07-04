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
    private LikeResponse(int likeCount, boolean like) {
        this.likeCount = likeCount;
        this.like = like;
    }

    public static LikeResponse of(int likeCount, boolean like) {
        return LikeResponse.builder()
                .likeCount(likeCount)
                .like(like)
                .build();
    }

}
