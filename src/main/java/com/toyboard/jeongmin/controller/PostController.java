package com.toyboard.jeongmin.controller;

import com.toyboard.jeongmin.request.PostRequest;
import com.toyboard.jeongmin.response.PostResponse;
import com.toyboard.jeongmin.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.findPost(postId);
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest){

        PostResponse saveResponse = postService.writePost(postRequest);
        return ResponseEntity.ok(saveResponse);
    }

    @PatchMapping("/{postId}")
    public PostResponse modifyPost(@PathVariable Long postId,
                                   @Valid @RequestBody PostRequest postRequest) {
        return postService.modifyPost(postId, postRequest);
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return "해당 게시글이 삭제되었습니다!";
    }

    @GetMapping
    public List<PostResponse> searchPostTitle(@RequestParam("keyword") String keyword){
        return postService.searchPostTitleList(keyword);
    }

    @GetMapping("/list")
    public List<PostResponse> findAllPost(){
        return postService.findAllPosts();
    }

}
