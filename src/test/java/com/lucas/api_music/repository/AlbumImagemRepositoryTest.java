package com.lucas.api_music.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.AlbumImagem;
import com.lucas.api_music.model.entity.Artista;

@DataJpaTest
@ActiveProfiles("test")
class AlbumImagemRepositoryTest {

    @Autowired
    private AlbumImagemRepository repository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Test
    void deveSalvarImagem() {

        Artista artista = new Artista();
        artista.setNome("Linkin Park");
        artista.setTipo("BANDA");
        artista = artistaRepository.save(artista);

        Album album = new Album();
        album.setTitulo("Hybrid Theory");
        album.setArtista(artista);
        album = albumRepository.save(album);

        AlbumImagem img = new AlbumImagem();
        img.setAlbum(album);
        img.setObjetoImg("teste.png");
        img.setUrlAlbum("http://localhost/teste.png");

        AlbumImagem salvo = repository.save(img);

        assertNotNull(salvo.getId());
        assertEquals("teste.png", salvo.getObjetoImg());
    }
}

