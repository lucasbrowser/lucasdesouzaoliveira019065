package com.lucas.api_music.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.api_music.model.entity.Artista;
import com.lucas.api_music.service.ArtistaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ArtistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarArtista() throws Exception {

        Artista artista = new Artista();
        artista.setId(1L);
        artista.setNome("Linkin Park");
        artista.setTipo("BANDA");

        when(service.salvar(any())).thenReturn(artista);

        mockMvc.perform(post("/api/v1/artistas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artista)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Linkin Park"));
    }
}

