package com.example.board_hexagonal.user.service;


import com.example.board_hexagonal.user.application.port.in.DeleteUserUsecase;
import com.example.board_hexagonal.user.application.port.out.CheckUserPort;
import com.example.board_hexagonal.user.application.port.out.RetrieveUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.DeleteUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteUserService implements DeleteUserUsecase {
    private final CheckUserPort checkUserPort;
    private final RetrieveUserPort retrieveUserPort;

    @Override
    public User deleteUser(DeleteUserDto deleteUserDto) {
        //삭제할 사용자 도메인 객체 가져오기
        User deleteUser = retrieveUserPort.retrieveUser(deleteUserDto.getEmail());

        //삭제할 사용자 정보 validation check
        checkUserPort.checkDeleteUser(deleteUser);

        //도메인 객체 DB에 반영
        deleteUser.deleteUser();
        return deleteUser;
    }
}
