package com.lucas.api_music.integration.argus;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lucas.api_music.integration.argus.dto.RegionalResponseDTO;

@Service
public class RegionalClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String URL = "https://integrador-argus-api.geia.vip/v1/regionais";

    public List<RegionalResponseDTO> buscarRegionais() {

        ResponseEntity<RegionalResponseDTO[]> response =
            restTemplate.getForEntity(URL, RegionalResponseDTO[].class);

        return Arrays.asList(response.getBody());
    }
}

