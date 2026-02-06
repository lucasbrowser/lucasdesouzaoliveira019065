package com.lucas.api_music.repository;

import com.lucas.api_music.model.entity.Regional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class RegionalRepositoryTest {

    @Autowired
    private RegionalRepository repository;

    @Test
    @DisplayName("Deve salvar regional")
    void deveSalvarRegional() {

        Regional regional = new Regional();
        regional.setNome("Regional Teste");
        regional.setAtivo(true);
        regional.setIdExterno(10L);

        Regional salvo = repository.save(regional);

        assertThat(salvo.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve buscar regional por id")
    void deveBuscarPorId() {

        Regional regional = new Regional();
        regional.setNome("Regional Busca");
        regional.setAtivo(true);
        regional.setIdExterno(20L);

        Regional salvo = repository.save(regional);

        Optional<Regional> encontrado = repository.findById(salvo.getId());

        assertThat(encontrado).isPresent();
    }
}

