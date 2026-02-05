package com.lucas.api_music.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {

    @Bean
    MinioClient minioClient(
        @Value("${minio.url}") String url,
        @Value("${minio.access-key}") String access,
        @Value("${minio.secret-key}") String secret
    ) {
        return MinioClient.builder()
            .endpoint(url)
            .credentials(access, secret)
            .build();
    }
}
