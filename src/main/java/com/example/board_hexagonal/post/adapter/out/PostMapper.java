package com.example.board_hexagonal.post.adapter.out;

import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import com.example.board_hexagonal.user.adapter.out.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final UserMapper userMapper;


    public PostEntity mapToEntity(Post post){
        return new PostEntity(
                null,
                post.getTitle(),
                post.getDescription(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                null,
                null
        );
    }
}
