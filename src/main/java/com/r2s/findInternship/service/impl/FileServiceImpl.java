package com.r2s.findInternship.service.impl;

import com.r2s.findInternship.service.FileService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    private final String UPLOAD_DIR = "path/file/"; // config in properties file later

    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        // Generate a unique file name to avoid overwriting existing files
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Create the directory for storing uploaded files if it doesn't exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Copy the uploaded file to the upload directory
        Path targetPath = Paths.get(UPLOAD_DIR + fileName);
        try {
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public Resource downloadFile(String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + fileName);
        }
        return resource;
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(UPLOAD_DIR + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
