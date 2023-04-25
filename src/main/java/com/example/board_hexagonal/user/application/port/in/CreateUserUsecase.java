package com.example.board_hexagonal.user.application.port.in;


import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.CreateUserDTO;

//신규 사용자 생성을 위한 usecase
public interface CreateUserUsecase {

    User createUser(CreateUserDTO createUserDTO);

}
