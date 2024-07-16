package com.baranozdeniz.personalwebsite.service;

import com.baranozdeniz.personalwebsite.dto.CommentCreateDto;
import com.baranozdeniz.personalwebsite.dto.CommentDto;
import com.baranozdeniz.personalwebsite.dto.CommentUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentService {

    CommentDto createComment(CommentCreateDto commentCreateDto);
    CommentDto getComment(UUID id);
    Page<CommentDto> getCommentsWithProject(UUID projectId, Pageable pageable);
    Page<CommentDto> getCommentsWithUser(UUID userId, Pageable pageable);
    CommentDto updateComment(UUID id, CommentUpdateDto commentUpdateDto);
    Boolean deleteComment(UUID id);

}
