-- ARTISTAS
INSERT INTO artistas (nome, tipo) VALUES
('Serj Tankian', 'SOLO'),
('Mike Shinoda', 'SOLO'),
('Michel Teló', 'SOLO'),
('Guns N’ Roses', 'BANDA');


-- ÁLBUNS
INSERT INTO albuns (titulo, artista, ano_lancamento) VALUES
('Harakiri', 1, 2012),
('Black Blooms', 1, 2021),
('The Rough Dog', 1, 2024),

('The Rising Tied', 2, 2005),
('Post Traumatic', 2, 2018),
('Post Traumatic EP', 2, 2018),
('Where’d You Go', 2, 2006),

('Bem Sertanejo', 3, 2014),
('Bem Sertanejo - O Show (Ao Vivo)', 3, 2015),
('Bem Sertanejo - (1ª Temporada) - EP', 3, 2014),

('Use Your Illusion I', 4, 1991),
('Use Your Illusion II', 4, 1991),
('Greatest Hits', 4, 2004);


-- USUÁRIO PADRÃO (senha: admin123)
INSERT INTO usuarios (login, senha, role)
VALUES (
    'admin',
    '$2a$10$7QY7zGZ8KQH6qK6...', -- bcrypt
    'ADMIN'
);
