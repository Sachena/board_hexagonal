package com.example.board_hexagonal.comment.dto;

import lombok.Data;

@Data
public class CreateCommentDTO {

    private Long postId;

    private String authorNickname;

    private String description;

}
