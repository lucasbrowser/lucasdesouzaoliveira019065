\connect musicadb;

CREATE SCHEMA IF NOT EXISTS public;
GRANT ALL ON SCHEMA public TO music;
ALTER SCHEMA public OWNER TO music;