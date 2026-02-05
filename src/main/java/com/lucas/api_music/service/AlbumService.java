package com.lucas.api_music.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.repository.AlbumRepository;

@Service
public class AlbumService {

    private final AlbumRepository repository;
    private final WebSocketNotifier notifier;

    public AlbumService(AlbumRepository repository, WebSocketNotifier notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    public Album salvar(Album album) {
        Album salvo = repository.save(album);
        notifier.novoAlbum(salvo);
        return salvo;
    }

    public Page<Album> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Album> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Album atualizar(Long id, Album albumAtualizado) {

    Album album = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Album não encontrado"));

        album.setTitulo(albumAtualizado.getTitulo());
        album.setArtista(albumAtualizado.getArtista());

        return repository.save(album);
    }


}
