package com.example.board_hexagonal.user.application.port.out;


import com.example.board_hexagonal.user.domain.User;

public interface SaveUserPort {
    void createUser(User user);
    void editUser(User user);
}
