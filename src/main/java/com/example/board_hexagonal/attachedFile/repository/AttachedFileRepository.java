package com.example.board_hexagonal.attachedFile.repository;


import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachedFileRepository extends JpaRepository<AttachedFileEntity, Long> {

    AttachedFileEntity findByUrl(String url);

}
