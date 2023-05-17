package com.toyboard.jeongmin.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class PostRequest {

    @NotBlank(message = "제목은 1자이상 15자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 1자이상 1000자 이하여야 합니다.")
    private String content;

    private String error_message;

    private LocalDateTime regTime;


}
