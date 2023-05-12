package com.toyboard.jeongmin.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequest {

    private String title;

    private String content;

    private LocalDateTime regTime;



}
