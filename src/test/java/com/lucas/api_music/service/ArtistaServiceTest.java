package com.lucas.api_music.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.lucas.api_music.model.entity.Artista;
import com.lucas.api_music.repository.ArtistaRepository;

class ArtistaServiceTest {

    @Mock
    private ArtistaRepository repository;

    @InjectMocks
    private ArtistaService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarArtista() {

        Artista artista = new Artista();
        artista.setNome("Metallica");

        when(repository.save(any())).thenReturn(artista);

        Artista salvo = service.salvar(artista);

        assertEquals("Metallica", salvo.getNome());
    }

    @Test
    void deveBuscarPorId() {

        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Coldplay");

        when(repository.findById(1L))
                .thenReturn(Optional.of(artista));

        Optional<Artista> resultado = service.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Coldplay", resultado.get().getNome());
    }
}

