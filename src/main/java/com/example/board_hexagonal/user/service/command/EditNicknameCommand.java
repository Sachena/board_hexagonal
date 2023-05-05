package com.example.board_hexagonal.user.service.command;

import com.example.board_hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditNicknameCommand implements EditUserCommand {

    private final String nickname;
    private final User editUser;

    @Override
    public void execute() {
        editUser.editNickname(nickname);

    }
}
