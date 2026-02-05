package com.lucas.api_music.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.Artista;

@DataJpaTest
@ActiveProfiles("test")
public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deveSalvarAlbum() {

        Artista artista = new Artista();
        artista.setNome("Linkin Park");
        artista.setTipo("BANDA");

        entityManager.persistAndFlush(artista);

        Album album = new Album();
        album.setTitulo("Hybrid Theory");
        album.setArtista(artista);

        Album salvo = repository.save(album);

        Assertions.assertNotNull(salvo.getId());
    }
}

