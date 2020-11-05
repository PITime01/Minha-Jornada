create TABLE IF NOT EXISTS "motorista" (
"id" bigserial NOT NULL PRIMARY KEY,
"nome" character varying (50) NOT NULL,
sobrenome character varying (100) NOT NULL,
"categoria_habilitada" character varying (15) NOT NULL,
"ano_admissão" integer NOT NULL,
"telefone" numeric,
"sindicato" character varying (50) NOT NULL,
"email" character varying (100) NOT NULL
);