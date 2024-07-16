package com.baranozdeniz.personalwebsite.mapper;

import com.baranozdeniz.personalwebsite.dto.LikeDto;
import com.baranozdeniz.personalwebsite.model.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper extends BaseMapper<Like, LikeDto> {



}
