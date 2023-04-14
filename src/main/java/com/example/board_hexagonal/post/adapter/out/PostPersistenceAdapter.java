package com.example.board_hexagonal.post.adapter.out;

import com.example.board_hexagonal.post.application.port.out.SavePostPort;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostPersistenceAdapter implements SavePostPort {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public void savePost(Post post) {
        postRepository.save(postMapper.mapToEntity(post));
    }
}
