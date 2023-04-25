package com.example.board_hexagonal.user.adapter.out;


import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

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
