package com.lucas.api_music.model.dto;

public class AlbumImageResponse {

    private Long id;
    private String url;

    public AlbumImageResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}

