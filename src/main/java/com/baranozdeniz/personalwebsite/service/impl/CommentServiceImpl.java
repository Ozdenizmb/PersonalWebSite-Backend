package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.dto.CommentCreateDto;
import com.baranozdeniz.personalwebsite.dto.CommentDto;
import com.baranozdeniz.personalwebsite.dto.CommentUpdateDto;
import com.baranozdeniz.personalwebsite.mapper.CommentMapper;
import com.baranozdeniz.personalwebsite.repository.CommentRepository;
import com.baranozdeniz.personalwebsite.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final CommentMapper mapper;

    @Override
    public CommentDto createComment(CommentCreateDto commentCreateDto) {
        return null;
    }

    @Override
    public CommentDto getComment(UUID id) {
        return null;
    }

    @Override
    public Page<CommentDto> getCommentsWithProject(UUID projectId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<CommentDto> getCommentsWithUser(UUID userId, Pageable pageable) {
        return null;
    }

    @Override
    public CommentDto updateComment(UUID id, CommentUpdateDto commentUpdateDto) {
        return null;
    }

    @Override
    public Boolean deleteComment(UUID id) {
        return null;
    }

}
