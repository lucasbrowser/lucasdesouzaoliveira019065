package com.lucas.api_music.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.AlbumImagem;
import com.lucas.api_music.repository.AlbumImagemRepository;
import com.lucas.api_music.repository.AlbumRepository;

import io.minio.MinioClient;

class AlbumImagemServiceTest {

    @Mock
    private AlbumImagemRepository repository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private MinioClient minioClient;

    @InjectMocks
    private AlbumImagemService service;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);

        ReflectionTestUtils.setField(
            service,
            "bucket",
            "test-bucket"
        );

    }

    @Test
    void deveSalvarImagem() {

        Album album = new Album();
        album.setId(1L);

        when(albumRepository.findById(1L))
                .thenReturn(Optional.of(album));

        AlbumImagem img = new AlbumImagem();
        img.setObjetoImg("teste.png");

        when(repository.save(any())).thenReturn(img);

        AlbumImagem salvo = repository.save(img);

        assertEquals("teste.png", salvo.getObjetoImg());
    }

    @Test
    void deveUploadMultiplasImagens() throws Exception {

        Album album = new Album();
        album.setId(1L);

        when(albumRepository.findById(1L))
                .thenReturn(Optional.of(album));

        MultipartFile file = new MockMultipartFile(
                "file",
                "foto.png",
                "image/png",
                "teste".getBytes()
        );

        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        List<AlbumImagem> resultado =
                service.uploadMultiplas(List.of(file), 1L);

        assertNotNull(resultado);
    }
}

