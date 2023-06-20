package com.toyboard.jeongmin.post.controller;

import com.toyboard.jeongmin.advice.ErrorResult;
import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.jwt.Login;
import com.toyboard.jeongmin.post.request.PostRequest;
import com.toyboard.jeongmin.post.response.PostResponse;
import com.toyboard.jeongmin.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 조회", description = "해당 id의 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글 조회",
                    content = @Content(schema = @Schema(implementation = ErrorResult.class)))
    })
    @GetMapping("/{postId}")
    public PostResponse getPost(@Parameter(description = "게시글의 id", in = ParameterIn.PATH)
                                @PathVariable Long postId) {

        return postService.findPost(postId);
    }

    @Operation(summary = "게시글 생성", description = "요청 DTO에 담긴 데이터로 게시글 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 생성 성공" ,
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest,
                                                   @Login Member member){
        PostResponse saveResponse = postService.writePost(postRequest, member);
        return ResponseEntity.ok(saveResponse);
    }

    @Operation(summary = "게시글 수정", description = "해당 id의 게시글을 요청 DTO에 담긴 데이터로 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글 조회",
                    content = @Content(schema = @Schema(implementation = ErrorResult.class)))
    })
    @PatchMapping("/{postId}")
    public PostResponse modifyPost(@Parameter(description = "게시글의 id", in = ParameterIn.PATH)
                                   @PathVariable Long postId,
                                   @Valid @RequestBody PostRequest postRequest,
                                   @Login Member member) {

        return postService.modifyPost(postId, postRequest, member);
    }

    @Operation(summary = "게시글 삭제", description = "해당 id의 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 삭제 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글 조회",
                    content = @Content(schema = @Schema(implementation = ErrorResult.class)))
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(
            @Parameter(description = "게시글의 id", in = ParameterIn.PATH) @PathVariable Long postId,
            @Login Member member) {

        postService.deletePost(postId, member);
        return ResponseEntity.ok("성공적으로 삭제되었습니다.");
    }

    @Operation(summary = "게시글 검색", description = "검색 키워드를 통해 게시글 제목을 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 검색 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "400", description = "검색 키워드가 조건에 맞지 않음",
                    content = @Content(schema = @Schema(implementation = ErrorResult.class)))
    })
    @GetMapping
    public List<PostResponse> searchPostTitle(@RequestParam("keyword") String keyword) {

        return postService.searchPostTitleList(keyword);
    }

    @Operation(summary = "게시글 모두 조회", description = "모든 게시글을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/list")
    public List<PostResponse> findAllPost(){
        return postService.findAllPosts();
    }

}
