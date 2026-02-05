package com.lucas.api_music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.api_music.model.entity.AlbumImagem;

public interface AlbumImagemRepository extends JpaRepository<AlbumImagem, Long> {
    List<AlbumImagem> findByAlbumId(Long albumId);
}

