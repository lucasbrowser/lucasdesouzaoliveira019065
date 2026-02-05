package com.lucas.api_music.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.AlbumImagem;
import com.lucas.api_music.repository.AlbumImagemRepository;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

@Service
public class AlbumImagemService {

    private final AlbumImagemRepository repository;
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    public AlbumImagemService(
            AlbumImagemRepository repository,
            MinioClient minioClient
    ) {
        this.repository = repository;
        this.minioClient = minioClient;
    }

    // Buscar imagens do album
    public List<AlbumImagem> buscarPorAlbum(Long albumId) {
        return repository.findByAlbumId(albumId);
    }

    // Salvar metadata
    public AlbumImagem salvar(AlbumImagem image) {
        return repository.save(image);
    }

    // Fazer upload da imagem
    public AlbumImagem upload(MultipartFile file, Album album) throws Exception {
        String objectName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
        );

        AlbumImagem img = new AlbumImagem();
        img.setAlbum(album);
        img.setObjetoImg(file.getOriginalFilename());
        img.setUrlAlbum(objectName);

        return repository.save(img);
    }

    public String gerarUrl(AlbumImagem imagem) throws Exception {
        return minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucket)
                .object(imagem.getObjetoImg())
                .expiry(60 * 30)
                .build()
        );
    }



}

