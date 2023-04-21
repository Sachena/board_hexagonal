package com.example.board_hexagonal.post.service;

import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.post.application.port.in.EditPostUsecase;
import com.example.board_hexagonal.post.application.port.out.RetrievePostPort;
import com.example.board_hexagonal.post.application.port.out.SavePostPort;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.dto.EditPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditPostService implements EditPostUsecase {

    private final RetrievePostPort retrievePostPort;
    private final SavePostPort savePostPort;

    @Override
    public Post editPost(EditPostDTO editPostDTO) {

        Post editPost = retrievePostPort.retrievePost(editPostDTO.getId());
        List<AttachedFile> attachedFileList = new ArrayList<>();
        for (String fileUrl : editPostDTO.getFileUrls()) {
            attachedFileList.add(new AttachedFile(null,fileUrl));
        }

        editPost = editPost.editPost(editPostDTO.getId(), editPostDTO.getTitle(), editPostDTO.getDescription(), attachedFileList );
        savePostPort.updatePost(editPost);

        return editPost;


    }
}
