package com.baranozdeniz.personalwebsite.controller;

import com.baranozdeniz.personalwebsite.api.FileApi;
import com.baranozdeniz.personalwebsite.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileController implements FileApi {

    private final FileService service;

    @Override
    public ResponseEntity<String> uploadFile(MultipartFile file) {
        return ResponseEntity.ok(service.uploadFile(file));
    }

    @Override
    public ResponseEntity<Void> deleteFile(String fileName) {
        service.deleteFile(fileName);
        return ResponseEntity.ok().build();
    }
}
