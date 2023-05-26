package com.toyboard.jeongmin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    private LocalDateTime regTime;

    @Builder
    public Post(String title, String content, LocalDateTime regTime) {
        this.title = new Title(title);
        this.content = new Content(content);
        this.regTime = LocalDateTime.now();
    }

    public void modify(String title, String content){
        this.title = new Title(title);
        this.content = new Content(content);
    }

}
