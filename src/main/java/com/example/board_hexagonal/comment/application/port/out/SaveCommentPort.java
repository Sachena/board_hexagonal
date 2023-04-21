package com.example.board_hexagonal.comment.application.port.out;

import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.post.domain.Post;

public interface SaveCommentPort {
    void createComment(Comment comment, Post post);

    void updateComment(Comment comment, Post post);
}
