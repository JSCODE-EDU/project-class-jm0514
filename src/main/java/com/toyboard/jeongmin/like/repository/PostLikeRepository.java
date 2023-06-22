package com.toyboard.jeongmin.like.repository;

import com.toyboard.jeongmin.like.domain.PostLike;
import com.toyboard.jeongmin.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByPostAndMemberId(Post post, Long postMemberId);

    boolean existsByPostAndMemberId(Post post, Long memberId);
}
