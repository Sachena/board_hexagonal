package com.example.board_hexagonal.post.dto;


import lombok.Data;

import java.util.List;

@Data
public class CreatePostDto {

    private String email;

    private String title;

    private String description;

    private List<String> fileUrls;

}
