package com.example.board_hexagonal.user.application.port.in;


import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.DeleteUserDto;

public interface DeleteUserUsecase {
    User deleteUser(DeleteUserDto deleteUserDto);
}
