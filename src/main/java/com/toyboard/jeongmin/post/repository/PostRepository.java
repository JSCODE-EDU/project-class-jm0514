package com.toyboard.jeongmin.post.repository;

import com.toyboard.jeongmin.post.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.title.value like %:keyword%")
    List<Post> findByTitleKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("select p from Post p")
    List<Post> findAllPostsLimited100(Pageable pageable);
}
