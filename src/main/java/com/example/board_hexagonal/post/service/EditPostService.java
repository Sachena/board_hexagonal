package com.example.board_hexagonal.post.service;

import com.example.board_hexagonal.post.application.port.in.EditPostUsecase;
import com.example.board_hexagonal.post.application.port.out.RetrievePostPort;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.dto.EditPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditPostService implements EditPostUsecase {


    private final RetrievePostPort retrievePostPort;

    @Override
    public void editPost(EditPostDTO editPostDTO) {
        Post editPost = retrievePostPort.retrievePost(editPostDTO.getId());
        System.out.println(editPost.getId());


    }
}
