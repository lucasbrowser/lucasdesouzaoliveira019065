ALTER TABLE regionais
ADD COLUMN id_externo BIGINT;

UPDATE regionais
SET id_externo = id
WHERE id_externo IS NULL;

CREATE INDEX idx_regionais_id_externo
ON regionais(id_externo);