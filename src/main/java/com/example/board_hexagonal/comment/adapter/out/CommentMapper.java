package com.example.board_hexagonal.comment.adapter.out;

import com.example.board_hexagonal.comment.domain.Comment;
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

    public List<Comment> fromEntityListToDomainList(List<CommentEntity> commentEntityList){
        List<Comment> commentList = new ArrayList<>();
        commentEntityList.forEach(commentEntity -> {
            commentList.add(new Comment(
                    commentEntity.getId(),
                    commentEntity.getDescription(),
                    commentEntity.getCreatedAt(),
                    commentEntity.getAuthorNickname()
            ));
        });

        return commentList;

    }

    public CommentEntity fromDomainToEntity(Comment comment, String authorNickname ,PostEntity postEntity) {
        return new CommentEntity(
            comment.getId(),
            comment.getDescription(),
            comment.getCreatedAt(),
            authorNickname,
            postEntity
        );
    }

    public List<CommentEntity> fromDomainListToEntityList(List<Comment> comments, PostEntity postEntity) {
        List<CommentEntity> commentEntityList = new ArrayList<>();
        comments.forEach(comment -> {
            commentEntityList.add(new CommentEntity(
                    comment.getId(),
                    comment.getDescription(),
                    comment.getCreatedAt(),
                    comment.getAuthorNickname(),
                    postEntity
            ));
        });
        return commentEntityList;
    }
}
