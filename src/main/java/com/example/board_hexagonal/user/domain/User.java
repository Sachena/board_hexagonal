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

    private String email;

    private String password;

    private String nickname;

    private LocalDateTime joinedAt;

    private Boolean isDeleted;


    public User createUser(CreateUserDTO createUserDTO){
        return new User(null,createUserDTO.getEmail(),createUserDTO.getPassword(),createUserDTO.getNickname(),LocalDateTime.now(),false);

    }

    public void editEmail(String email) {
        this.email = email;
    }
    public void editNickname(String nickname) {
        this.nickname = nickname;
    }

    public void deleteUser(DeleteUserDto deleteUserDto) {
        this.isDeleted = true;
    }



}
