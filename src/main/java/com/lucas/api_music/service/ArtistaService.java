package com.lucas.api_music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lucas.api_music.exception.ArtistaNotFoundException;
import com.lucas.api_music.model.entity.Artista;
import com.lucas.api_music.repository.ArtistaRepository;

@Service
public class ArtistaService {

    private final ArtistaRepository repository;

    public ArtistaService(ArtistaRepository repository) {
        this.repository = repository;
    }

    public List<Artista> listarTodos() {
        return repository.findAll();
    }

    public Optional<Artista> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Artista salvar(Artista artista) {
        return repository.save(artista);
    }

    public Artista atualizar(Long id, Artista artistaAtualizado) {

        Artista artista = repository.findById(id)
                .orElseThrow(() -> new ArtistaNotFoundException(id));

        artista.setNome(artistaAtualizado.getNome());
        artista.setTipo(artistaAtualizado.getTipo());

        return repository.save(artista);
    }
}
