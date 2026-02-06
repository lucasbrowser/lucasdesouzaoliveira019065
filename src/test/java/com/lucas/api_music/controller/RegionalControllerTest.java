package com.lucas.api_music.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.api_music.model.entity.Regional;
import com.lucas.api_music.service.RegionalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RegionalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionalService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveBuscarTodos() throws Exception {

        Regional r = new Regional();
        r.setId(1L);
        r.setNome("Regional Controller");

        when(service.buscarAtivos())
                .thenReturn(List.of(r));

        mockMvc.perform(get("/api/v1/regionais"))
                .andExpect(status().isOk());
    }
}

