package com.example.board_hexagonal.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class RetrieveUserDto {

    @NotEmpty
    private String email;


}
