package com.toyboard.jeongmin.like.service;

import com.toyboard.jeongmin.like.domain.PostLike;
import com.toyboard.jeongmin.like.dto.LikeResponse;
import com.toyboard.jeongmin.like.repository.PostLikeRepository;
import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.exception.NotFoundEmailException;
import com.toyboard.jeongmin.member.repository.MemberRepository;
import com.toyboard.jeongmin.post.domain.Post;
import com.toyboard.jeongmin.post.exception.NotFoundPostException;
import com.toyboard.jeongmin.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public LikeResponse PostLike(Long postId, Member member){
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        Long memberId = member.getId();

        int likeCount = postLike(post, memberId);

        boolean liked = postLikeRepository.existsByPostAndMemberId(post, memberId);

        return LikeResponse.of(likeCount, liked);
    }

    private int postLike(Post post, Long memberId) {
        Optional<PostLike> postLike = postLikeRepository.findByPostAndMemberId(post, memberId);
        if (postLike.isPresent()) {
            post.deletePostLike(postLike.get());
            postRepository.decreaseLikeCount(post.getId());
            return post.getLikeCount() - 1;
        }

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(NotFoundEmailException::new);

        PostLike savePostLike = PostLike.builder()
                .member(findMember)
                .post(post)
                .build();
        postLikeRepository.save(savePostLike);

        postRepository.increaseLikeCount(post.getId());
        return post.getLikeCount() + 1;

    }
}
