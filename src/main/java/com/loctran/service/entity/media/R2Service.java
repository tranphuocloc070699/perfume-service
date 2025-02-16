package com.loctran.service.entity.media;

import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class R2Service {

  private final S3Client s3Client;

  @Value("${cloudflare.r2.bucket-name}")
  private String bucketName;

  @Value("${cloudflare.r2.endpoint}")
  private String endpointUrl;

  @Value("${cloudflare.r2.domain}")
  private String domain;

  public String uploadFile(EntityType folderDir, MultipartFile file) throws IOException {
    String key = folderDir.name() + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
    PutObjectRequest request = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(key)
        .contentType(file.getContentType())
        .build();

    s3Client.putObject(request, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
    return String.format("%s/%s", domain, key);
  }

  public String deleteFile(String key) {
    DeleteObjectRequest request = DeleteObjectRequest.builder()
        .bucket(bucketName)
        .key(key)
        .build();
    s3Client.deleteObject(request);
    return String.format("%s/%s/%s", endpointUrl, bucketName, key);
  }
}