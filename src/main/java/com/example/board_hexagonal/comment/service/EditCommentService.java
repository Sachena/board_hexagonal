package com.example.board_hexagonal.comment.service;

import com.example.board_hexagonal.comment.application.port.in.EditCommentUsecase;
import com.example.board_hexagonal.comment.application.port.out.RetrieveCommentPort;
import com.example.board_hexagonal.comment.application.port.out.SaveCommentPort;
import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.dto.EditCommentDTO;
import com.example.board_hexagonal.post.application.port.out.RetrievePostPort;
import com.example.board_hexagonal.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditCommentService implements EditCommentUsecase {

    private final RetrievePostPort retrievePostPort;
    private final SaveCommentPort saveCommentPort;

    private final RetrieveCommentPort retrieveCommentPort;

    @Override
    public Comment editComment(EditCommentDTO editCommentDTO) {
        Post editPost = retrievePostPort.retrievePost(editCommentDTO.getPostId());

        Comment editComment = retrieveCommentPort.retrieveComment(editCommentDTO.getId());
        editComment = editComment.editComment(editCommentDTO);

        editComment = saveCommentPort.updateComment(editComment, editPost);
        return editComment;
    }
}
