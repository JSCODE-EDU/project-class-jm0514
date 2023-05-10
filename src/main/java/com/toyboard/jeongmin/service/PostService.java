package com.toyboard.jeongmin.service;

import com.toyboard.jeongmin.request.PostRequest;
import com.toyboard.jeongmin.domain.Post;
import com.toyboard.jeongmin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postrepository;

    @Transactional
    public Post writePost(PostRequest postRequest){
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();

        postrepository.save(post);
        return post;
    }

    public Post findPost(Long id){
        return postrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글 입니다."));
    }

    public List<Post> findAllPosts(){
        return postrepository.findAll();
    }

    @Transactional
    public Post modifyPost(Long id, PostRequest postRequest){
        Post post = postrepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("존재하지 않는 게시글 입니다."));

        post.modify(postRequest.getTitle(), postRequest.getContent());
        return post;
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글 입니다"));

        postrepository.delete(post);
    }

}
