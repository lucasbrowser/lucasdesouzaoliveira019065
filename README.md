🚀 API Music

API REST desenvolvida em Java + Spring Boot para gerenciamento de artistas, álbuns e imagens, com autenticação JWT, upload de arquivos, documentação Swagger, sincronização externa e testes automatizados.

📌 Sobre o Projeto

A API Music permite:

Cadastro e consulta de Artistas

Cadastro e consulta de Álbuns

Upload de múltiplas imagens por álbum

Autenticação via JWT

Documentação automática via Swagger / OpenAPI

Sincronização de Regionais via API externa

Rate Limit por usuário

Testes unitários e de integração

🧱 Stack Tecnológica

Java 21

Spring Boot

Spring Security

JWT

Spring Data JPA

PostgreSQL

Flyway (migrations)

MinIO (armazenamento de arquivos)

Swagger / OpenAPI

JUnit + Mockito

Bucket4j (Rate Limit)

📂 Arquitetura do Projeto
controller
service
repository
model
 ├ entity
 └ dto
security
config
exception
integration
testeconfig

🔐 Segurança

Autenticação via JWT

Rate Limit:

10 requisições por minuto por usuário

Endpoints públicos:

/api/v1/auth

Swagger

Actuator


📦 Funcionalidades
🎤 Artistas

Criar artista

Atualizar artista

Buscar artista

Listar artistas

💿 Álbuns

Criar álbum

Atualizar álbum

Buscar por ID

Listar álbuns

🖼 Upload de Imagens

Endpoint:

POST /api/v1/albums/{albumId}/images


Multipart:

files = List<MultipartFile>


Armazenamento:

MinIO Bucket configurado automaticamente

🌎 Sincronização de Regionais

Fonte externa:

https://integrador-argus-api.geia.vip/v1/regionais


Regras de sincronização:

Situação	Ação
Novo no endpoint	Inserir
Não existe mais	Inativar
Alterado	Inativa antigo + cria novo
🧪 Testes

Cobertura:

✅ Controller
✅ Service
✅ Repository
✅ Upload Service
✅ Entities

Tecnologias:

JUnit 5

Mockito

MockMvc

📘 Documentação Swagger

Após subir a aplicação:

http://localhost:8080/swagger-ui/index.html

🗄 Banco de Dados

PostgreSQL + Flyway

Exemplo Migration Regionais
CREATE TABLE regionais (
    id BIGSERIAL PRIMARY KEY,
    id_externo BIGINT NOT NULL,
    nome VARCHAR(200) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

🧾 Variáveis de Ambiente

Exemplo:

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/musicdb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres

JWT_SECRET=secrettoken

MINIO_URL=http://localhost:9000
MINIO_ACCESS_KEY=minio
MINIO_SECRET_KEY=minio123
MINIO_BUCKET=albums

▶️ Como Executar o Projeto
1️⃣ Clonar repositório
git clone https://github.com/seu-repo/api-music.git

2️⃣ Subir banco e MinIO (Docker)
docker-compose up -d

3️⃣ Rodar aplicação

Linux / Mac:

./mvnw spring-boot:run


Windows:

mvnw spring-boot:run

🧪 Rodar Testes
mvn clean test

📡 Endpoints Principais
Auth
POST /api/v1/auth/login
POST /api/v1/auth/register

Artistas
GET /api/v1/artistas
GET /api/v1/artistas/{id}
POST /api/v1/artistas
PUT /api/v1/artistas/{id}

Álbuns
GET /api/v1/albums
GET /api/v1/albums/{id}
POST /api/v1/albums
PUT /api/v1/albums/{id}

Upload Imagens
POST /api/v1/albums/{id}/images

Regionais
POST /api/v1/regionais/sync
GET /api/v1/regionais


🧠 Boas Práticas Aplicadas

Clean Architecture

DTO Pattern

Exception Handler Global

Test Configuration isolada

Upload desacoplado

Integração externa via Client

👨‍💻 Desenvolvedor

Lucas Oliveira

📄 Licença

Uso educacional / avaliação técnica.