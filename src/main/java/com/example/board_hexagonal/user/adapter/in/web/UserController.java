package com.example.board_hexagonal.user.adapter.in.web;

import com.example.board_hexagonal.user.application.port.in.CreateUserUsecase;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUsecase createUserUsecase;

    @PostMapping("/user")
    public void createUser(CreateUserDTO createUserDTO){

        createUserUsecase.createUser(createUserDTO);

    }

}
