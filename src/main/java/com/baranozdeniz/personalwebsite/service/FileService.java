package com.baranozdeniz.personalwebsite.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FileService {

    String uploadFile(MultipartFile file);
    List<String> uploadFiles(List<MultipartFile> files);
    Boolean deleteFile(String fileName);

}
