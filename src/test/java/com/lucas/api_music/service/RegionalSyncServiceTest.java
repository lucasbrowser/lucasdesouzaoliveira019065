package com.lucas.api_music.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lucas.api_music.integration.argus.RegionalClient;
import com.lucas.api_music.integration.argus.dto.RegionalResponseDTO;
import com.lucas.api_music.repository.RegionalRepository;

@ExtendWith(MockitoExtension.class)
class RegionalSyncServiceTest {

    @Mock
    private RegionalClient client;

    @Mock
    private RegionalRepository repository;

    @InjectMocks
    private RegionalSyncService service;

    @Test
    void deveSincronizar() {

        RegionalResponseDTO dto = new RegionalResponseDTO();
        dto.setId(1L);
        dto.setNome("Regional Sync");

        when(client.buscarRegionais())
                .thenReturn(List.of(dto));

        service.sincronizar();

        verify(repository, atLeastOnce()).save(any());
    }
}

