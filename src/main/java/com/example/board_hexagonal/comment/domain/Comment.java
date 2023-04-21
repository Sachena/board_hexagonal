package com.example.board_hexagonal.comment.domain;

import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;

    private String description;

    private LocalDateTime createdAt;

    private String authorNickname;

    public Comment createComment(String description, String authorNickname) {
        return new Comment(
                null,
                this.description = description,
                LocalDateTime.now(),
                this.authorNickname = authorNickname
        );
    }

    public Comment editComment(String description){
        return new Comment(
                this.getId(),
                this.description = description,
                this.getCreatedAt(),
                this.getAuthorNickname()
        );
    }
}
