package com.example.board_hexagonal.post.application.port.in;

import com.example.board_hexagonal.post.dto.EditPostDTO;

public interface EditPostUsecase {
    void editPost(EditPostDTO editPostDTO);
}
