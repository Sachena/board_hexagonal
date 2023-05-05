package com.example.board_hexagonal.user.service.command;

import com.example.board_hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditEmailCommand implements EditUserCommand {

    private final String email;
    private final User editUser;

    @Override
    public void execute() {
        editUser.editEmail(email);
    }
}
