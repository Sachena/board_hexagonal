package com.example.board_hexagonal.post.adapter.out;

import com.example.board_hexagonal.attachedFile.adapter.out.AttachedFileMapper;
import com.example.board_hexagonal.comment.adapter.out.CommentMapper;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.repository.PostRepository;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import com.example.board_hexagonal.user.adapter.out.UserMapper;
import com.example.board_hexagonal.user.domain.User;
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
                post.getTitle(),
                post.getDescription(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                userEntity,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public PostEntity fromDomainToEntityWithId(Post post){

        return postRepository.findById(post.getId()).orElse(null);
    }

    public Post fromEntityToDomain(PostEntity postEntity, User user) {
        return new Post(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getDescription(),
                postEntity.getCreatedAt(),
                postEntity.getUpdatedAt(),
                user.getId().getValue(),
                commentMapper.fromEntityListToDomainList(postEntity.getComments()),
                attachedFileMapper.fromEntityListToDomainList(postEntity.getAttachedFiles())
        );
    }
}
