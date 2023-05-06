package com.example.board_hexagonal.attachedFile.domain;

import com.example.board_hexagonal.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AttachedFile {
    private AttachedFileId id;

    private Url url;
}
