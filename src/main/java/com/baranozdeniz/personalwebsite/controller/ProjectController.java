package com.baranozdeniz.personalwebsite.controller;

import com.baranozdeniz.personalwebsite.api.ProjectApi;
import com.baranozdeniz.personalwebsite.dto.ProjectCreateDto;
import com.baranozdeniz.personalwebsite.dto.ProjectDto;
import com.baranozdeniz.personalwebsite.dto.ProjectUpdateDto;
import com.baranozdeniz.personalwebsite.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ProjectController implements ProjectApi {

    private final ProjectService service;

    @Override
    public ResponseEntity<ProjectDto> createProject(ProjectCreateDto projectCreateDto) {
        return ResponseEntity.ok(service.createProject(projectCreateDto));
    }

    @Override
    public ResponseEntity<ProjectDto> getProjectWithId(UUID id) {
        return ResponseEntity.ok(service.getProjectWithId(id));
    }

    @Override
    public ResponseEntity<ProjectDto> getProjectWithName(String name) {
        return ResponseEntity.ok(service.getProjectWithName(name));
    }

    @Override
    public ResponseEntity<Page<ProjectDto>> getProjects(Pageable pageable) {
        return ResponseEntity.ok(service.getProjects(pageable));
    }

    @Override
    public ResponseEntity<ProjectDto> updateProject(UUID id, ProjectUpdateDto projectUpdateDto) {
        return ResponseEntity.ok(service.updateProject(id, projectUpdateDto));
    }

    @Override
    public ResponseEntity<Boolean> deleteProject(UUID id) {
        return ResponseEntity.ok(service.deleteProject(id));
    }

}
