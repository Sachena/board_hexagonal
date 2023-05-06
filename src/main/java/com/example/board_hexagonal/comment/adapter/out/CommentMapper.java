package com.example.board_hexagonal.comment.adapter.out;

import com.example.board_hexagonal.comment.domain.*;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.adapter.out.PostMapper;
import com.example.board_hexagonal.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    public Comment fromEntityToDomain(CommentEntity commentEntity) {

        return new Comment(
                new CommentId(commentEntity.getId()),
                new CommentDescription(commentEntity.getDescription()),
                new CommentCreatedAt(commentEntity.getCreatedAt()),
                new AuthorNickname(commentEntity.getAuthorNickname())
        );
    }

    public List<Comment> fromEntityListToDomainList(List<CommentEntity> commentEntityList){
        List<Comment> commentList = new ArrayList<>();
        commentEntityList.forEach(commentEntity -> {
            commentList.add(new Comment(
                    new CommentId(commentEntity.getId()),
                    new CommentDescription(commentEntity.getDescription()),
                    new CommentCreatedAt(commentEntity.getCreatedAt()),
                    new AuthorNickname(commentEntity.getAuthorNickname())
            ));
        });

        return commentList;

    }

    public CommentEntity fromDomainToEntityWithId(Comment comment, String authorNickname ,PostEntity postEntity) {
        return new CommentEntity(
                comment.getId().getValue(),
            comment.getDescription().getValue(),
            comment.getCreatedAt().getValue(),
            authorNickname,
            postEntity
        );
    }

    public CommentEntity fromDomainToEntityWithoutId(Comment comment, String authorNickname ,PostEntity postEntity) {
        return new CommentEntity(
                null,
                comment.getDescription().getValue(),
                comment.getCreatedAt().getValue(),
                authorNickname,
                postEntity
        );
    }

    public List<CommentEntity> fromDomainListToEntityList(List<Comment> comments, PostEntity postEntity) {
        List<CommentEntity> commentEntityList = new ArrayList<>();
        comments.forEach(comment -> {
            commentEntityList.add(new CommentEntity(
                    comment.getId().getValue(),
                    comment.getDescription().getValue(),
                    comment.getCreatedAt().getValue(),
                    comment.getAuthorNickname().getValue(),
                    postEntity
            ));
        });
        return commentEntityList;
    }


}
