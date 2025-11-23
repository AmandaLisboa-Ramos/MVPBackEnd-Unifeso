# Módulo de Avaliações de Pontos Turísticos

## 📋 Visão Geral

Este é um módulo independente desenvolvido em **Java com Spring Boot** para gerenciar avaliações de pontos turísticos. O módulo faz parte de um projeto maior em desenvolvimento colaborativo com uma equipe.

### Status do Projeto
✅ **Completo e Funcional** - Pronto para integração com o projeto principal da equipe

### Data de Desenvolvimento
23 de Novembro de 2025

---

## 🎯 Objetivo

Desenvolver apenas o módulo responsável pelo endpoint de Avaliações de Pontos Turísticos, que será posteriormente integrado com endpoints de:
- Usuários (desenvolvido por outros integrantes)
- Pontos Turísticos (desenvolvido por outros integrantes)
- Agendamentos (desenvolvido por outros integrantes)

---

## 🏗️ Estrutura do Projeto

```
src/main/java/com/avaliacoes/
├── controller/
│   └── AvaliacaoController.java      # API REST - 6 endpoints
├── service/
│   └── AvaliacaoService.java         # Lógica de negócio e validações
├── repository/
│   └── AvaliacaoRepository.java      # Acesso ao banco de dados
├── dto/
│   ├── AvaliacaoRequestDTO.java      # DTO de entrada
│   └── AvaliacaoResponseDTO.java     # DTO de saída
├── entity/
│   └── Avaliacao.java                # Entidade JPA
├── exception/
│   ├── ResourceNotFoundException.java    # Exceção customizada
│   ├── InvalidDataException.java         # Exceção customizada
│   └── GlobalExceptionHandler.java       # Tratamento global
└── AvaliacoesApplication.java        # Classe principal (APENAS para testes)
```

---

## 🚀 Tecnologias Utilizadas

- **Java 17** com GraalVM 22.3
- **Spring Boot 3.2.0**
- **Spring Data JPA** - Persistência
- **Spring Web** - API REST
- **H2 Database** - Banco de dados em arquivo
- **Bean Validation** - Validação automática
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências

### ⚠️ Nota sobre Java 21
O GraalVM 22.3 disponível no ambiente suporta apenas Java 11, 17 e 19 (deprecado). Por isso, o projeto foi configurado com **Java 17** ao invés de Java 21. Para usar Java 21, seria necessário atualizar o ambiente para GraalVM mais recente.

### 📝 Estilo de Código
O código foi mantido **limpo e minimalista**, com documentação JavaDoc reduzida ao essencial. As classes são auto-explicativas através de nomes claros, anotações do Spring e estrutura organizada.

---

## 📡 Endpoints Disponíveis

| Método | Endpoint | Descrição | Status Code |
|--------|----------|-----------|-------------|
| POST | `/avaliacoes` | Criar nova avaliação | 201 Created |
| GET | `/avaliacoes` | Listar todas as avaliações | 200 OK |
| GET | `/avaliacoes/{id}` | Buscar avaliação por ID | 200 OK / 404 Not Found |
| GET | `/avaliacoes/ponto/{pontoId}` | Listar avaliações de um ponto turístico | 200 OK |
| PUT | `/avaliacoes/{id}` | Atualizar avaliação | 200 OK / 404 Not Found |
| DELETE | `/avaliacoes/{id}` | Excluir avaliação | 204 No Content / 404 Not Found |

---

## 💾 Banco de Dados

### H2 Database
- **Modo:** Arquivo persistente (`jdbc:h2:file:./data/avaliacoes`)
- **Console Web:** http://localhost:8080/h2-console
- **Usuário:** sa
- **Senha:** (vazio)

### Tabela Avaliacoes
```sql
CREATE TABLE avaliacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ponto_turistico_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    nota INTEGER NOT NULL CHECK (nota BETWEEN 1 AND 5),
    comentario TEXT,
    data_avaliacao TIMESTAMP NOT NULL
);
```

---

## 🔄 Como Executar

### Opção 1: Usando Maven (Replit)
O workflow já está configurado e roda automaticamente:
```bash
mvn spring-boot:run
```

### Opção 2: Localmente
```bash
# Compilar o projeto
mvn clean install

# Executar
mvn spring-boot:run

# Ou executar o JAR
java -jar target/modulo-avaliacoes-1.0.0.jar
```

O servidor iniciará em: **http://localhost:8080**

---

## 🧪 Testando a API

### Criar Avaliação
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

### Listar Todas
```bash
curl http://localhost:8080/avaliacoes
```

### Buscar por ID
```bash
curl http://localhost:8080/avaliacoes/1
```

### Listar por Ponto Turístico
```bash
curl http://localhost:8080/avaliacoes/ponto/1
```

### Atualizar
```bash
curl -X PUT http://localhost:8080/avaliacoes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "pontoTuristicoId": 1,
    "usuarioId": 1,
    "nota": 4,
    "comentario": "Muito bom!"
  }'
```

### Excluir
```bash
curl -X DELETE http://localhost:8080/avaliacoes/1
```

---

## 🔗 Integração com o Projeto Principal

### Passos para Integração:

1. **Copiar os pacotes** para o projeto da equipe:
   ```
   - controller/
   - service/
   - repository/
   - dto/
   - entity/
   - exception/
   ```

2. **REMOVER** a classe `AvaliacoesApplication.java`
   - Esta classe foi criada apenas para testes independentes
   - O projeto principal já terá sua própria classe `@SpringBootApplication`

3. **Adicionar dependências** ao `pom.xml` principal:
   - spring-boot-starter-web
   - spring-boot-starter-data-jpa
   - spring-boot-starter-validation
   - h2database (ou trocar pelo banco definitivo)

4. **Ajustar pacotes** se necessário
   - De: `com.avaliacoes`
   - Para: `com.projeto.avaliacoes` (ou o pacote base do projeto)

5. **Criar relacionamentos JPA** (quando as entidades dos colegas estiverem prontas):
   ```java
   @ManyToOne
   @JoinColumn(name = "ponto_turistico_id")
   private PontoTuristico pontoTuristico;
   
   @ManyToOne
   @JoinColumn(name = "usuario_id")
   private Usuario usuario;
   ```

---

## ✅ Validações Implementadas

### Bean Validation (Request DTO):
- ✅ `pontoTuristicoId`: não nulo e positivo
- ✅ `usuarioId`: não nulo e positivo
- ✅ `nota`: não nulo, entre 1 e 5
- ✅ `comentario`: máximo 1000 caracteres (opcional)

### Service Layer:
- ✅ Validação adicional de nota (1-5)
- ✅ Inserção automática de `dataAvaliacao`
- ✅ Verificação de existência antes de atualizar/excluir

---

## 🛡️ Tratamento de Erros

### Tipos de Erro Retornados:

**404 Not Found** - Recurso não encontrado
```json
{
  "timestamp": "2025-11-23T21:15:57",
  "status": 404,
  "error": "Recurso não encontrado",
  "message": "Avaliação com ID 999 não encontrada"
}
```

**400 Bad Request** - Dados inválidos
```json
{
  "timestamp": "2025-11-23T21:15:57",
  "status": 400,
  "error": "Erro de validação",
  "errors": {
    "nota": "A nota deve estar entre 1 e 5",
    "pontoTuristicoId": "O ID do ponto turístico é obrigatório"
  }
}
```

**500 Internal Server Error** - Erro inesperado
```json
{
  "timestamp": "2025-11-23T21:15:57",
  "status": 500,
  "error": "Erro interno do servidor",
  "message": "Mensagem do erro"
}
```

---

## 📚 Documentação

- **GUIA_DE_APRESENTACAO.md** - Guia completo para apresentação ao professor
  - Explicação de cada camada
  - Fluxo de requisição
  - Roteiro para apresentação oral
  - Perguntas frequentes

- **Código fonte** - Completamente documentado com comentários JavaDoc

---

## 🎓 Conceitos Demonstrados

- ✅ Arquitetura em camadas (Controller, Service, Repository)
- ✅ API RESTful com padrões HTTP corretos
- ✅ CRUD completo
- ✅ DTOs para separação de camadas
- ✅ Bean Validation
- ✅ Spring Data JPA
- ✅ Tratamento centralizado de exceções
- ✅ Injeção de dependências
- ✅ Persistência de dados

---

## 📝 Notas Importantes

### Sobre a Classe Principal
A classe `AvaliacoesApplication.java` foi criada **APENAS para fins de teste**. Quando integrar ao projeto da equipe, esta classe deve ser **REMOVIDA**, pois o projeto principal já terá sua própria classe `@SpringBootApplication`.

### Sobre os IDs
Os campos `pontoTuristicoId` e `usuarioId` são atualmente `Long` simples. Quando integrar com as entidades dos colegas, você pode:
- Manter como está (relacionamento por ID)
- Ou criar relacionamentos JPA `@ManyToOne` completos

### Sobre o Banco de Dados
O H2 está configurado em modo **arquivo** (`jdbc:h2:file:./data/avaliacoes`), o que significa que os dados persistem mesmo após reiniciar a aplicação. Para trocar para outro banco (PostgreSQL, MySQL, etc.), basta alterar as dependências e configurações no `application.properties`.

---

## 🚀 Próximos Passos

1. ✅ Módulo de Avaliações completo
2. ⏳ Aguardando integração com módulos de Usuários e Pontos Turísticos
3. ⏳ Criar relacionamentos JPA entre as entidades
4. ⏳ Testes integrados com todo o sistema
5. ⏳ Deploy em produção

---

## 👨‍💻 Desenvolvedor

Módulo desenvolvido como parte do trabalho em equipe de desenvolvimento de sistema de pontos turísticos.

**Responsável:** Módulo de Avaliações
**Linguagem:** Java 17
**Framework:** Spring Boot 3.2.0
**Data:** Novembro 2025
