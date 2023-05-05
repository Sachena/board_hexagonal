package com.example.board_hexagonal.user.service.command;

import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.EditUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditUserCommandFactory {

    public EditUserCommand createCommand(EditUserCommandCode userCommandCode, User editUser, EditUserDTO editUserDTO){
        switch (userCommandCode){
            case EMAIL:
                return new EditEmailCommand(editUserDTO.getEmail(), editUser);
            case NICKNAME:
                return new EditNicknameCommand(editUserDTO.getNickname(), editUser);
            default:
                return null;
        }
    }

}
