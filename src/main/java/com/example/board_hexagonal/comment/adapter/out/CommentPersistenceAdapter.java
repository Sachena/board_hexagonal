package com.example.board_hexagonal.comment.adapter.out;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.comment.application.port.out.RetrieveCommentPort;
import com.example.board_hexagonal.comment.application.port.out.SaveCommentPort;
import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.comment.repository.CommentRepository;
import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.adapter.out.PostMapper;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.repository.PostRepository;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CommentPersistenceAdapter implements SaveCommentPort, RetrieveCommentPort {

    private final CommentMapper commentMapper;

    private final PostMapper postMapper;

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Override
    public void createComment(Comment comment, Post post) {
        PostEntity postEntity = postMapper.fromDomainToEntityWithId(post);

        CommentEntity commentEntity = commentMapper.fromDomainToEntity(comment, comment.getAuthorNickname() ,postEntity);
        commentEntity.addPost(postEntity);

        commentEntity = commentRepository.save(commentEntity);

        postEntity.addComment(commentEntity);
        postRepository.save(postEntity);

    }

    @Override
    public void updateComment(Comment comment, Post post) {
        PostEntity postEntity = postMapper.fromDomainToEntityWithId(post);
        CommentEntity commentEntity = commentMapper.fromDomainToEntity(comment, comment.getAuthorNickname() ,postEntity);
        commentRepository.save(commentEntity);
    }

    @Override
    public Comment retrieveComment(Long commentId) {
        CommentEntity retrieveCommentEntity = commentRepository.findById(commentId).orElse(null);
        return commentMapper.fromEntityToDomain(retrieveCommentEntity);
    }
}
