package com.baranozdeniz.personalwebsite.service;

import com.baranozdeniz.personalwebsite.dto.ProjectCreateDto;
import com.baranozdeniz.personalwebsite.dto.ProjectDto;
import com.baranozdeniz.personalwebsite.dto.ProjectUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProjectService {

    ProjectDto createProject(ProjectCreateDto projectCreateDto);
    ProjectDto getProjectWithId(UUID id);
    ProjectDto getProjectWithName(String name);
    Page<ProjectDto> getProjects(Pageable pageable);
    ProjectDto updateProject(UUID id, ProjectUpdateDto projectUpdateDto);
    Boolean deleteProject(UUID id);

}
