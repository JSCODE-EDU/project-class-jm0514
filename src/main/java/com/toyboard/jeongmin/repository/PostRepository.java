package com.toyboard.jeongmin.repository;

import com.toyboard.jeongmin.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
