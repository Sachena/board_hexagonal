package com.example.board_hexagonal.user.service;


import com.example.board_hexagonal.user.application.port.in.CreateUserUsecase;
import com.example.board_hexagonal.user.application.port.out.CheckUserPort;
import com.example.board_hexagonal.user.application.port.out.SaveUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//신규 사용자 생성 처리를 위한 서비스
@RequiredArgsConstructor
@Service
public class CreateUserService implements CreateUserUsecase {

    private final CheckUserPort checkUserPort;
    private final SaveUserPort saveUserPort;

    @Override
    public User createUser(CreateUserDTO createUserDTO) {

        //신규 사용자 도메인 객체 생성
        User newUser = new User();
        newUser = newUser.createUser(createUserDTO);

        //신규 사용자 데이터 validation check
        checkUserPort.checkNewUser(newUser);

        //도메인 객체를 DB에 저장
        saveUserPort.createUser(newUser);

        return newUser;
    }
}
