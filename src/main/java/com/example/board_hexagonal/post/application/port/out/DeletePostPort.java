package com.example.board_hexagonal.post.application.port.out;

import com.example.board_hexagonal.post.domain.Post;

public interface DeletePostPort {

    void deletePost(Post deletePost);

}
