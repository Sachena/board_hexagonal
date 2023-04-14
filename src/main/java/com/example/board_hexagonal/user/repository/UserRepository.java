package com.example.board_hexagonal.user.repository;


import com.example.board_hexagonal.user.adapter.out.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);

    boolean existsByNickname(String nickname);

    UserEntity findByNickname(String nickname);
}
