package com.project.wish.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3FileUploader {

    //bucketName
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String s3Region;

    @Value("${wish.image.local.path}")
    private String localPath;

    private final AmazonS3Client amazonS3Client;

    public String uploadToLocal(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + "_" + multipartFile.getOriginalFilename();
        Path savePath = Paths.get(localPath, saveFileName);

        try {
            multipartFile.transferTo(savePath);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        //프로젝트내에 저장한 경로
        return savePath.toString();
    }

    public String getS3FileUrl(String filePath) {
        File localFile = new File(filePath);
        String uploadImageUrl = uploadToS3(localFile, localFile.getName());
        removeLocalFile(localFile);
        return uploadImageUrl;
    }

    private String uploadToS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeLocalFile(File localFile) {
        if (localFile.exists() && localFile.delete()) {
            log.info("Local file deleted");
        }
    }

}