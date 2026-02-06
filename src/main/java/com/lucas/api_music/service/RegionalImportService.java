package com.lucas.api_music.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.api_music.integration.argus.RegionalClient;
import com.lucas.api_music.integration.argus.dto.RegionalResponseDTO;
import com.lucas.api_music.model.entity.Regional;
import com.lucas.api_music.repository.RegionalRepository;

@Service
public class RegionalImportService {

    private final RegionalClient client;
    private final RegionalRepository repository;

    public RegionalImportService(
            RegionalClient client,
            RegionalRepository repository
    ) {
        this.client = client;
        this.repository = repository;
    }

    @Transactional
    public void importarRegionais() {

        List<RegionalResponseDTO> lista = client.buscarRegionais();

        for (RegionalResponseDTO dto : lista) {

            Regional regional = new Regional();
            regional.setId(dto.getId());
            regional.setNome(dto.getNome());
            regional.setAtivo(true);

            repository.save(regional);
        }
    }
}

