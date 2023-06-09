package com.example.board_hexagonal.post.adapter.out;


import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttachedFileEntity> attachedFiles = new ArrayList<>();

    public void addAttachedFile(AttachedFileEntity attachedFile){
        this.attachedFiles.add(attachedFile);
        attachedFile.addPost(this);
    }

    public void addComment(CommentEntity comment){
        this.comments.add(comment);
        comment.addPost(this);
    }

    public void deleteComment(CommentEntity commentEntity) {
        this.comments.remove(commentEntity);
    }


    public void editPost(String title, String description) {
        this.title = title;
        this.description = description;
        this.updatedAt = LocalDateTime.now();

    }

    public void addAttachedFileList(List<AttachedFileEntity> attachedFileEntityList) {
        this.attachedFiles = attachedFileEntityList;
    }

    public void addUser(UserEntity userEntity){
        this.user = userEntity;
    }


}
