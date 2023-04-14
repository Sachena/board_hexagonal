package com.example.board_hexagonal.attachedFile.domain;

import com.example.board_hexagonal.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AttachedFile {
    private Long id;

    private String url;

    private Post post;
}
