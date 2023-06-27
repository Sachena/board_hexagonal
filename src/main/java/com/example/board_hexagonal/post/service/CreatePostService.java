package com.example.board_hexagonal.post.service;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.domain.Url;
import com.example.board_hexagonal.post.application.port.in.CreatePostUsecase;
import com.example.board_hexagonal.post.application.port.out.SavePostPort;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.dto.CreatePostDto;
import com.example.board_hexagonal.user.application.port.out.RetrieveUserPort;
import com.example.board_hexagonal.user.domain.User;
import com.example.board_hexagonal.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatePostService implements CreatePostUsecase {

    private final RetrieveUserPort retrieveUserPort;

    private final SavePostPort savePostPort;

    @Override
    public Post createPost(CreatePostDto createPostDto) {
        User author = retrieveUserPort.retrieveUser(createPostDto.getEmail());

        Post newPost = new Post();

        newPost = newPost.createPost(author, createPostDto);
        savePostPort.createPost(newPost);

        return newPost;
    }

}
