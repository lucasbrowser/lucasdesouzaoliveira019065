package com.lucas.api_music.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lucas.api_music.integration.argus.RegionalClient;
import com.lucas.api_music.integration.argus.dto.RegionalResponseDTO;
import com.lucas.api_music.model.entity.Regional;
import com.lucas.api_music.repository.RegionalRepository;

import jakarta.transaction.Transactional;

@Service
public class RegionalSyncService {

    private final RegionalClient client;
    private final RegionalRepository repository;

    public RegionalSyncService(
            RegionalClient client,
            RegionalRepository repository
    ) {
        this.client = client;
        this.repository = repository;
    }

    @Transactional
    public void sincronizar() {

        List<RegionalResponseDTO> externos = client.buscarRegionais();

        List<Regional> ativosBanco = repository.findByAtivoTrue();

        // Map externos
        Map<Long, RegionalResponseDTO> externosMap =
                externos.stream()
                        .collect(Collectors.toMap(
                                RegionalResponseDTO::getId,
                                dto -> dto
                        ));

        // 🔴 Inativar removidos ou alterados
        for (Regional banco : ativosBanco) {

            RegionalResponseDTO externo =
                    externosMap.get(banco.getIdExterno());

            if (externo == null) {
                banco.setAtivo(false);
                repository.save(banco);
                continue;
            }

            if (!banco.getNome().equals(externo.getNome())) {

                banco.setAtivo(false);
                repository.save(banco);

                Regional novo = criarNovo(externo);
                repository.save(novo);
            }
        }

        // 🟢 Inserir novos
        for (RegionalResponseDTO dto : externos) {

            boolean existe =
                    ativosBanco.stream()
                            .anyMatch(r ->
                                    r.getIdExterno() != null 
                                    && r.getIdExterno().equals(dto.getId())
                                    && r.getAtivo()
                            );

            if (!existe) {
                repository.save(criarNovo(dto));
            }
        }
    }

    private Regional criarNovo(RegionalResponseDTO dto) {

        Regional r = new Regional();
        r.setIdExterno(dto.getId());
        r.setNome(dto.getNome());
        r.setAtivo(true);
        return r;
    }
}



