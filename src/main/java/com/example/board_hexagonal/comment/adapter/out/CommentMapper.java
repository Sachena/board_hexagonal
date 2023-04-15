package com.example.board_hexagonal.comment.adapter.out;

import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.adapter.out.PostMapper;
import com.example.board_hexagonal.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final PostMapper postMapper;

    public Comment mapToDomain(CommentEntity commentEntity) {
        return new Comment(
                commentEntity.getId(),
                commentEntity.getDescription(),
                commentEntity.getCreatedAt(),
                commentEntity.getAuthorNickname(),
                postMapper.mapToDomain(commentEntity.getPost())
        );
    }

    public List<Comment> mapToDomainList(List<CommentEntity> commentEntityList) {

        List<Comment> commentList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            Comment commentDomain = new Comment(
                    commentEntity.getId(),
                    commentEntity.getDescription(),
                    commentEntity.getCreatedAt(),
                    commentEntity.getAuthorNickname(),
                    postMapper.mapToDomain(commentEntity.getPost())
            );
            commentList.add(commentDomain);
        }

        return commentList;
    }

}
