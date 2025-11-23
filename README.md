# Módulo de Avaliações de Pontos Turísticos

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![H2 Database](https://img.shields.io/badge/Database-H2-blue.svg)](https://www.h2database.com/)

## 📋 Sobre o Projeto

Este é um **módulo independente** desenvolvido em Java com Spring Boot para gerenciar avaliações de pontos turísticos. O módulo faz parte de um projeto colaborativo maior desenvolvido em equipe.

### ✨ Funcionalidades

- ✅ API REST completa com operações CRUD
- ✅ Avaliações com notas de 1 a 5 estrelas
- ✅ Comentários opcionais
- ✅ Validação de dados com Bean Validation
- ✅ Armazenamento persistente em H2 Database
- ✅ Tratamento de erros centralizado
- ✅ Arquitetura em camadas bem organizada

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+

### Executando o projeto

```bash
# Compilar o projeto
mvn clean install

# Executar
mvn spring-boot:run
```

O servidor iniciará em: **http://localhost:8080**

## 📡 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/avaliacoes` | Criar nova avaliação |
| GET | `/avaliacoes` | Listar todas as avaliações |
| GET | `/avaliacoes/{id}` | Buscar avaliação por ID |
| GET | `/avaliacoes/ponto/{pontoId}` | Listar avaliações de um ponto turístico |
| PUT | `/avaliacoes/{id}` | Atualizar avaliação |
| DELETE | `/avaliacoes/{id}` | Excluir avaliação |

## 📚 Documentação Completa

- **[GUIA_DE_APRESENTACAO.md](GUIA_DE_APRESENTACAO.md)** - Guia detalhado para apresentação ao professor
- **[replit.md](replit.md)** - Documentação técnica completa do projeto

## 🏗️ Arquitetura

```
┌─────────────────────────────────────┐
│    CONTROLLER (Camada de API)      │  ← Recebe requisições HTTP
├─────────────────────────────────────┤
│    SERVICE (Lógica de Negócio)     │  ← Validações e regras
├─────────────────────────────────────┤
│    REPOSITORY (Acesso a Dados)     │  ← Comunicação com banco
├─────────────────────────────────────┤
│    DATABASE H2 (Banco de Dados)    │  ← Armazena as avaliações
└─────────────────────────────────────┘
```

## 🧪 Exemplo de Uso

### Criar uma avaliação

```bash
curl -X POST http://localhost:8080/avaliacoes \
  -H "Content-Type: application/json" \
  -d '{
    "pontoTuristicoId": 1,
    "usuarioId": 1,
    "nota": 5,
    "comentario": "Lugar incrível!"
  }'
```

### Listar todas as avaliações

```bash
curl http://localhost:8080/avaliacoes
```

## 🔗 Integração com o Projeto Principal

Este módulo foi desenvolvido para ser facilmente integrado ao projeto principal da equipe. Consulte o arquivo **[replit.md](replit.md)** para instruções detalhadas de integração.

## 🛠️ Tecnologias

- **Java 17** com GraalVM
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database**
- **Bean Validation**
- **Lombok**
- **Maven**

## 👨‍💻 Desenvolvedor

Módulo desenvolvido como parte do trabalho em equipe de desenvolvimento de sistema de pontos turísticos.

**Data de Desenvolvimento:** Novembro 2025

---

**Status:** ✅ Completo e pronto para integração
