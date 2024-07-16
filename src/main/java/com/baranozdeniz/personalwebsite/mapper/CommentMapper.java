package com.baranozdeniz.personalwebsite.mapper;

import com.baranozdeniz.personalwebsite.dto.CommentDto;
import com.baranozdeniz.personalwebsite.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<Comment, CommentDto> {



}
