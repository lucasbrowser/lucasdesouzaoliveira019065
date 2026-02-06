package com.lucas.api_music.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionalImportService {

    private final RegionalSyncService syncService;

    public RegionalImportService(RegionalSyncService syncService) {
        this.syncService = syncService;
    }

    @Transactional
    public void importarRegionais() {
        syncService.sincronizar();
    }
}


