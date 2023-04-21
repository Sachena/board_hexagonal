package com.example.board_hexagonal.comment.application.port.in;

import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.dto.CreateCommentDTO;

public interface CreateCommentUsecase {
    Comment createComment(CreateCommentDTO createCommentDTO);
}
