package com.lucas.api_music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucas.api_music.exception.AlbumNotFoundException;
import com.lucas.api_music.exception.BusinessException;
import com.lucas.api_music.model.dto.AlbumImageResponse;
import com.lucas.api_music.model.dto.AlbumResponse;
import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.AlbumImagem;
import com.lucas.api_music.repository.AlbumImagemRepository;
import com.lucas.api_music.repository.AlbumRepository;

@Service
public class AlbumService {

    private final AlbumRepository repository;
    private final WebSocketNotifier notifier;
    private final AlbumImagemRepository albumImagemRepository;
    private final AlbumImagemService albumImagemService;

    public AlbumService(AlbumRepository repository, 
        WebSocketNotifier notifier, 
        AlbumImagemRepository albumImagemRepository,
        AlbumImagemService albumImagemService) {
        this.repository = repository;
        this.notifier = notifier;
        this.albumImagemRepository = albumImagemRepository;
        this.albumImagemService = albumImagemService;
    }

    public Album salvar(Album album) {
        if(album.getTitulo() == null || album.getTitulo().isBlank()) {
            throw new BusinessException("Título do álbum é obrigatório");
        }
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
            .orElseThrow(() -> new AlbumNotFoundException(id));

        album.setTitulo(albumAtualizado.getTitulo());
        album.setArtista(albumAtualizado.getArtista());

        return repository.save(album);
    }

    public AlbumResponse buscarComImagens(Long id) throws Exception {
        Album album = repository.findById(id)
            .orElseThrow(() -> new AlbumNotFoundException(id));

        List<AlbumImagem> imagens = albumImagemRepository.findByAlbumId(id);

        List<AlbumImageResponse> imagensDto =
            imagens.stream()
                .map(img -> {
                    try {
                        return new AlbumImageResponse(
                            img.getId(),
                            albumImagemService.gerarUrl(img)
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        return new AlbumResponse(
            album.getId(),
            album.getTitulo(),
            album.getArtista().getNome(),
            imagensDto
        );
    }



}
