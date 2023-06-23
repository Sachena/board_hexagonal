package com.example.board_hexagonal.user.adapter.out;


import com.example.board_hexagonal.exception.DuplicateException;
import com.example.board_hexagonal.exception.InvalidUserException;
import com.example.board_hexagonal.user.application.port.out.CheckUserPort;
import com.example.board_hexagonal.user.application.port.out.RetrieveUserPort;
import com.example.board_hexagonal.user.application.port.out.SaveUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import com.example.board_hexagonal.user.dto.DeleteUserDto;
import com.example.board_hexagonal.user.dto.EditUserDTO;
import com.example.board_hexagonal.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements RetrieveUserPort, SaveUserPort, CheckUserPort {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User retrieveUser(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);
        User user = userMapper.fromEntityToDomain(userEntity);
        return user;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(userMapper.fromDomainToEntityWithoutId(user));
    }

    @Override
    public void editUser(User user) {
        UserEntity originEntity = userRepository.findByEmail(user.getEmail().getValue());
        userRepository.save(userMapper.fromDomainToEntityWithId(user,originEntity.getPosts()));
    }

    @Override
    public void checkNewUser(User newUser) {
        //Check Value
        UserEntity checkEmailUser = userRepository.findByEmail( newUser.getEmail().getValue() );
        if(!isUserNull(checkEmailUser)){
            throw new DuplicateException("이미 가입된 회원입니다.");
        }

        if(isNicknameDuplicate(newUser.getNickname().getValue())){
            throw new DuplicateException("중복된 닉네임 입니다.");
        }
    }


    @Override
    public void checkEditUser(User editUser) {
        //Check Value
        UserEntity editUserEntity = userRepository.findByEmail(editUser.getEmail().getValue());
        if(editUserEntity == null){
            throw new InvalidUserException("올바른 사용자가 아닙니다.");
        }

        if(userRepository.existsByNickname( editUser.getNickname().getValue())){
            throw new DuplicateException("중복된 닉네임 입니다.");
        }

    }

    @Override
    public void checkDeleteUser(User deleteUser) {
        //Check Value
        UserEntity deleteUserEntity = userRepository.findByEmail(deleteUser.getEmail().getValue());
        if(deleteUser == null){
            throw new InvalidUserException("올바른 사용자가 아닙니다.");
        }

    }

    private boolean isUserNull(UserEntity userEntity){
        return userEntity == null ? true : false;
    }

    private boolean isNicknameDuplicate(String nickName){
        return userRepository.existsByNickname(nickName);
    }
}
