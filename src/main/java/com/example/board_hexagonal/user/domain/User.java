package com.example.board_hexagonal.user.domain;


import com.example.board_hexagonal.post.adapter.out.PostEntity;
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

    private Long id;

    private String email;

    private String password;

    private String nickname;

    private LocalDateTime joinedAt;

    private Boolean isDeleted;

    private List<PostEntity> posts;



    //setter업애기 위해?
    public User createUser(CreateUserDTO createUserDTO){
        return new User(null,createUserDTO.getEmail(),createUserDTO.getPassword(),createUserDTO.getNickname(),LocalDateTime.now(),false,new ArrayList<>());

    }

    public void editUser(EditUserDTO editUserDTO) {
        this.nickname = editUserDTO.getNickname();
    }

    public void deleteUser(DeleteUserDto deleteUserDto) {
        this.isDeleted = true;
    }


}
