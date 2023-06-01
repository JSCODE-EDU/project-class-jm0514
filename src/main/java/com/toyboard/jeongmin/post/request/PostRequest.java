package com.toyboard.jeongmin.post.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class PostRequest {

    @Schema(description = "제목")
    @NotBlank(message = "제목은 1자이상 15자 이하여야 합니다.")
    private String title;

    @Schema(description = "내용")
    @NotBlank(message = "내용은 1자이상 1000자 이하여야 합니다.")
    private String content;

    @Schema(description = "생성일자")
    private LocalDateTime regTime;


}
