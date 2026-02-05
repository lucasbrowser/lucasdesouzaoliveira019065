package com.lucas.api_music.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.model.entity.AlbumImagem;
import com.lucas.api_music.service.AlbumImagemService;
import com.lucas.api_music.service.AlbumService;



@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    private final AlbumService service;
    private final AlbumImagemService albumImagemService; 

    public AlbumController(AlbumService service, AlbumImagemService albumImagemService) {
        this.service = service;
        this.albumImagemService = albumImagemService;
    }

    @PostMapping
    public Album criar(@RequestBody Album album) {
        return service.salvar(album);
    }

    @GetMapping
    public Page<Album> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizar(@PathVariable Long id, @RequestBody Album album) {
        return ResponseEntity.ok(service.atualizar(id, album));
    }

    @PostMapping(value = "/{albumId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AlbumImagem> uploadImagem(
            @PathVariable Long albumId,
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        return ResponseEntity.ok(albumImagemService.upload(file, albumId));
    }

}
