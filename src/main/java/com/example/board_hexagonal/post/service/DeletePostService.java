package com.example.board_hexagonal.post.service;

import com.example.board_hexagonal.post.application.port.in.DeletePostUsecase;
import com.example.board_hexagonal.post.application.port.out.DeletePostPort;
import com.example.board_hexagonal.post.application.port.out.RetrievePostPort;
import com.example.board_hexagonal.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostService implements DeletePostUsecase {

    private final RetrievePostPort retrievePostPort;
    private final DeletePostPort deletePostPort;

    @Override
    public void deletePost(Long postId) {
        Post deletePost = retrievePostPort.retrievePost(postId);
        deletePostPort.deletePost(deletePost.getId());

    }
}
