package com.example.board_hexagonal.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserDTO {


    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nickname;

}
