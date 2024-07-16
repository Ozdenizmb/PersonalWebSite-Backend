package com.baranozdeniz.personalwebsite.controller;

import com.baranozdeniz.personalwebsite.api.LikeApi;
import com.baranozdeniz.personalwebsite.dto.LikeCreateDto;
import com.baranozdeniz.personalwebsite.dto.LikeDto;
import com.baranozdeniz.personalwebsite.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class LikeController implements LikeApi {

    private final LikeService service;

    @Override
    public ResponseEntity<LikeDto> addLike(LikeCreateDto likeCreateDto) {
        return ResponseEntity.ok(service.addLike(likeCreateDto));
    }

    @Override
    public ResponseEntity<Integer> getLikeCount(UUID projectId) {
        return ResponseEntity.ok(service.getLikeCount(projectId));
    }

    @Override
    public ResponseEntity<Boolean> didILikeIt(UUID userId, UUID projectId) {
        return ResponseEntity.ok(service.didILikeIt(userId, projectId));
    }

    @Override
    public ResponseEntity<Boolean> deleteLike(UUID userId, UUID projectId) {
        return ResponseEntity.ok(service.deleteLike(userId, projectId));
    }

}
