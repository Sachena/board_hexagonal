package com.example.board_hexagonal;

import com.example.board_hexagonal.exception.DuplicateException;
import com.example.board_hexagonal.exception.InvalidUserException;
import com.example.board_hexagonal.user.adapter.out.UserMapper;
import com.example.board_hexagonal.user.application.port.in.CreateUserUsecase;
import com.example.board_hexagonal.user.application.port.in.DeleteUserUsecase;
import com.example.board_hexagonal.user.application.port.in.EditUserUsecase;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import com.example.board_hexagonal.user.dto.DeleteUserDto;
import com.example.board_hexagonal.user.dto.EditUserDTO;
import com.example.board_hexagonal.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private EditUserUsecase editUserUsecase;

    @Autowired
    private CreateUserUsecase createUserUsecase;

    @Autowired
    private DeleteUserUsecase deleteUserUsecase;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @BeforeEach
    void 테스트회원생성(){

        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("test@naver.com");
        createUserDTO.setNickname("test");
        createUserDTO.setPassword("test");
        User newUser1 = createUserUsecase.createUser(createUserDTO);
    }


    @Test
    void 회원가입() {

        // given
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("1217smj@naver.com");
        createUserDTO.setNickname("sachena");
        createUserDTO.setPassword("1234");

        // when
        User newUser = createUserUsecase.createUser(createUserDTO);

        userRepository.findAll().forEach(userEntity -> {
            System.out.println(userEntity.getId());
            System.out.println(userEntity.getEmail());
        });

        // then
        assertNotNull(userRepository.findByEmail(newUser.getEmail().getValue()));

    }

    @Test
    void 회원가입_이메일중복() {


        // given


        // when
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("test@naver.com");
        createUserDTO.setNickname("test");
        createUserDTO.setPassword("test");


        // then
        assertThrows(DuplicateException.class, ()->{
                    createUserUsecase.createUser(createUserDTO);
        }
        );

    }

    @Test
    void 회원가입_닉네임중복() {

        // given

        // when
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("test2@naver.com");
        createUserDTO.setNickname("test");
        createUserDTO.setPassword("test");


        System.out.println("userRepository = " + userRepository.findAll());

        // then
        assertThrows(DuplicateException.class, ()->{
            createUserUsecase.createUser(createUserDTO);
                }
        );

    }


    @Test
    void 회원정보수정() {

        // given
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setEmail("test@naver.com");
        editUserDTO.setNickname("edit");



        // when
        User editUser = editUserUsecase.editUser(editUserDTO);

        // then
        assertEquals("edit", editUser.getNickname().getValue());
        assertEquals("test@naver.com",editUser.getEmail().getValue());

    }

    @Test
    void 회원정보수정_이메일오류() {

        // given
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setEmail("asd@naver.com");
        editUserDTO.setNickname("test");



        // when


        // then
        assertThrows(InvalidUserException.class, ()->{
            editUserUsecase.editUser(editUserDTO);
                }
        );

    }

    @Test
    void 회원정보수정_닉네임중복() {

        // given
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setEmail("test@naver.com");
        editUserDTO.setNickname("test");



        // when


        // then
        assertThrows(DuplicateException.class, ()->{
            editUserUsecase.editUser(editUserDTO);
                }
        );

    }

    @Test
    void 회원탈퇴() {

        // given

        // when
        DeleteUserDto deleteUserDto = new DeleteUserDto();
        deleteUserDto.setEmail("test@naver.com");
        User testUser = deleteUserUsecase.deleteUser(deleteUserDto);


        // then
        assertEquals(testUser.getIsDeleted().getValue(), true);

    }

    @Test
    void 회원탈퇴_유효하지않은이메일() {

        // given
        DeleteUserDto deleteUserDto = new DeleteUserDto();
        deleteUserDto.setEmail("asdasd");



        // when



        // then
        assertThrows(InvalidUserException.class, ()->{
            deleteUserUsecase.deleteUser(deleteUserDto);
                }
        );

    }



}