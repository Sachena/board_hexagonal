package com.example.board_hexagonal.comment.repository;


import com.example.board_hexagonal.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
