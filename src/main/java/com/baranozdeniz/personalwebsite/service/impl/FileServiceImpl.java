package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.exception.ErrorMessages;
import com.baranozdeniz.personalwebsite.exception.PwsException;
import com.baranozdeniz.personalwebsite.model.FileEntity;
import com.baranozdeniz.personalwebsite.repository.FileRepository;
import com.baranozdeniz.personalwebsite.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository repository;

    @Value("${file.allowed-formats-for-project}")
    private String[] allowedFormats;
    @Value("${file.default-image-height}")
    private int defaultImageHeight;
    @Value("${file.default-image-width}")
    private int defaultImageWidth;

    private final String serverFilePath = System.getProperty("user.dir") + "/assets/";
    @Value("${file.cdn.path}")
    private String cdnPath;

    @Override
    public String uploadFile(MultipartFile file) {
        String randomName = UUID.randomUUID().toString().replace("-", "");
        String fileType = Objects.requireNonNull(file.getContentType()).split("/")[1];

        if(!Arrays.asList(allowedFormats).contains(fileType)) {
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.UNSUPPORTED_FILE_TYPE);
        }

        String fileName = randomName + "." + fileType;

        try {
            File tempFile;

            if(fileType.equals("png") || fileType.equals("jpeg") || fileType.equals("jpg")) {
                BufferedImage originalImage = ImageIO.read(file.getInputStream());
                BufferedImage resizedImage = new BufferedImage(defaultImageWidth, defaultImageHeight, originalImage.getType());

                Graphics2D writer = resizedImage.createGraphics();
                writer.drawImage(originalImage, 0, 0, defaultImageWidth, defaultImageHeight, null);
                writer.dispose();

                tempFile = new File(serverFilePath, fileName);
                ImageIO.write(resizedImage, fileType, tempFile);
            }
            else {
                tempFile = new File(serverFilePath, fileName);
                file.transferTo(tempFile);
            }

            FileEntity fileEntity = FileEntity.builder()
                    .name(fileName)
                    .type(file.getContentType())
                    .cdnPath(cdnPath)
                    .build();

            FileEntity responseFile = repository.save(fileEntity);

            return responseFile.getName();

        } catch (IOException e) {
            throw PwsException.withStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.FILE_CANNOT_WRITE);
        }

    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) {
        CompletableFuture<List<String>> uploadResultFuture = uploadFilesAsync(files);

        try {
            return uploadResultFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw PwsException.withStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.FILE_CANNOT_WRITE);
        }

    }

    @Async
    public CompletableFuture<List<String>> uploadFilesAsync(List<MultipartFile> files) {
        List<String> uploadedFileNames = new ArrayList<>();

        files.forEach(file -> {
            uploadedFileNames.add(uploadFile(file));
        });

        return CompletableFuture.completedFuture(uploadedFileNames);
    }

    @Override
    public void deleteFile(String fileName) {
        Optional<FileEntity> response = repository.findByName(fileName);

        if(response.isPresent()){
            FileEntity existFile = response.get();

            try {
                repository.delete(existFile);

                String filePath = serverFilePath + "/" + existFile.getName();
                File fileToDelete = new File(filePath);

                if(fileToDelete.exists()) {
                    boolean isDeleted = fileToDelete.delete();
                    if (!isDeleted) {
                        throw PwsException.withStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.FILE_CANNOT_DELETE);
                    }
                } else {
                    throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.FILE_NOT_FOUND);
                }

            } catch (Exception e) {
                throw PwsException.withStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.FILE_CANNOT_DELETE);
            }

        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.FILE_NOT_FOUND);
        }

    }

}
