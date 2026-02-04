package com.lucas.api_music.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "album_imagens")
public class AlbumImagem {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "object_img")
    private String objetoImg;

    @ManyToOne
    @JoinColumn(name = "album")
    private Album album;

    @Column(name = "url_album")
    private String urlAlbum;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getObjetoImg() {
        return objetoImg;
    }

    public void setObjetoImg(String objetoImg) {
        this.objetoImg = objetoImg;
    }

    public String getUrlAlbum() {
        return urlAlbum;
    }

    public void setUrlAlbum(String urlAlbum) {
        this.urlAlbum = urlAlbum;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
