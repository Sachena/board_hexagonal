package com.example.board_hexagonal.comment.service;

import com.example.board_hexagonal.comment.application.port.in.CreateCommentUsecase;
import com.example.board_hexagonal.comment.application.port.out.SaveCommentPort;
import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.dto.CreateCommentDTO;
import com.example.board_hexagonal.post.application.port.out.RetrievePostPort;
import com.example.board_hexagonal.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService implements CreateCommentUsecase {

    private final RetrievePostPort retrievePostPort;
    private final SaveCommentPort saveCommentPort;

    @Override
    public Comment createComment(CreateCommentDTO createCommentDTO) {

        Post editPost = retrievePostPort.retrievePost(createCommentDTO.getPostId());

        Comment newComment = new Comment();
        newComment = newComment.createComment(createCommentDTO.getDescription(), createCommentDTO.getAuthorNickname());

        saveCommentPort.createComment(newComment, editPost);
        return newComment;
    }
}
