package com.example.board_hexagonal.post.domain;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.domain.Url;
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

    public Post createPost(User author, CreatePostDto createPostDto) {

        List<AttachedFile> attachedFiles = new ArrayList<>();
        for (String fileUrl : createPostDto.getFileUrls()) {
            attachedFiles.add(new AttachedFile(null,new Url(fileUrl)));
        }


        return new Post(
                null,
                new Title(createPostDto.getTitle()),
                new PostDescription(createPostDto.getDescription()),
                new PostCreatedAt(LocalDateTime.now()),
                new PostUpdatedAt(LocalDateTime.now()),
                new UserId(author.getId().getValue()),
                new ArrayList<>(),
                attachedFiles

        );
    }

    public Post editPost(EditPostDTO editPostDTO) {

        List<AttachedFile> attachedFileList = new ArrayList<>();
        for (String fileUrl : editPostDTO.getFileUrls()) {
            attachedFileList.add(new AttachedFile(null,new Url(fileUrl)));
        }

        return new Post(
                new PostId(editPostDTO.getId()),
                new Title(editPostDTO.getTitle()),
                new PostDescription(editPostDTO.getDescription()),
                this.getCreatedAt(),
                new PostUpdatedAt(LocalDateTime.now()),
                this.getUserId(),
                this.getComments(),
                attachedFileList
        );
    }


}
