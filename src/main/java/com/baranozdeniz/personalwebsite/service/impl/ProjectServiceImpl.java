package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.dto.ProjectCreateDto;
import com.baranozdeniz.personalwebsite.dto.ProjectDto;
import com.baranozdeniz.personalwebsite.dto.ProjectUpdateDto;
import com.baranozdeniz.personalwebsite.exception.ErrorMessages;
import com.baranozdeniz.personalwebsite.exception.PwsException;
import com.baranozdeniz.personalwebsite.mapper.PageMapperHelper;
import com.baranozdeniz.personalwebsite.mapper.ProjectMapper;
import com.baranozdeniz.personalwebsite.model.Project;
import com.baranozdeniz.personalwebsite.repository.ProjectRepository;
import com.baranozdeniz.personalwebsite.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    @Override
    public ProjectDto createProject(ProjectCreateDto projectCreateDto) {
        Project project = new Project();
        BeanUtils.copyProperties(projectCreateDto, project);

        return mapper.toDto(repository.save(project));
    }

    @Override
    public ProjectDto getProjectWithId(UUID id) {
        Optional<Project> responseProject = repository.findById(id);

        if(responseProject.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.PROJECT_NOT_FOUND);
        }

        return mapper.toDto(responseProject.get());
    }

    @Override
    public ProjectDto getProjectWithName(String name) {
        Optional<Project> responseProject = repository.findByName(name);

        if(responseProject.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.PROJECT_NOT_FOUND);
        }

        return mapper.toDto(responseProject.get());
    }

    @Override
    public Page<ProjectDto> getProjects(Pageable pageable) {
        Page<Project> responsePage = repository.findAll(pageable);
        return PageMapperHelper.mapEntityPageToDtoPage(responsePage, mapper);
    }

    @Override
    public ProjectDto updateProject(UUID id, ProjectUpdateDto projectUpdateDto) {
        Optional<Project> responseProject = repository.findById(id);

        if(responseProject.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.PROJECT_NOT_FOUND);
        }

        Project existProject = responseProject.get();
        BeanUtils.copyProperties(projectUpdateDto, existProject);

        return mapper.toDto(repository.save(existProject));
    }

    @Override
    public Boolean deleteProject(UUID id) {
        Optional<Project> responseProject = repository.findById(id);

        if(responseProject.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.PROJECT_NOT_FOUND);
        }

        repository.delete(responseProject.get());

        return true;
    }

}
