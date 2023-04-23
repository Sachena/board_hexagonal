package com.example.board_hexagonal.comment.dto;

import lombok.Data;

@Data
public class DeleteCommentDTO {
    private Long id;
    private Long postId;
}
