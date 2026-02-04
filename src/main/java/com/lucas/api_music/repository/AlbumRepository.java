package com.lucas.api_music.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.Artista;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Override
    Page<Album> findAll(Pageable pageable);

    List<Album> findByArtista(Artista artista);

    Page<Album> findByArtistaId(Long artistaId, Pageable pageable);

    Page<Album> findByTituloContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    @Query("""
        SELECT a FROM Album a
        JOIN a.artista ar
        WHERE LOWER(ar.nome) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    Page<Album> findByArtistaName(
            @Param("nome") String nome,
            Pageable pageable
    );

    @Query("""
        SELECT a FROM Album a
        JOIN a.artista ar
        WHERE ar.tipo = :type
    """)
    Page<Album> findByArtistaType(
            @Param("tipo") String tipo,
            Pageable pageable
    );


    @Query("""
        SELECT a FROM Album a
        JOIN a.artista ar
        WHERE
            (:artistName IS NULL OR LOWER(ar.nome) LIKE LOWER(CONCAT('%', :artistaNome, '%')))
        AND
            (:albumName IS NULL OR LOWER(a.titulo) LIKE LOWER(CONCAT('%', :albumNome, '%')))
        AND
            (:tipo IS NULL OR ar.tipo = :tipo)
    """)
    Page<Album> searchAdvanced(
            @Param("artistaNome") String artistaNome,
            @Param("albumNome") String albumNome,
            @Param("tipo") String tipo,
            Pageable pageable
    );


    Page<Album> findAllByOrderByTituloAsc(Pageable pageable);

    Page<Album> findAllByOrderByTituloDesc(Pageable pageable);


}
