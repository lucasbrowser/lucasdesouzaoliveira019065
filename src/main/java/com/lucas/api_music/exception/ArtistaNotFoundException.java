package com.lucas.api_music.exception;

public class ArtistaNotFoundException extends ResourceNotFoundException {

    public ArtistaNotFoundException(Long id) {
        super("Artista não encontrado com id: " + id);
    }
}

