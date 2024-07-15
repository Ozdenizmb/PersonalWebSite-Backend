package com.baranozdeniz.personalwebsite.mapper;

import com.baranozdeniz.personalwebsite.dto.ProjectDto;
import com.baranozdeniz.personalwebsite.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<Project, ProjectDto> {



}
