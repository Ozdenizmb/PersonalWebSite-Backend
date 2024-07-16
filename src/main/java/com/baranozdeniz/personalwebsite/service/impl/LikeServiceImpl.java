package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.dto.LikeCreateDto;
import com.baranozdeniz.personalwebsite.dto.LikeDto;
import com.baranozdeniz.personalwebsite.exception.ErrorMessages;
import com.baranozdeniz.personalwebsite.exception.PwsException;
import com.baranozdeniz.personalwebsite.mapper.LikeMapper;
import com.baranozdeniz.personalwebsite.model.Like;
import com.baranozdeniz.personalwebsite.repository.LikeRepository;
import com.baranozdeniz.personalwebsite.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final LikeMapper mapper;

    @Override
    public LikeDto addLike(LikeCreateDto likeCreateDto) {
        Like like = new Like();
        BeanUtils.copyProperties(likeCreateDto, like);

        return mapper.toDto(repository.save(like));
    }

    @Override
    public int getLikeCount(UUID projectId) {
        List<Like> responseLike = repository.findAllByProjectId(projectId);
        return responseLike.size();
    }

    @Override
    public Boolean didILikeIt(UUID userId, UUID projectId) {
        Optional<Like> responseLike = repository.findByUserIdAndProjectId(userId, projectId);
        return responseLike.isPresent();
    }

    @Override
    public Boolean deleteLike(UUID userId, UUID projectId) {
        Optional<Like> responseLike = repository.findByUserIdAndProjectId(userId, projectId);

        if(responseLike.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.LIKE_NOT_FOUND);
        }

        repository.delete(responseLike.get());

        return true;
    }

}
