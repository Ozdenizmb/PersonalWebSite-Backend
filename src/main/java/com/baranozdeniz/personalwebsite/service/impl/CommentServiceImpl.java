package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.dto.CommentCreateDto;
import com.baranozdeniz.personalwebsite.dto.CommentDto;
import com.baranozdeniz.personalwebsite.dto.CommentUpdateDto;
import com.baranozdeniz.personalwebsite.dto.CommentWithUserDto;
import com.baranozdeniz.personalwebsite.exception.ErrorMessages;
import com.baranozdeniz.personalwebsite.exception.PwsException;
import com.baranozdeniz.personalwebsite.mapper.CommentMapper;
import com.baranozdeniz.personalwebsite.mapper.PageMapperHelper;
import com.baranozdeniz.personalwebsite.model.Comment;
import com.baranozdeniz.personalwebsite.model.User;
import com.baranozdeniz.personalwebsite.repository.CommentRepository;
import com.baranozdeniz.personalwebsite.repository.UserRepository;
import com.baranozdeniz.personalwebsite.service.AuthService;
import com.baranozdeniz.personalwebsite.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final CommentMapper mapper;
    private final AuthService authService;

    @Override
    public CommentDto createComment(CommentCreateDto commentCreateDto) {
        if(!authService.verifyUserIdMatchesAuthenticatedUser(commentCreateDto.userId())) {
            throw PwsException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.UNAUTHORIZED);
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentCreateDto, comment);

        return mapper.toDto(repository.save(comment));
    }

    @Override
    public CommentDto getComment(UUID id) {
        Optional<Comment> responseComment = repository.findById(id);

        if(responseComment.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.COMMENT_NOT_FOUND);
        }

        return mapper.toDto(responseComment.get());
    }

    @Override
    public Page<CommentWithUserDto> getCommentsWithProject(UUID projectId, Pageable pageable) {
        Page<Comment> responseComments = repository.findAllByProjectId(projectId, pageable);

        if(responseComments.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.COMMENT_NOT_FOUND);
        }

        Set<UUID> userIds = responseComments.stream()
                .map(Comment::getUserId)
                .collect(Collectors.toSet());

        List<User> users = userRepository.findAllById(userIds);
        Map<UUID, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user));

        List<CommentWithUserDto> dtoList = new ArrayList<>();

        for(Comment comment : responseComments) {
            User user = userMap.get(comment.getUserId());

            if(user == null) {
                throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
            }

            CommentWithUserDto dto = new CommentWithUserDto(
                    comment.getId(),
                    comment.getUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getImageUrl(),
                    user.getRole(),
                    comment.getProjectId(),
                    comment.getText(),
                    comment.getCreatedDate(),
                    comment.getUpdatedDate()
            );

            dtoList.add(dto);
        }

        return new PageImpl<>(dtoList, pageable, responseComments.getTotalElements());
    }

    @Override
    public Page<CommentWithUserDto> getCommentsWithUser(UUID userId, Pageable pageable) {
        Page<Comment> responseComments = repository.findAllByUserId(userId, pageable);

        if(responseComments.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.COMMENT_NOT_FOUND);
        }

        Optional<User> repository = userRepository.findById(userId);

        if(repository.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        User user = repository.get();
        List<CommentWithUserDto> dtoList = new ArrayList<>();

        for(Comment comment : responseComments) {
            CommentWithUserDto dto = new CommentWithUserDto(
                    comment.getId(),
                    comment.getUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getImageUrl(),
                    user.getRole(),
                    comment.getProjectId(),
                    comment.getText(),
                    comment.getCreatedDate(),
                    comment.getUpdatedDate()
            );

            dtoList.add(dto);
        }

        return new PageImpl<>(dtoList, pageable, responseComments.getTotalElements());
    }

    @Override
    public CommentDto updateComment(UUID id, CommentUpdateDto commentUpdateDto) {
        Optional<Comment> responseComment = repository.findById(id);

        if(responseComment.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.COMMENT_NOT_FOUND);
        }

        Comment existComment = responseComment.get();

        if(!authService.verifyUserIdMatchesAuthenticatedUser(existComment.getUserId())) {
            throw PwsException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.UNAUTHORIZED);
        }

        BeanUtils.copyProperties(commentUpdateDto, existComment);

        return mapper.toDto(repository.save(existComment));
    }

    @Override
    public Boolean deleteComment(UUID id) {
        Optional<Comment> responseComment = repository.findById(id);

        if(responseComment.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.COMMENT_NOT_FOUND);
        }

        Comment existComment = responseComment.get();

        if(!authService.verifyUserIdMatchesAuthenticatedUser(existComment.getUserId())) {
            throw PwsException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.UNAUTHORIZED);
        }

        repository.delete(existComment);

        return true;
    }

}
