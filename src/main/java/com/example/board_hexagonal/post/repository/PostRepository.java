package com.example.board_hexagonal.post.repository;


import com.example.board_hexagonal.post.adapter.out.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findByTitle(String title);
}
