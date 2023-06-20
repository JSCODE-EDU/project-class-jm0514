package com.toyboard.jeongmin.comment.service;

import com.toyboard.jeongmin.comment.domain.Comment;
import com.toyboard.jeongmin.comment.dto.CommentRequest;
import com.toyboard.jeongmin.comment.repository.CommentRepository;
import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.exception.NotFoundUserIdException;
import com.toyboard.jeongmin.member.repository.MemberRepository;
import com.toyboard.jeongmin.post.domain.Post;
import com.toyboard.jeongmin.post.exception.NotFoundPostException;
import com.toyboard.jeongmin.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long writeComment(Member member, Long postId, CommentRequest commentRequest) {

        Post post = postRepository.findById(postId).orElseThrow(NotFoundPostException::new);
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(NotFoundUserIdException::new);
        Comment comment = Comment.of(commentRequest.getContent(), post, findMember);

        commentRepository.save(comment);

        return comment.getId();
    }

    public List<Comment> readComment(Post post){
        return commentRepository.findByCommentByPost(post);
    }

}
