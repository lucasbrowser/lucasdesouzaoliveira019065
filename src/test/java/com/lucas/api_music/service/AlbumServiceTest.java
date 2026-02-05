package com.lucas.api_music.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.Artista;
import com.lucas.api_music.repository.AlbumRepository;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    private AlbumRepository repository;

    @Mock
    private WebSocketNotifier notifier;

    @InjectMocks
    private AlbumService service;

    @Test
    void deveSalvarAlbum() {

        Artista artista = new Artista();
        artista.setNome("Linkin Park");
        artista.setTipo("BANDA");

        Album album = new Album();
        album.setTitulo("Hybrid Theory");
        album.setArtista(artista);

        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(album);

        Album salvo = service.salvar(album);

        Assertions.assertNotNull(salvo);
        Assertions.assertEquals("Hybrid Theory", salvo.getTitulo());

        Mockito.verify(notifier).novoAlbum(Mockito.any());
    }

    @Test
    void deveBuscarPorId() {

        Album album = new Album();
        album.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(album));

        Optional<Album> resultado = service.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }
}

