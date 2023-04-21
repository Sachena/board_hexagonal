package com.example.board_hexagonal.comment.dto;

import lombok.Data;

@Data
public class EditCommentDTO {

    private Long id;
    private Long postId;
    private String description;

}
