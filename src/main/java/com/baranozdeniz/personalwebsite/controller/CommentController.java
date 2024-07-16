package com.baranozdeniz.personalwebsite.controller;

import com.baranozdeniz.personalwebsite.api.CommentApi;
import com.baranozdeniz.personalwebsite.dto.CommentCreateDto;
import com.baranozdeniz.personalwebsite.dto.CommentDto;
import com.baranozdeniz.personalwebsite.dto.CommentUpdateDto;
import com.baranozdeniz.personalwebsite.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CommentController implements CommentApi {

    private final CommentService service;

    @Override
    public ResponseEntity<CommentDto> createComment(CommentCreateDto commentCreateDto) {
        return ResponseEntity.ok(service.createComment(commentCreateDto));
    }

    @Override
    public ResponseEntity<CommentDto> getComment(UUID id) {
        return ResponseEntity.ok(service.getComment(id));
    }

    @Override
    public ResponseEntity<Page<CommentDto>> getCommentsWithProject(UUID projectId, Pageable pageable) {
        return ResponseEntity.ok(service.getCommentsWithProject(projectId, pageable));
    }

    @Override
    public ResponseEntity<Page<CommentDto>> getCommentsWithUser(UUID userId, Pageable pageable) {
        return ResponseEntity.ok(service.getCommentsWithUser(userId, pageable));
    }

    @Override
    public ResponseEntity<CommentDto> updateComment(UUID id, CommentUpdateDto commentUpdateDto) {
        return ResponseEntity.ok(service.updateComment(id, commentUpdateDto));
    }

    @Override
    public ResponseEntity<Boolean> deleteComment(UUID id) {
        return ResponseEntity.ok(service.deleteComment(id));
    }

}
