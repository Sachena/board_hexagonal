package com.example.board_hexagonal.user.adapter.out;


import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.repository.PostRepository;
import com.example.board_hexagonal.user.domain.*;
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
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getNickname().getValue(),
                user.getJoinedAt().getValue(),
                user.getIsDeleted().getValue(),
                null
        );

    }

    public UserEntity fromDomainToEntityWithId(User user, List<PostEntity> posts) {
        return new UserEntity(
                user.getId().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getNickname().getValue(),
                user.getJoinedAt().getValue(),
                user.getIsDeleted().getValue(),
                posts

        );
    }

    public User fromEntityToDomain(UserEntity userEntity) {
        
        return new User(
                new UserId(userEntity.getId()),
                new Email(userEntity.getEmail()),
                new Password(userEntity.getPassword()),
                new Nickname(userEntity.getNickname()),
                new UserJoinedAt(userEntity.getJoinedAt()),
                new Isdeleted(userEntity.getIsDeleted())
        );
    }


}
