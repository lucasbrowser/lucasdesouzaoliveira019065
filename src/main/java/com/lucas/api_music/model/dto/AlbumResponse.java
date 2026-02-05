package com.lucas.api_music.model.dto;

import java.util.List;

public class AlbumResponse {

    private Long id;
    private String nome;
    private String artista;
    private List<AlbumImageResponse> imagens;

    public AlbumResponse(Long id, String nome, String artista,
                         List<AlbumImageResponse> imagens) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }

    public List<AlbumImageResponse> getImagens() {
        return imagens;
    }

}

