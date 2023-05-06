package com.example.board_hexagonal.post.domain;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.post.dto.CreatePostDto;
import com.example.board_hexagonal.post.dto.EditPostDTO;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.domain.UserId;
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

    private PostId id;

    private Title title;

    private PostDescription description;

    private PostCreatedAt createdAt;

    private PostUpdatedAt updatedAt;

    private UserId userId;

    private List<Comment> comments;

    private List<AttachedFile> attachedFiles;

    public Post createPost(Long userId, String title, String description, List<AttachedFile> attachedFiles) {
        return new Post(
                null,
                new Title(title),
                new PostDescription(description),
                new PostCreatedAt(LocalDateTime.now()),
                new PostUpdatedAt(LocalDateTime.now()),
                new UserId(userId),
                new ArrayList<>(),
                attachedFiles

        );
    }

    public Post editPost(Long postId, String title, String description, List<AttachedFile> attachedFileList) {
        return new Post(
                new PostId(postId),
                new Title(title),
                new PostDescription(description),
                this.getCreatedAt(),
                new PostUpdatedAt(LocalDateTime.now()),
                this.getUserId(),
                this.getComments(),
                attachedFileList
        );
    }


}
