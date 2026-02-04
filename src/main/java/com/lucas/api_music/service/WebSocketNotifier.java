package com.lucas.api_music.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.lucas.api_music.model.entity.Album;

@Component
public class WebSocketNotifier {

    private final SimpMessagingTemplate template;

    public WebSocketNotifier(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void novoAlbum(Album album) {
        template.convertAndSend("/topic/novos-albuns", album.getTitulo());
    }
}
