package com.example.board_hexagonal.comment.service;

import com.example.board_hexagonal.comment.application.port.in.DeleteCommentUsecase;
import com.example.board_hexagonal.comment.application.port.out.DeleteCommentPort;
import com.example.board_hexagonal.comment.application.port.out.RetrieveCommentPort;
import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.dto.DeleteCommentDTO;
import com.example.board_hexagonal.post.application.port.out.RetrievePostPort;
import com.example.board_hexagonal.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService implements DeleteCommentUsecase {

    private final RetrievePostPort retrievePostPort;
    private final RetrieveCommentPort retrieveCommentPort;
    private final DeleteCommentPort deleteCommentPort;

    @Override
    public void deleteComment(DeleteCommentDTO deleteCommentDTO) {
        Post post = retrievePostPort.retrievePost(deleteCommentDTO.getPostId());
        Comment deleteComment = retrieveCommentPort.retrieveComment(deleteCommentDTO.getId());
        deleteCommentPort.deleteComment(deleteComment, post);
    }
}
