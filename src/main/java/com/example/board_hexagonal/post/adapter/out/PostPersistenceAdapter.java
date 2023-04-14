package com.example.board_hexagonal.post.adapter.out;

import com.example.board_hexagonal.attachedFile.adapter.out.AttachedFileMapper;
import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.attachedFile.repository.AttachedFileRepository;
import com.example.board_hexagonal.post.application.port.out.SavePostPort;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PostPersistenceAdapter implements SavePostPort {

    private final PostRepository postRepository;
    private final AttachedFileRepository attachedFileRepository;

    private final PostMapper postMapper;
    private final AttachedFileMapper attachedFileMapper;

    @Override
    public void savePost(Post post) {

        PostEntity postEntity = postMapper.mapToEntity(post);
        List<AttachedFileEntity> attachedFileEntityList = new ArrayList<>();
        for (AttachedFile attachedFile : post.getAttachedFiles()) {
            AttachedFileEntity attachedFileEntity = attachedFileMapper.mapToEntity(attachedFile);
            attachedFileEntity.addPost(postEntity);

            attachedFileEntity = attachedFileRepository.save(attachedFileEntity);
            attachedFileEntityList.add(attachedFileEntity);
        }

        postEntity.addAttachedFileList(attachedFileEntityList);
        postRepository.save(postEntity);

    }
}
