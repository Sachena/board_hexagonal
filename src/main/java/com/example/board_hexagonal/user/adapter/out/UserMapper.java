package com.example.board_hexagonal.user.adapter.out;


import com.example.board_hexagonal.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity mapToEntityWithoutId(User user){

        return new UserEntity(
                null,
                user.getEmail(),
                user.getPassword(),
                user.getNickname(),
                user.getJoinedAt(),
                user.getIsDeleted(),
                user.getPosts()
        );

    }

    public User mapToDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getNickname(),
                userEntity.getJoinedAt(),
                userEntity.getIsDeleted(),
                userEntity.getPosts()
        );
    }
}
