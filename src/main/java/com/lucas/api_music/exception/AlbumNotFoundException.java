package com.lucas.api_music.exception;

public class AlbumNotFoundException extends ResourceNotFoundException {

    public AlbumNotFoundException(Long id) {
        super("Album não encontrado com id: " + id);
    }
}

