package com.toyboard.jeongmin.comment.repository;

import com.toyboard.jeongmin.comment.domain.Comment;
import com.toyboard.jeongmin.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.post = :post")
    List<Comment> findByCommentByPost(@Param("post") Post post);
}
