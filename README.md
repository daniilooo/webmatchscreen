# 🎬 WebMatchScreen API

API REST desenvolvida em **Java 17** com **Spring Boot**, que realiza a **busca e persistência de títulos (filmes e séries)** utilizando integração com a **API externa OMDb**, **cache com Caffeine** e **banco de dados em memória (H2)**.  
O projeto é modular, performático e pronto para evoluir com segurança via **OAuth2 + JWT**.

---

## 🧠 Sumário

1. [Arquitetura](#arquitetura)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Requisitos](#requisitos)
4. [Configuração do Ambiente](#configuração-do-ambiente)
5. [Execução do Projeto](#execução-do-projeto)
6. [Endpoints Disponíveis](#endpoints-disponíveis)
7. [Fluxo de Cache e Persistência](#fluxo-de-cache-e-persistência)
8. [Próximos Passos](#próximos-passos)

---

## 🧩 Arquitetura

A aplicação segue um fluxo inteligente de **cache + persistência + integração externa**:

```
[Usuário] → [Controller /pesquisa/{titulo}]
    ↓
[Caffeine Cache] 🔹 busca se já está em memória
    ↓
[H2 Database] 🔹 se não estiver no cache, busca no banco local
    ↓
[API OMDb] 🔹 se não estiver no banco, consulta API externa
    ↓
[Persistência + Cache] 🔹 resultado é salvo no H2 e armazenado em cache (10min)
```

---

## ⚙️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|-------------|------------|
| **Java 17** | Linguagem principal |
| **Spring Boot** | Framework principal (MVC, JPA, Web, Cache) |
| **Spring Data JPA** | Acesso ao banco H2 |
| **H2 Database (in-memory)** | Banco de dados temporário, ideal para desenvolvimento |
| **Caffeine Cache** | Cache de alta performance, expira em 10 minutos |
| **Lombok** | Reduz boilerplate de getters/setters |
| **Jackson** | Serialização e desserialização JSON |
| **DotEnv Java** | Leitura de variáveis de ambiente (`.env`) |
| **OMDb API** | API pública para dados de filmes e séries |
| **Maven** | Gerenciador de dependências e build tool |

---

## 📋 Requisitos

- Java 17+
- Maven 3.8+
- Acesso à internet (para consumo da API OMDb)

---

## 🔐 Configuração do Ambiente

Crie um arquivo `.env` na raiz do projeto com suas credenciais da API OMDb:

```env
API_KEY= "Acessar o site OMBApi para solicitar a sua API Key"
API_URL=https://www.omdbapi.com/
```

> 🔒 O projeto utiliza a biblioteca **DotEnv** para ler essas variáveis de ambiente automaticamente.

---

## 🚀 Execução do Projeto

Clone o repositório e execute com Maven:

```bash
git clone https://github.com/seuusuario/webmatchscreen.git
cd webmatchscreen
mvn spring-boot:run
```

A aplicação será inicializada em:

👉 **http://localhost:8080**

O console do banco H2 ficará disponível em:

👉 **http://localhost:8080/h2-console**

**Configuração de acesso ao H2:**
```
JDBC URL: jdbc:h2:mem:webmatchdb
User: sa
Password: (deixe em branco)
```

---

## 🧠 Endpoints Disponíveis

### 🔹 Buscar título (com cache e fallback)

```
GET /pesquisa/{titulo}
```

**Descrição:**  
Busca o título informado.  
Fluxo:
1. Verifica o cache Caffeine  
2. Caso não exista, verifica o banco H2  
3. Caso não exista, busca na API externa OMDb e persiste localmente  

**Exemplo:**
```bash
GET http://localhost:8080/pesquisa/Inception
```

**Retorno:**
```json
{
  "idTitulo": 1,
  "titulo": "Inception",
  "genero": "Action, Adventure, Sci-Fi",
  "sinopse": "A thief who steals corporate secrets through the use of dream-sharing technology...",
  "tipo": "FILME",
  "anoLancamento": 2010
}
```

---

### 🔹 Limpar cache manualmente

```
GET /cache/limpar
```

**Descrição:**  
Remove todas as entradas atuais do cache Caffeine (útil para testes).

**Exemplo:**
```bash
GET http://localhost:8080/cache/limpar
```

**Retorno:**
```json
{
  "message": "🧹 Cache de títulos limpo com sucesso!"
}
```

---

### 🔹 Cadastrar título manualmente *(planejado)*

```
POST /titulos
```

**Descrição:**  
Endpoint para cadastro manual de filmes/séries diretamente no banco.

**Exemplo de corpo:**
```json
{
  "titulo": "The Batman",
  "genero": "Action, Crime",
  "sinopse": "When a sadistic killer leaves behind a trail of cryptic clues...",
  "poster": "https://...",
  "anoLancamento": 2022,
  "tipo": "FILME"
}
```

---

## ⚡ Fluxo de Cache e Persistência

```text
Requisição → Cache (Caffeine)
           → Banco H2 (Spring Data JPA)
           → API Externa (OMDb)
           → Persistência + Retorno ao Cache
```

O cache é válido por **10 minutos** (`expireAfterWrite=10m`).

---

## 🔒 Próximos Passos

1. **Implementar camada de segurança com OAuth2 + JWT**
   - Autenticação e autorização via token JWT
   - Criação de endpoint `/auth/login`
2. **Popular banco H2 com usuário `admin`**
   - Seed automático via `data.sql` na inicialização
3. **Adicionar endpoint POST /titulos**
   - Permitir cadastro manual de títulos
4. **Documentação via Swagger / OpenAPI**
   - Geração automática dos endpoints para testes

---

## 📂 Estrutura do Projeto

```
src/
 ├── main/java/com/webmatchscreen/webmatchscreen/
 │    ├── controller/
 │    │    ├── PesquisaController.java
 │    │    └── CacheController.java
 │    ├── model/
 │    │    ├── Titulo.java
 │    │    ├── TipoTitulo.java
 │    │    └── Temporada.java
 │    ├── repository/
 │    │    └── TituloRepository.java
 │    ├── service/
 │    │    └── ApiServices.java
 │    └── WebmatchscreenApplication.java
 ├── resources/
 │    ├── application.properties
 │    └── data.sql (em breve, para popular usuário admin)
 └── .env
```

---

## 👨‍💻 Autor

**Danilo Franco**  
Desenvolvedor Fullstack  
Java | Spring Boot | PHP | Angular | SQL  

---

## 🧾 Licença

Este projeto é de uso livre para fins de estudo e desenvolvimento interno.
