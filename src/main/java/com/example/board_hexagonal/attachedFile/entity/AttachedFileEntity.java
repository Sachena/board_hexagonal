package com.example.board_hexagonal.attachedFile.entity;

import com.example.board_hexagonal.post.entity.PostEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class AttachedFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    public void addPost(PostEntity post){
        this.post = post;
    }


    public void createAttachedFile(String fileUrl, PostEntity newPost) {
        this.url = fileUrl;
        this.post = newPost;
    }
}
