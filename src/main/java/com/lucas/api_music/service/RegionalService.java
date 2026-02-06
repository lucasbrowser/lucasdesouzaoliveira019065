package com.lucas.api_music.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.api_music.model.entity.Regional;
import com.lucas.api_music.repository.RegionalRepository;

@Service
public class RegionalService {

    private final RegionalRepository repository;

    public RegionalService(RegionalRepository repository) {
        this.repository = repository;
    }

    public List<Regional> listar() {
        return repository.findAll();
    }

    public Regional buscarPorId(Long id) {
        return repository.findAll()
            .stream()
            .filter(r -> r.getId().equals(id))
            .findFirst()
            .orElseThrow(() ->
                new RuntimeException("Regional não encontrada"));
    }

    public List<Regional> buscarAtivos() {
        return repository.findAll()
            .stream()
            .filter(Regional::getAtivo)
            .toList();
    }
}

