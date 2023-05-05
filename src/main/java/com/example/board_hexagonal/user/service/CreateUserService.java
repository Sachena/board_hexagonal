package com.example.board_hexagonal.user.service;


import com.example.board_hexagonal.user.application.port.in.CreateUserUsecase;
import com.example.board_hexagonal.user.application.port.out.CheckNewUserPort;
import com.example.board_hexagonal.user.application.port.out.SaveUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//신규 사용자 생성 처리를 위한 서비스
@RequiredArgsConstructor
@Service
public class CreateUserService implements CreateUserUsecase {

    private final CheckNewUserPort checkNewUserPort;
    private final SaveUserPort saveUserPort;

    @Override
    public User createUser(CreateUserDTO createUserDTO) {

        //신규 사용자 데이터 유효성 체크
        checkNewUserPort.checkNewUser(createUserDTO);

        User newUser = new User();

        newUser = newUser.createUser(createUserDTO);

        saveUserPort.createUser(newUser);

        return newUser;
    }
}
