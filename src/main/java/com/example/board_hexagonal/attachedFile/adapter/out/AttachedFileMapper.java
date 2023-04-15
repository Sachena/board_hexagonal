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
    private final PostMapper postMapper;

    public AttachedFileEntity mapToEntity(AttachedFile attachedFile){
        return new AttachedFileEntity(
                null,
                attachedFile.getUrl(),
                null
        );
    }

    public List<AttachedFile> mapToDomainList(List<AttachedFileEntity> attachedFileEntityList) {

        List<AttachedFile> attachedFileList = new ArrayList<>();
        for (AttachedFileEntity attachedFileEntity : attachedFileEntityList) {
            AttachedFile attachedFileDomain = new AttachedFile(
                    attachedFileEntity.getId(),
                    attachedFileEntity.getUrl(),
                    postMapper.mapToDomain(attachedFileEntity.getPost())
            );
            attachedFileList.add(attachedFileDomain);
        }
        return attachedFileList;

    }
}
