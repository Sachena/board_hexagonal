package com.example.board_hexagonal.comment.application.port.in;

import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.dto.EditCommentDTO;

public interface EditCommentUsecase {
    Comment editComment(EditCommentDTO editCommentDTO);
}
