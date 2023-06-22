package com.toyboard.jeongmin.post.domain;

import com.toyboard.jeongmin.comment.domain.Comment;
import com.toyboard.jeongmin.like.domain.PostLike;
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

    private int likeCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PostLike> postLikes = new ArrayList<>();

    @Builder
    public Post(String title, String content, LocalDateTime regTime, Member member,
                List<Comment> comments, int likeCount, List<PostLike> postLikes) {
        this.title = new Title(title);
        this.content = new Content(content);
        this.regTime = LocalDateTime.now();
        this.member = member;
        this.comments = comments;
        this.likeCount = likeCount;
        this.postLikes = postLikes;
    }

    public void modify(String title, String content) {
        this.title = new Title(title);
        this.content = new Content(content);
    }

    public void addPostLike(PostLike postLike) {
        postLikes.add(postLike);
    }

    public void deletePostLike(PostLike postLike) {
        postLikes.remove(postLike);
        postLike.delete();
    }

}
