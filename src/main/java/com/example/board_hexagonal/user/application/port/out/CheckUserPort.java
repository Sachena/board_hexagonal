package com.example.board_hexagonal.user.application.port.out;

import com.example.board_hexagonal.user.dto.DeleteUserDto;
import com.example.board_hexagonal.user.dto.EditUserDTO;
import com.example.board_hexagonal.user.domain.User;

public interface CheckUserPort {
    void checkNewUser(User newUser);
    void checkEditUser(User editUser);
    void checkDeleteUser(User deleteUser);
}
