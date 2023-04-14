package com.example.board_hexagonal.attachedFile.adapter.out;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.post.adapter.out.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachedFileMapper {

    private final PostMapper postMapper;

    AttachedFileEntity mapToEntity(AttachedFile attachedFile){
        return null;
    }
}
