package com.example.board_hexagonal.post.application.port.out;

import com.example.board_hexagonal.post.domain.Post;

public interface SavePostPort {
    void createPost(Post post);

    void updatePost(Post post);
}
