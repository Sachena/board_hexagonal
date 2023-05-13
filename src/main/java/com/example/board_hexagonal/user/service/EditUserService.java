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

        //수정할 사용자 정보 validation check
        checkUserPort.checkEditUser(editUserDTO);

        //수정할 사용자 가져오기
        User editUser = retrieveUserPort.retrieveUser(editUserDTO.getEmail());

        //도메인 객체의 field들을 수정할 정보에 맞게 update
        editUserCommandFactory.createCommand(EditUserCommandCode.EMAIL,editUser, editUserDTO ).execute();
        editUserCommandFactory.createCommand(EditUserCommandCode.NICKNAME,editUser,editUserDTO).execute();
        
        //update한 도메인 객체를 DB에 반영
        saveUserPort.editUser(editUser);


        return editUser;
    }
}
