package com.lucas.api_music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.api_music.model.entity.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
