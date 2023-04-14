package com.example.board_hexagonal.post.dto;

import lombok.Data;

import java.util.List;

@Data
public class EditPostDTO {

    private Long id;

    private String title;

    private String description;

    private List<String> fileUrls;


}
