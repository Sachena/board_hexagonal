package com.example.board_hexagonal.user.domain;


import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import com.example.board_hexagonal.user.dto.DeleteUserDto;
import com.example.board_hexagonal.user.dto.EditUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UserId id;

    private Email email;

    private Password password;

    private Nickname nickname;

    private UserJoinedAt joinedAt;

    private Isdeleted isDeleted;


    public User createUser(CreateUserDTO createUserDTO){
        return new User(null,new Email(createUserDTO.getEmail()),new Password(createUserDTO.getPassword()),new Nickname(createUserDTO.getNickname()),new UserJoinedAt(LocalDateTime.now()),new Isdeleted(false));

    }

    public void editEmail(String email) {
        this.email = new Email(email);
    }
    public void editNickname(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    public void deleteUser() {
        this.isDeleted = new Isdeleted(true);
    }



}
