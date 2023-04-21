package com.example.board_hexagonal;

import com.example.board_hexagonal.comment.application.port.in.CreateCommentUsecase;
import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.dto.CreateCommentDTO;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.comment.repository.CommentRepository;
import com.example.board_hexagonal.post.adapter.out.PostEntity;
import com.example.board_hexagonal.post.adapter.out.PostMapper;
import com.example.board_hexagonal.post.application.port.in.CreatePostUsecase;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.dto.CreatePostDto;
import com.example.board_hexagonal.post.dto.EditPostDTO;
import com.example.board_hexagonal.post.repository.PostRepository;
import com.example.board_hexagonal.user.adapter.out.UserMapper;
import com.example.board_hexagonal.user.application.port.in.CreateUserUsecase;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.dto.CreateUserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    private CreateUserUsecase createUserUsecase;

    @Autowired
    private CreatePostUsecase createPostUsecase;

    @Autowired
    private CreateCommentUsecase createCommentUsecase;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

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
    void 댓글생성(){
        // given
        PostEntity postEntity = postRepository.findByTitle("testTitle");
        Post editPost = postMapper.fromEntityToDomain(postEntity,userMapper.fromEntityToDomain(postEntity.getUser()));

        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setPostId(postEntity.getId());
        createCommentDTO.setDescription("commentTest");
        createCommentDTO.setAuthorNickname("commentTest");

        //when
        Comment newComment = createCommentUsecase.createComment(createCommentDTO);
        
        //then
        assertEquals(1,postEntity.getComments().size());

    }

    @Test
    void 댓글수정(){
        // given
        PostEntity postEntity = postRepository.findByTitle("testTitle");
        Post editPost = postMapper.fromEntityToDomain(postEntity,userMapper.fromEntityToDomain(postEntity.getUser()));


        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setPostId(postEntity.getId());
        createCommentDTO.setDescription("commentTest");
        createCommentDTO.setAuthorNickname("commentTest");

        //when
        Comment newComment = createCommentUsecase.createComment(createCommentDTO);

        //then
        assertEquals(1,postEntity.getComments().size());

    }

}