package com.example.board_hexagonal.post.adapter.out;


import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<AttachedFileEntity> attachedFiles = new ArrayList<>();

    public void addAttachedFile(AttachedFileEntity attachedFile){
        this.attachedFiles.add(attachedFile);
        attachedFile.addPost(this);
    }

    public void addComment(CommentEntity comment){
        this.comments.add(comment);
        comment.addPost(this);
    }


    public void createPost(String title, String description, UserEntity user, List<String> fileUrls) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.user = user;

        for( String fileUrl : fileUrls){
            AttachedFileEntity newAttachedFile = new AttachedFileEntity();
            newAttachedFile.createAttachedFile(fileUrl, this);
            this.addAttachedFile(newAttachedFile);
        }
    }

    public void editPost(String title, String description, List<String> fileUrls) {
        this.title = title;
        this.description = description;
        this.updatedAt = LocalDateTime.now();

        for( String fileUrl : fileUrls){
            AttachedFileEntity newAttachedFile = new AttachedFileEntity();
            newAttachedFile.createAttachedFile(fileUrl, this);
            this.addAttachedFile(newAttachedFile);
        }
    }
}
