package com.baranozdeniz.personalwebsite.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    String uploadFile(MultipartFile file);
    List<String> uploadFiles(List<MultipartFile> files);
    Boolean deleteFile(String fileName);

}