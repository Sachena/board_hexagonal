package com.example.board_hexagonal.post.adapter.out;

import com.example.board_hexagonal.attachedFile.adapter.out.AttachedFileMapper;
import com.example.board_hexagonal.attachedFile.domain.AttachedFile;
import com.example.board_hexagonal.attachedFile.entity.AttachedFileEntity;
import com.example.board_hexagonal.attachedFile.repository.AttachedFileRepository;
import com.example.board_hexagonal.comment.adapter.out.CommentMapper;
import com.example.board_hexagonal.comment.domain.Comment;
import com.example.board_hexagonal.comment.entity.CommentEntity;
import com.example.board_hexagonal.comment.repository.CommentRepository;
import com.example.board_hexagonal.post.application.port.out.DeletePostPort;
import com.example.board_hexagonal.post.application.port.out.RetrievePostPort;
import com.example.board_hexagonal.post.application.port.out.SavePostPort;
import com.example.board_hexagonal.post.domain.Post;
import com.example.board_hexagonal.post.repository.PostRepository;
import com.example.board_hexagonal.user.adapter.out.UserEntity;
import com.example.board_hexagonal.user.adapter.out.UserMapper;
import com.example.board_hexagonal.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PostPersistenceAdapter implements SavePostPort, RetrievePostPort, DeletePostPort {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AttachedFileRepository attachedFileRepository;

    private final CommentRepository commentRepository;

    private final PostMapper postMapper;
    private final UserMapper userMapper;

    private final CommentMapper commentMapper;
    private final AttachedFileMapper attachedFileMapper;

    @Override
    public void createPost(Post post) {

        UserEntity userEntity = userRepository.findById(post.getUserId().getValue()).orElse(null);

        PostEntity postEntity = postMapper.fromDomainToEntityWithoutId(post, userEntity);

        postEntity.addUser(userEntity);

        List<CommentEntity> commentEntityList = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            CommentEntity commentEntity = commentMapper.fromDomainToEntityWithId(comment, userEntity.getNickname() ,postEntity);
            commentEntity.addPost(postEntity);

            commentEntity = commentRepository.save(commentEntity);
            commentEntityList.add(commentEntity);
        }

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

    @Override
    public void updatePost(Post post) {
        UserEntity userEntity = userRepository.findById(post.getUserId().getValue()).orElse(null);

        PostEntity postEntity = postMapper.fromDomainToEntityWithId(post);
        postEntity.editPost(post.getTitle().getValue(), post.getDescription().getValue());

        postEntity.addUser(userEntity);

        List<CommentEntity> commentEntityList = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            CommentEntity commentEntity = commentMapper.fromDomainToEntityWithId(comment, userEntity.getNickname() ,postEntity);
            commentEntity.addPost(postEntity);

            commentEntity = commentRepository.save(commentEntity);
            commentEntityList.add(commentEntity);
        }

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

    @Override
    public Post retrievePost(Long postId) {

        PostEntity postEntity = postRepository.findById(postId).orElse(null);
        return postMapper.fromEntityToDomain(postEntity, userMapper.fromEntityToDomain(postEntity.getUser()));
    }

    @Override
    public void deletePost(Post deletePost) {
        PostEntity postEntity = postRepository.findById(deletePost.getId().getValue()).orElse(null);
        postRepository.delete(postEntity);
    }
}
