package com.toyboard.jeongmin.comment.domain;

import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime regTime;

    @Builder
    private Comment(String content, Post post, Member member, LocalDateTime regTime) {
        this.content = content;
        this.post = post;
        this.member = member;
        this.regTime = LocalDateTime.now();
    }

    public static Comment of(String content, Post post, Member member) {
        return Comment.builder()
                .content(content)
                .post(post)
                .member(member)
                .build();
    }

}
