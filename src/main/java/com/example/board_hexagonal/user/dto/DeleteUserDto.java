package com.example.board_hexagonal.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DeleteUserDto {

    @NotEmpty
    private String email;

}
