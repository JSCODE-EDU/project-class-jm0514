package com.toyboard.jeongmin.controller;

import com.toyboard.jeongmin.domain.Post;
import com.toyboard.jeongmin.request.PostRequest;
import com.toyboard.jeongmin.response.PostResponse;
import com.toyboard.jeongmin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.findPost(postId);
    }

    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest postRequest){
        Post post = postService.writePost(postRequest);
        return new PostResponse(post);
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postService.findAllPosts();
    }

    @PatchMapping("/{postId}")
    public PostResponse modifyPost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        Post post = postService.modifyPost(postId, postRequest);
        return new PostResponse(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

}
