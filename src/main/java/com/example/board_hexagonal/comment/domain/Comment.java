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

    private CommentId id;

    private CommentDescription description;

    private CommentCreatedAt createdAt;

    private AuthorNickname authorNickname;

    public Comment createComment(String description, String authorNickname) {
        return new Comment(
                null,
                this.description = new CommentDescription(description),
                this.createdAt = new CommentCreatedAt(LocalDateTime.now()),
                this.authorNickname = new AuthorNickname(authorNickname)
        );
    }

    public Comment editComment(String description){
        return new Comment(
                this.getId(),
                this.description = new CommentDescription(description),
                this.getCreatedAt(),
                this.getAuthorNickname()
        );
    }
}
