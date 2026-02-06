package com.lucas.api_music.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.api_music.model.entity.Regional;
import com.lucas.api_music.service.RegionalImportService;
import com.lucas.api_music.service.RegionalService;
import com.lucas.api_music.service.RegionalSyncService;

@RestController
@RequestMapping("/api/v1/regionais")
public class RegionalController {

    private final RegionalImportService importService;
    private final RegionalService service;
    private final RegionalSyncService syncService;

    public RegionalController(
        RegionalImportService importService, 
        RegionalService service, 
        RegionalSyncService syncService) {
        this.importService = importService;
        this.service = service;
        this.syncService = syncService;
    }

    @PostMapping("/importar")
    public ResponseEntity<?> importar() {
        importService.importarRegionais();
        return ResponseEntity.ok("Importação concluída");
    }

    @PostMapping("/sync")
    public ResponseEntity<?> sincronizar() {
        syncService.sincronizar();
        return ResponseEntity.ok("Sincronização executada");
    }

    @GetMapping
    public ResponseEntity<List<Regional>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Regional> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Regional>> ativos() {
        return ResponseEntity.ok(service.buscarAtivos());
    }
}


