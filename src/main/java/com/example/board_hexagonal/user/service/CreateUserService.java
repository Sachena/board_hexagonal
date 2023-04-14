package com.example.board_hexagonal.user.service;


import com.example.board_hexagonal.user.application.port.in.CreateUserUsecase;
import com.example.board_hexagonal.user.application.port.out.CheckNewUserPort;
import com.example.board_hexagonal.user.application.port.out.SaveUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateUserService implements CreateUserUsecase {

    private final CheckNewUserPort checkNewUserPort;
    private final SaveUserPort saveUserPort;

    @Override
    public User createUser(CreateUserDTO createUserDTO) {

        checkNewUserPort.checkNewUser(createUserDTO);

        User newUser = new User();

        newUser = newUser.createUser(createUserDTO);

        saveUserPort.saveUser(newUser);

        return newUser;
    }
}
