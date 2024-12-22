package com.loctran.service.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class R2Config {

  @Value("${cloudflare.r2.access-key}")
  private String accessKey;

  @Value("${cloudflare.r2.secret-key}")
  private String secretKey;

  @Value("${cloudflare.r2.endpoint}")
  private String endPoint;

  @Bean
  public S3Client s3Client() {
    return S3Client.builder()
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey)
            )
        )
        .endpointOverride(URI.create(endPoint))
        .region(Region.US_EAST_1)
        .serviceConfiguration(
            S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build()
        )
        .build();
  }
}