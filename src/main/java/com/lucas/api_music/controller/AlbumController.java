package com.lucas.api_music.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.api_music.model.entity.Album;
import com.lucas.api_music.service.AlbumService;


@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @PostMapping
    public Album criar(@RequestBody Album album) {
        return service.salvar(album);
    }

    @GetMapping
    public Page<Album> listar(Pageable pageable) {
        return service.listar(pageable);
    }
}
