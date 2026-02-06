package com.lucas.api_music.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.api_music.model.entity.Regional;
import com.lucas.api_music.model.entity.RegionalId;

@Repository
public interface RegionalRepository extends JpaRepository<Regional, RegionalId> {
        
        Optional<Regional> findByIdExternoAndAtivoTrue(Long idExterno);

        List<Regional> findByAtivoTrue();

        Optional<Regional> findById(Long regionalId);
}

