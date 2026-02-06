-- Criar sequence
CREATE SEQUENCE IF NOT EXISTS regionais_id_seq;

-- Garantir tipo BIGINT (caso não esteja)
ALTER TABLE regionais
ALTER COLUMN id TYPE BIGINT;

-- Vincular default auto increment
ALTER TABLE regionais
ALTER COLUMN id SET DEFAULT nextval('regionais_id_seq');

-- Vincular sequence à coluna
ALTER SEQUENCE regionais_id_seq OWNED BY regionais.id;

-- Ajustar valor inicial baseado no maior id existente
SELECT setval(
    'regionais_id_seq',
    COALESCE((SELECT MAX(id) FROM regionais), 1)
);
