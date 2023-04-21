package com.example.board_hexagonal.comment.application.port.out;

import com.example.board_hexagonal.comment.domain.Comment;

public interface RetrieveCommentPort {
    Comment retrieveComment(Long commentId);
}
