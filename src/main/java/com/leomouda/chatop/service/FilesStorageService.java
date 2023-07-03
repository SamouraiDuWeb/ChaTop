package com.leomouda.chatop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FilesStorageService {

    private String uploadDir;
    @Value("${app.url}")
    private String appUrl;
    @Value("${server.port}")
    private String serverPort;

    public String save(MultipartFile file) throws IOException {
        uploadDir = new File(".").getAbsolutePath() + "/images/" ;
        try {
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = normalizeFileName(Objects.requireNonNull(file.getOriginalFilename()));

            Path filePath = Paths.get(uploadDir, fileName);

            file.transferTo(filePath.toFile());

            return "http://localhost:3001/images/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier.", e);
        }
    }

    public String normalizeFileName(String originalFileName) {

        String normalizedFileName = originalFileName.toLowerCase();

        normalizedFileName = normalizedFileName.replace(" ", "-");

        normalizedFileName = normalizedFileName.replaceAll("[^a-z0-9\\-\\.]", "");

        return normalizedFileName;
    }
}
