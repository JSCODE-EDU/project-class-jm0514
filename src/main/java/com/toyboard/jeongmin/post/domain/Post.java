package com.toyboard.jeongmin.post.domain;

import com.toyboard.jeongmin.comment.domain.Comment;
import com.toyboard.jeongmin.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    private LocalDateTime regTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content, LocalDateTime regTime, Member member,
                List<Comment> comments) {
        this.title = new Title(title);
        this.content = new Content(content);
        this.regTime = LocalDateTime.now();
        this.member = member;
        this.comments = comments;
    }

    public void modify(String title, String content) {
        this.title = new Title(title);
        this.content = new Content(content);
    }


}
