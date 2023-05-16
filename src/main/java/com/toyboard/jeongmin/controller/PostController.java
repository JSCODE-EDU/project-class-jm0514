package com.toyboard.jeongmin.controller;

import com.toyboard.jeongmin.domain.Post;
import com.toyboard.jeongmin.request.PostRequest;
import com.toyboard.jeongmin.response.PostResponse;
import com.toyboard.jeongmin.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
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

    @PatchMapping("/{postId}")
    public PostResponse modifyPost(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        Post post = postService.modifyPost(postId, postRequest);
        return new PostResponse(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

    @GetMapping
    public List<Post> searchPostTitle(@RequestParam("keyword") String keyword){
        if(keyword == null){
            return postService.findAllPosts();
        }else{
            return postService.searchPostTitleList(keyword);
        }
    }

}
