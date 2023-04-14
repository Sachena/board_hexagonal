package com.example.board_hexagonal.user.application.port.in;


import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.EditUserDTO;

public interface EditUserUsecase {

    User editUser(EditUserDTO editUserDTO);

}
