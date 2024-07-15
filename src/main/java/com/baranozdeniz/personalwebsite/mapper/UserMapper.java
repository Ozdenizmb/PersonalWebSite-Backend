package com.baranozdeniz.personalwebsite.mapper;

import com.baranozdeniz.personalwebsite.dto.UserDto;
import com.baranozdeniz.personalwebsite.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {



}
