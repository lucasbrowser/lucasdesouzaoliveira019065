package com.lucas.api_music.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.Artista;
import com.lucas.api_music.service.AlbumService;
import com.lucas.api_music.service.WebSocketNotifier;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService service;

    @MockBean 
    private WebSocketNotifier notifier;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarAlbum() throws Exception {

        Artista artista = new Artista();
        artista.setNome("Linkin Park");
        artista.setTipo("BANDA");

        Album album = new Album();
        album.setId(1L);
        album.setTitulo("Hybrid Theory");
        album.setArtista(artista);

        Mockito.when(service.salvar(Mockito.any()))
                .thenReturn(album);

        mockMvc.perform(post("/api/v1/albuns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isOk());
    }
}


