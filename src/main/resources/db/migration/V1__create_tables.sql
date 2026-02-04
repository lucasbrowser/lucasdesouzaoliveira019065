-- ARTISTAS
CREATE TABLE artistas (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ALBUNS
CREATE TABLE albuns (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    artista BIGINT NOT NULL,
    ano_lancamento INTEGER,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_album_artista
        FOREIGN KEY (artista)
        REFERENCES artistas(id)
);

-- IMAGENS
CREATE TABLE album_imagens (
    id BIGSERIAL PRIMARY KEY,
    album BIGINT NOT NULL,
    object_img VARCHAR(255) NOT NULL,
    url_album VARCHAR(500),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_imagem_album
        FOREIGN KEY (album)
        REFERENCES albuns(id)
);

-- USUÁRIOS
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

-- REFRESH TOKEN
CREATE TABLE refresh_tokens (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token VARCHAR(255) UNIQUE NOT NULL,
    expires_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_token_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);
