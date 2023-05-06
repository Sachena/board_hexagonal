package com.example.board_hexagonal.user.adapter.out;


import com.example.board_hexagonal.exception.DuplicateException;
import com.example.board_hexagonal.exception.InvalidUserException;
import com.example.board_hexagonal.user.application.port.out.CheckNewUserPort;
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
public class UserPersistenceAdapter implements RetrieveUserPort, SaveUserPort, CheckNewUserPort, CheckUserPort {


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
    public void checkNewUser(CreateUserDTO createUserDTO) {
        //Check Value
        UserEntity checkEmailUser = userRepository.findByEmail( createUserDTO.getEmail());
        if(!isUserNull(checkEmailUser)){
            throw new DuplicateException("이미 가입된 회원입니다.");
        }

        if(isNicknameDuplicate(createUserDTO.getNickname())){
            throw new DuplicateException("중복된 닉네임 입니다.");
        }
    }


    @Override
    public void checkEditUser(EditUserDTO editUserDTO) {
        //Check Value
        UserEntity editUser = userRepository.findByEmail(editUserDTO.getEmail());
        if(editUser == null){
            throw new InvalidUserException("올바른 사용자가 아닙니다.");
        }

        if(userRepository.existsByNickname( editUserDTO.getNickname())){
            throw new DuplicateException("중복된 닉네임 입니다.");
        }

    }

    @Override
    public void checkDeleteUser(DeleteUserDto deleteUserDto) {
        //Check Value
        UserEntity deleteUser = userRepository.findByEmail(deleteUserDto.getEmail());
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
