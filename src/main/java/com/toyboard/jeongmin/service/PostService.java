package com.toyboard.jeongmin.service;

import com.toyboard.jeongmin.request.PostRequest;
import com.toyboard.jeongmin.domain.Post;
import com.toyboard.jeongmin.repository.PostRepository;
import com.toyboard.jeongmin.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Post> findAllPosts(){
        PageRequest pageRequest = getPageRequestLimited100DescRegTime();

        return postrepository.findAllPostsLimited100(pageRequest);
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

    public List<Post> searchPostTitleList(String keyword) {
        PageRequest pageRequest = getPageRequestLimited100DescRegTime();

        return postrepository.findByTitleKeyword(keyword, pageRequest);
    }

    private Post getFindByIdPost(Long id) {
        return postrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글 입니다."));
    }

    private PageRequest getPageRequestLimited100DescRegTime() {
        return PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC,("regTime")));
    }

}
