package com.example.board_hexagonal.user.service;


import com.example.board_hexagonal.user.application.port.in.EditUserUsecase;
import com.example.board_hexagonal.user.application.port.out.CheckUserPort;
import com.example.board_hexagonal.user.application.port.out.RetrieveUserPort;
import com.example.board_hexagonal.user.application.port.out.SaveUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.EditUserDTO;
import com.example.board_hexagonal.user.service.command.EditUserCommandCode;
import com.example.board_hexagonal.user.service.command.EditUserCommandFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditUserService implements EditUserUsecase {

    private final CheckUserPort checkUserPort;
    private final SaveUserPort saveUserPort;
    private final RetrieveUserPort retrieveUserPort;
    private final EditUserCommandFactory editUserCommandFactory;

    @Override
    public User editUser(EditUserDTO editUserDTO) {

        checkUserPort.checkEditUser(editUserDTO);

        User editUser = retrieveUserPort.retrieveUser(editUserDTO.getEmail());

        editUserCommandFactory.createCommand(EditUserCommandCode.EMAIL,editUser, editUserDTO ).execute();
        editUserCommandFactory.createCommand(EditUserCommandCode.NICKNAME,editUser,editUserDTO).execute();
        saveUserPort.editUser(editUser);


        return editUser;
    }
}
