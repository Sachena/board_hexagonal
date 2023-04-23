package com.example.board_hexagonal.comment.application.port.in;

import com.example.board_hexagonal.comment.dto.DeleteCommentDTO;

public interface DeleteCommentUsecase {
    void deleteComment(DeleteCommentDTO deleteCommentDTO);
}
