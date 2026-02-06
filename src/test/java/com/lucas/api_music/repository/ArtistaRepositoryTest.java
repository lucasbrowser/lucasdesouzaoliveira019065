package com.lucas.api_music.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.lucas.api_music.model.entity.Artista;

@DataJpaTest
@ActiveProfiles("test")
class ArtistaRepositoryTest {

    @Autowired
    private ArtistaRepository repository;

    @Test
    void deveSalvarArtista() {

        Artista artista = new Artista();
        artista.setNome("Linkin Park");
        artista.setTipo("BANDA");

        Artista salvo = repository.save(artista);

        assertNotNull(salvo.getId());
        assertEquals("Linkin Park", salvo.getNome());
    }

    @Test
    void deveBuscarPorId() {

        Artista artista = new Artista();
        artista.setNome("Eminem");
        artista.setTipo("SOLO");

        Artista salvo = repository.save(artista);

        Artista encontrado = repository.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Eminem", encontrado.getNome());
    }
}

