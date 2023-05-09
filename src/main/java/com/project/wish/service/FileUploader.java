package com.project.wish.service;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Component
public class FileUploader {

    public String getUploadFilePath(MultipartFile uploadFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(uploadFile.getOriginalFilename()));
        Path path = Paths.get("src/main/resources/static/uploadImages/" + fileName);
        Files.write(path, uploadFile.getBytes());

        return path.toString().replace("src/main/resources/static", "");
    }

}
