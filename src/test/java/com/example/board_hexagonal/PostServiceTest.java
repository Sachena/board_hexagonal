package com.example.board_hexagonal;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.repository.AttachedFileRepository;
import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.application.port.in.CreatePostUsecase;
import com.example.board_hexagonal.post.application.port.in.DeletePostUsecase;
import com.example.board_hexagonal.post.application.port.in.EditPostUsecase;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.dto.CreatePostDto;
import com.example.board_hexagonal.post.dto.EditPostDTO;
import com.example.board_hexagonal.post.repository.PostRepository;
import com.example.board_hexagonal.user.application.port.in.CreateUserUsecase;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import com.example.board_hexagonal.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private CreateUserUsecase createUserUsecase;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreatePostUsecase createPostUsecase;

    @Autowired
    private EditPostUsecase editPostUsecase;

    @Autowired
    private DeletePostUsecase deletePostUsecase;


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AttachedFileRepository attachedFileRepository;

    @BeforeEach
    void 게시글테스트데이터(){

        //테스트용 회원 생성
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setEmail("test@naver.com");
        createUserDTO.setNickname("test");
        createUserDTO.setPassword("test");
        User author = createUserUsecase.createUser(createUserDTO);
        CreatePostDto createPostDto = new CreatePostDto();
        createPostDto.setEmail("test@naver.com");
        createPostDto.setTitle("testTitle");
        createPostDto.setDescription("asdasd");

        List<String> fileUrls = new ArrayList<>();
        fileUrls.add("test");
        createPostDto.setFileUrls(fileUrls);
        Post newPost = createPostUsecase.createPost(createPostDto);

    }

    @Test
    void 게시글생성() {
        // given
        CreatePostDto createPostDto = new CreatePostDto();
        createPostDto.setEmail("test@naver.com");
        createPostDto.setTitle("newTestTitle");
        createPostDto.setDescription("asdasdasdasda");
        List<String> fileUrls = new ArrayList<>();
        fileUrls.add("newTest");
        createPostDto.setFileUrls(fileUrls);

        // when
        Post newPost = createPostUsecase.createPost(createPostDto);

        // then
        assertEquals(newPost.getTitle().getValue(), postRepository.findByTitle(createPostDto.getTitle()).getTitle()  );


    }

    @Test
    void 게시글수정(){
        // given
        PostEntity postEntity = postRepository.findByTitle("testTitle");


        //기존 파일 수정
        EditPostDTO editPostDTO = new EditPostDTO();
        editPostDTO.setId(postEntity.getId());
        editPostDTO.setTitle("editTest");
        editPostDTO.setDescription("editTest");

        List<String> editUrl = new ArrayList<>();
        editUrl.add("editFile");
        editPostDTO.setFileUrls(editUrl);

        //when
        Post editPost = editPostUsecase.editPost(editPostDTO);
        
        //then
        assertEquals(editPostDTO.getTitle(), editPost.getTitle().getValue());
        editPost.getAttachedFiles().forEach(attachedFile -> {
            System.out.println("attachedFile.getUrl() = " + attachedFile.getUrl());
        });



    }

    @Test
    void 게시글삭제(){
        // given
        CreatePostDto createPostDto = new CreatePostDto();
        createPostDto.setEmail("test@naver.com");
        createPostDto.setTitle("deleteTest");
        createPostDto.setDescription("deleteTest");
        List<String> fileUrls = new ArrayList<>();
        fileUrls.add("deleteTest");
        createPostDto.setFileUrls(fileUrls);
        Post newPost = createPostUsecase.createPost(createPostDto);
        PostEntity postEntity = postRepository.findByTitle(createPostDto.getTitle());

        // when

        deletePostUsecase.deletePost(postEntity.getId());

        // then
        assertNull(postRepository.findByTitle("deleteTest"));

    }

}
