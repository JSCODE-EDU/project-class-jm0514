package com.toyboard.jeongmin.post.service;

import com.toyboard.jeongmin.post.domain.Keyword;
import com.toyboard.jeongmin.post.exception.PostNotFoundException;
import com.toyboard.jeongmin.post.request.PostRequest;
import com.toyboard.jeongmin.post.domain.Post;
import com.toyboard.jeongmin.post.repository.PostRepository;
import com.toyboard.jeongmin.post.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postrepository;

    @Transactional
    public PostResponse writePost(PostRequest postRequest){
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .regTime(postRequest.getRegTime())
                .build();

        postrepository.save(post);
        return new PostResponse(post);
    }

    public PostResponse findPost(Long id){
        Post post = getFindByIdPost(id);

        return new PostResponse(post);
    }

    public List<PostResponse> findAllPosts(){
        PageRequest pageRequest = getPageRequestLimited100DescRegTime();

        return postrepository.findAllPostsLimited100(pageRequest).stream()
                .map(post -> new PostResponse(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponse modifyPost(Long id, PostRequest postRequest){
        Post post = getFindByIdPost(id);

        post.modify(postRequest.getTitle(), postRequest.getContent());
        return new PostResponse(post);
    }

    @Transactional
    public void deletePost(Long id){
        Post post = getFindByIdPost(id);
        postrepository.delete(post);
    }

    public List<PostResponse> searchPostTitleList(String keyword) {
        PageRequest pageRequest = getPageRequestLimited100DescRegTime();
        Keyword validKeyword = Keyword.validKeyword(keyword);

        return postrepository.findByTitleKeyword(validKeyword.getValue(), pageRequest).stream()
                .map(post -> new PostResponse(post))
                .collect(Collectors.toList());
    }

    private Post getFindByIdPost(Long id) {
        return postrepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException());
    }

    private PageRequest getPageRequestLimited100DescRegTime() {
        return PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC,("regTime")));
    }

}
