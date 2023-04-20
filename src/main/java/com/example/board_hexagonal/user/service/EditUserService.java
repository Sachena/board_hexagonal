package com.example.board_hexagonal.user.service;


import com.example.board_hexagonal.user.application.port.in.EditUserUsecase;
import com.example.board_hexagonal.user.application.port.out.CheckUserPort;
import com.example.board_hexagonal.user.application.port.out.RetrieveUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.EditUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditUserService implements EditUserUsecase {

    private final CheckUserPort checkUserPort;
    private final RetrieveUserPort retrieveUserPort;

    @Override
    public User editUser(EditUserDTO editUserDTO) {

        checkUserPort.checkEditUser(editUserDTO);

        User editUser = retrieveUserPort.retrieveUser(editUserDTO.getEmail());

        editUser.editUser(editUserDTO);

        return editUser;
    }
}
