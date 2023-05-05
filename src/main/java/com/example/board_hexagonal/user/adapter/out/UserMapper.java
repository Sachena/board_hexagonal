package com.example.board_hexagonal.user.adapter.out;


import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.repository.PostRepository;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PostRepository postRepository;

    public UserEntity fromDomainToEntityWithoutId(User user){

        return new UserEntity(
                null,
                user.getEmail(),
                user.getPassword(),
                user.getNickname(),
                user.getJoinedAt(),
                user.getIsDeleted(),
                null
        );

    }

    public UserEntity fromDomainToEntityWithId(User user, List<PostEntity> posts) {
        return new UserEntity(
                user.getId().getValue(),
                user.getEmail(),
                user.getPassword(),
                user.getNickname(),
                user.getJoinedAt(),
                user.getIsDeleted(),
                posts

        );
    }

    public User fromEntityToDomain(UserEntity userEntity) {
        
        return new User(
                new UserId(userEntity.getId()),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getNickname(),
                userEntity.getJoinedAt(),
                userEntity.getIsDeleted()
        );
    }


}
