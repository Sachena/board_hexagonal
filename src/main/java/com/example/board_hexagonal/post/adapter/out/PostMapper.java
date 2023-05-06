package com.example.board_hexagonal.post.adapter.out;

import com.example.board_hexagonal.attachedFile.adapter.out.AttachedFileMapper;
import com.example.board_hexagonal.comment.adapter.out.CommentMapper;
import com.example.board_hexagonal.post.domain.*;
import com.example.board_hexagonal.post.repository.PostRepository;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import com.example.board_hexagonal.user.adapter.out.UserMapper;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final AttachedFileMapper attachedFileMapper;

    private final PostRepository postRepository;



    public PostEntity fromDomainToEntityWithoutId(Post post, UserEntity userEntity){
        return new PostEntity(
                null,
                post.getTitle().getValue(),
                post.getDescription().getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                userEntity,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public PostEntity fromDomainToEntityWithId(Post post){

        return postRepository.findById(post.getId().getValue()).orElse(null);
    }

    public Post fromEntityToDomain(PostEntity postEntity, User user) {
        return new Post(
                new PostId(postEntity.getId()),
                new Title(postEntity.getTitle()),
                new PostDescription(postEntity.getDescription()),
                new PostCreatedAt(postEntity.getCreatedAt()),
                new PostUpdatedAt(postEntity.getUpdatedAt()),
                new UserId(user.getId().getValue()),
                commentMapper.fromEntityListToDomainList(postEntity.getComments()),
                attachedFileMapper.fromEntityListToDomainList(postEntity.getAttachedFiles())
        );
    }
}
