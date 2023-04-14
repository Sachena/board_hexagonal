package com.example.board_hexagonal.user.application.port.out;


import com.example.board_hexagonal.user.dto.CreateUserDTO;

public interface CheckNewUserPort {
    void checkNewUser(CreateUserDTO createUserDTO);
}
