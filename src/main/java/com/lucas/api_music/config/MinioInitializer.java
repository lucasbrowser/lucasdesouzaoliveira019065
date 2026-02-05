package com.lucas.api_music.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;

@Component
public class MinioInitializer {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    public MinioInitializer(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @PostConstruct
    public void init() {

        try {

            boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder()
                    .bucket(bucket)
                    .build()
            );

            if (!exists) {

                minioClient.makeBucket(
                    MakeBucketArgs.builder()
                        .bucket(bucket)
                        .build()
                );

                System.out.println("Bucket criado: " + bucket);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

