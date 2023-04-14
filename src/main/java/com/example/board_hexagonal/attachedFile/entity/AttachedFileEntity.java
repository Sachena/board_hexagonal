package com.example.board_hexagonal.attachedFile.entity;

import com.example.board_hexagonal.post.adapter.out.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttachedFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    public AttachedFileEntity addPost(PostEntity post){
        this.post = post;
        return this;
    }


    public void createAttachedFile(String fileUrl, PostEntity newPost) {
        this.url = fileUrl;
        this.post = newPost;
    }
}
