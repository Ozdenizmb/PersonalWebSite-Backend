package com.baranozdeniz.personalwebsite.service;

import com.baranozdeniz.personalwebsite.dto.LikeCreateDto;
import com.baranozdeniz.personalwebsite.dto.LikeDto;

import java.util.UUID;

public interface LikeService {

    LikeDto addLike(LikeCreateDto likeCreateDto);
    int getLikeCount(UUID projectId);
    Boolean didILikeIt(UUID userId, UUID projectId);
    Boolean deleteLike(UUID userId, UUID projectId);

}
