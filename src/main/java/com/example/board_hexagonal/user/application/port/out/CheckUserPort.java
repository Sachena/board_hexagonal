package com.example.board_hexagonal.user.application.port.out;

import com.example.board_hexagonal.user.dto.CreateUserDTO;
import com.example.board_hexagonal.user.dto.DeleteUserDto;
import com.example.board_hexagonal.user.dto.EditUserDTO;

public interface CheckUserPort {
    void checkNewUser(CreateUserDTO createUserDTO);
    void checkEditUser(EditUserDTO editUserDTO);
    void checkDeleteUser(DeleteUserDto deleteUserDto);
}
