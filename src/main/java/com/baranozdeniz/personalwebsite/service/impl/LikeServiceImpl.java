package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.dto.LikeCreateDto;
import com.baranozdeniz.personalwebsite.dto.LikeDto;
import com.baranozdeniz.personalwebsite.mapper.LikeMapper;
import com.baranozdeniz.personalwebsite.repository.LikeRepository;
import com.baranozdeniz.personalwebsite.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final LikeMapper mapper;

    @Override
    public LikeDto addLike(LikeCreateDto likeCreateDto) {
        return null;
    }

    @Override
    public int getLikeCount(UUID projectId) {
        return 0;
    }

    @Override
    public Boolean didILikeIt(UUID userId, UUID projectId) {
        return null;
    }

    @Override
    public Boolean deleteLike(UUID userId, UUID projectId) {
        return null;
    }

}
