package com.toyboard.jeongmin.post.service;

import com.toyboard.jeongmin.comment.dto.CommentResponse;
import com.toyboard.jeongmin.comment.service.CommentService;
import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.exception.NotFoundUserIdException;
import com.toyboard.jeongmin.member.repository.MemberRepository;
import com.toyboard.jeongmin.post.domain.Keyword;
import com.toyboard.jeongmin.post.exception.NotEqualIdsException;
import com.toyboard.jeongmin.post.exception.NotFoundPostException;
import com.toyboard.jeongmin.post.request.PostRequest;
import com.toyboard.jeongmin.post.domain.Post;
import com.toyboard.jeongmin.post.repository.PostRepository;
import com.toyboard.jeongmin.post.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postrepository;

    private final MemberRepository memberRepository;

    private final CommentService commentService;

    @Transactional
    public PostResponse writePost(PostRequest postRequest, Member member){
        Member m = findMemberById(member);

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .regTime(postRequest.getRegTime())
                .member(m)
                .build();

        postrepository.save(post);
        return PostResponse.of(post);
    }

    private Member findMemberById(Member member) {
        return memberRepository.findById(member.getId())
                .orElseThrow(NotFoundUserIdException::new);
    }

    public PostResponse findPost(Long id){
        Post post = getFindByIdPost(id);
        List<CommentResponse> comments = commentService.readComment(post).stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());

        return PostResponse.read(post, comments);
    }

    public List<PostResponse> findAllPosts(){
        PageRequest pageRequest = getPageRequestLimited100DescRegTime();

        return postrepository.findAllPostsLimited100(pageRequest).stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponse modifyPost(Long id, PostRequest postRequest, Member member){
        Post post = getFindByIdPost(id);

        Long postMemberId = post.getMember().getId();
        Long memberId = member.getId();
        if(!Objects.equals(postMemberId, memberId)){
            throw new NotEqualIdsException();
        }

        post.modify(postRequest.getTitle(), postRequest.getContent());
        return PostResponse.of(post);
    }

    @Transactional
    public void deletePost(Long id, Member member){
        Post post = getFindByIdPost(id);

        Long postMemberId = post.getMember().getId();
        Long memberId = member.getId();
        if(!Objects.equals(postMemberId, memberId)){
            throw new NotEqualIdsException();
        }

        postrepository.delete(post);
    }

    public List<PostResponse> searchPostTitleList(String keyword) {
        PageRequest pageRequest = getPageRequestLimited100DescRegTime();
        Keyword validKeyword = Keyword.validKeyword(keyword);

        return postrepository.findByTitleKeyword(validKeyword.getValue(), pageRequest).stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    private Post getFindByIdPost(Long id) {
        return postrepository.findById(id)
                .orElseThrow(NotFoundPostException::new);
    }

    private PageRequest getPageRequestLimited100DescRegTime() {
        return PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC,("regTime")));
    }

}
