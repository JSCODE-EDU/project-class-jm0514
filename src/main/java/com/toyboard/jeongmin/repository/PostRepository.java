package com.toyboard.jeongmin.repository;

import com.toyboard.jeongmin.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainingOrderByRegTimeDesc(String keyword, Pageable pageable);

    @Query("select p from Post p")
    List<Post> findAllPostsLimited100(Pageable pageable);
}
