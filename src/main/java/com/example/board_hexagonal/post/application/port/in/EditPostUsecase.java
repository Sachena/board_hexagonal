package com.example.board_hexagonal.post.application.port.in;

import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.dto.EditPostDTO;

public interface EditPostUsecase {
    Post editPost(EditPostDTO editPostDTO);
}
