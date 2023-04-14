package com.example.board_hexagonal;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.repository.AttachedFileRepository;
import com.example.board_hexagonal.post.application.port.in.CreatePostUsecase;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.dto.CreatePostDto;
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



    }

    @Test
    void 게시글생성() {
        // given
        CreatePostDto createPostDto = new CreatePostDto();
        createPostDto.setEmail("test@naver.com");
        createPostDto.setTitle("testTitle");
        createPostDto.setDescription("asdasd");
        List<String> fileUrls = new ArrayList<>();
        fileUrls.add("test");
        createPostDto.setFileUrls(fileUrls);

        // when
        Post newPost = createPostUsecase.createPost(createPostDto);

        // then
        assertEquals(newPost, postRepository.findByTitle(createPostDto.getTitle()));
//        newPost.getAttachedFiles().forEach(attachedFile -> {
//            AttachedFile retrieveFile = attachedFileRepository.findByUrl(attachedFile.getUrl());
//            assertEquals(attachedFile.getUrl(), retrieveFile.getUrl());
//        });

    }
//
//    @Test
//    void 게시글수정(){
//        // given
//        CreatePostDto createPostDto = new CreatePostDto();
//        createPostDto.setNickname("test");
//        createPostDto.setTitle("testTitle");
//        createPostDto.setDescription("asdasd");
//
//        List<String> fileUrls = new ArrayList<>();
//        fileUrls.add("test");
//        createPostDto.setFileUrls(fileUrls);
//        Post newPost = postUseCase.createPost(createPostDto);
//
//        //기존 파일 수정
//        EditPostDTO editPostDTO = new EditPostDTO();
//        editPostDTO.setId(newPost.getId());
//        editPostDTO.setTitle("editTest");
//        editPostDTO.setDescription("editTest");
//
//        List<String> editUrl = new ArrayList<>();
//        fileUrls.add("editFile");
//        editPostDTO.setFileUrls(editUrl);
//
//        //when
//        postUseCase.editPost(editPostDTO);
//
//        //then
//        Post editPost = postRepository.findByTitle(editPostDTO.getTitle());
//        assertEquals(editPostDTO.getTitle(), editPost.getTitle());
//
//        editPost.getAttachedFiles().forEach( attachedFile -> {
//            AttachedFile retrieveFile = attachedFileRepository.findByUrl(attachedFile.getUrl());
//            assertEquals(attachedFile.getUrl(), retrieveFile.getUrl());
//        });
//
//    }
//
//    @Test
//    void 게시글삭제(){
//        // given
//        CreatePostDto createPostDto = new CreatePostDto();
//        createPostDto.setNickname("test");
//        createPostDto.setTitle("deleteTitle");
//        createPostDto.setDescription("delete");
//
//        List<String> fileUrls = new ArrayList<>();
//        fileUrls.add("delete");
//        createPostDto.setFileUrls(fileUrls);
//        Post newPost = postUseCase.createPost(createPostDto);
//
//        //when
//        postUseCase.deletePost(newPost.getId());
//
//
//        // then
//        Optional<Post> retrievePost = postRepository.findById(newPost.getId());
//
//        assertEquals(retrievePost.isEmpty(),true);
//
//    }

}
