package com.example.board_hexagonal.comment.entity;

import com.example.board_hexagonal.post.entity.PostEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime createdAt;

    private String authorNickname;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;


    public void createComment(PostEntity post, String authorNickname, String description) {

        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.authorNickname = authorNickname;
        this.addPost(post);
        post.addComment(this);

    }

    public void addPost(PostEntity post) {
        this.post = post;
    }

    public void editComment(String description) {

        this.description = description;

    }
}
