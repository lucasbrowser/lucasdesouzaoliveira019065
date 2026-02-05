package com.lucas.api_music.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.api_music.exception.AlbumNotFoundException;
import com.lucas.api_music.exception.BusinessException;
import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.AlbumImagem;
import com.lucas.api_music.repository.AlbumImagemRepository;
import com.lucas.api_music.repository.AlbumRepository;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

@Service
public class AlbumImagemService {

    private final AlbumImagemRepository repository;
    private final AlbumRepository albumRepository;
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    public AlbumImagemService(
            AlbumImagemRepository repository,
            MinioClient minioClient,
            AlbumRepository albumRepository
    ) {
        this.repository = repository;
        this.minioClient = minioClient;
        this.albumRepository = albumRepository;
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
    public AlbumImagem upload(MultipartFile file, Long albumId) throws Exception {

        Album album = albumRepository.findById(albumId)
        .orElseThrow(() -> new AlbumNotFoundException(albumId));

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


    public List<AlbumImagem> uploadMultiplas(List<MultipartFile> files, Long albumId) throws Exception {

        Album album = albumRepository.findById(albumId)
            .orElseThrow(() -> new AlbumNotFoundException(albumId));

        List<AlbumImagem> result = new ArrayList<>();

        for (MultipartFile file : files) {

            if(file.isEmpty()) {
                throw new BusinessException("Arquivo vazio");
            }

            if(!file.getContentType().startsWith("image/")) {
                throw new BusinessException("Arquivo deve ser imagem");
            }

            String objectName =
                UUID.randomUUID() + "_" + file.getOriginalFilename();

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

            result.add(repository.save(img));
        }

        return result;
    }


}

