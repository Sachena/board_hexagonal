package com.example.board_hexagonal.post.domain;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.post.dto.CreatePostDto;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import com.example.board_hexagonal.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private User user;

    private List<Comment> comments;

    private List<AttachedFile> attachedFiles;

    public Post createPost(User author, String title, String description, List<AttachedFile> attachedFiles) {
        return new Post(
                null,
                title,
                description,
                LocalDateTime.now(),
                LocalDateTime.now(),
                author,
                null,
                attachedFiles

        );
    }

}
