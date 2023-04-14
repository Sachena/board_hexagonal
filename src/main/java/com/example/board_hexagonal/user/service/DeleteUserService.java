package com.example.board_hexagonal.user.service;


import com.example.board_hexagonal.user.application.port.in.DeleteUserUsecase;
import com.example.board_hexagonal.user.application.port.out.CheckUserPort;
import com.example.board_hexagonal.user.application.port.out.LoadUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.DeleteUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteUserService implements DeleteUserUsecase {
    private final CheckUserPort checkUserPort;
    private final LoadUserPort loadUserPort;

    @Override
    public User deleteUser(DeleteUserDto deleteUserDto) {
        checkUserPort.checkDeleteUser(deleteUserDto);
        User deleteUser = loadUserPort.loadUser(deleteUserDto.getEmail());

        deleteUser.deleteUser(deleteUserDto);
        return deleteUser;
    }
}
