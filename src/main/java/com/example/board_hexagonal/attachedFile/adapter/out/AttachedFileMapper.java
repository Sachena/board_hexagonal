package com.example.board_hexagonal.attachedFile.adapter.out;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.post.adapter.out.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AttachedFileMapper {

    public AttachedFileEntity mapToEntity(AttachedFile attachedFile){
        return new AttachedFileEntity(
                null,
                attachedFile.getUrl(),
                null
        );
    }

    public List<AttachedFile> fromEntityListToDomainList(List<AttachedFileEntity> attachedFileEntityList) {

        List<AttachedFile> attachedFileList = new ArrayList<>();

        attachedFileEntityList.forEach(attachedFileEntity -> {
            attachedFileList.add(new AttachedFile(
                    attachedFileEntity.getId(),
                    attachedFileEntity.getUrl()
            ));
        });

        return attachedFileList;

    }
}
