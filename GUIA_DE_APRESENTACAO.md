# 📚 GUIA DE APRESENTAÇÃO - MÓDULO DE AVALIAÇÕES

## 📋 Visão Geral

Este documento é um guia para você apresentar o módulo de avaliações de pontos turísticos para o professor. Use este roteiro para explicar claramente o que foi desenvolvido e como funciona.

---

## 🎯 O QUE FOI DESENVOLVIDO?

Desenvolvemos um **módulo independente** de avaliações de pontos turísticos usando **Java com Spring Boot**. Este módulo permite que usuários avaliem pontos turísticos com notas de 1 a 5 estrelas e comentários opcionais.

### Características Principais:
- ✅ API REST completa com operações CRUD (Criar, Ler, Atualizar, Deletar)
- ✅ Validação de dados com Bean Validation
- ✅ Armazenamento em banco de dados H2
- ✅ Arquitetura em camadas bem organizada
- ✅ Tratamento de erros centralizado
- ✅ Código completamente documentado

---

## 🏗️ ARQUITETURA DO SISTEMA

### Camadas da Aplicação

O sistema foi desenvolvido seguindo o padrão de arquitetura em camadas:

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

### 📁 Estrutura de Pacotes

```
com.avaliacoes/
├── entity/              → Entidades do banco de dados
│   └── Avaliacao.java
├── dto/                 → Objetos de transferência de dados
│   ├── AvaliacaoRequestDTO.java
│   └── AvaliacaoResponseDTO.java
├── repository/          → Acesso ao banco de dados
│   └── AvaliacaoRepository.java
├── service/             → Lógica de negócio
│   └── AvaliacaoService.java
├── controller/          → Endpoints REST
│   └── AvaliacaoController.java
├── exception/           → Tratamento de erros
│   ├── ResourceNotFoundException.java
│   ├── InvalidDataException.java
│   └── GlobalExceptionHandler.java
└── AvaliacoesApplication.java  → Classe principal (APENAS para testes)
```

---

## 📖 EXPLICAÇÃO DE CADA CAMADA

### 1️⃣ ENTITY (Entidade)

**Arquivo:** `Avaliacao.java`

**O que faz:** Representa a tabela de avaliações no banco de dados.

**Campos:**
- `id` - Identificador único (gerado automaticamente)
- `pontoTuristicoId` - Referência ao ponto turístico
- `usuarioId` - Referência ao usuário que avaliou
- `nota` - Avaliação de 1 a 5 estrelas
- `comentario` - Opinião do usuário (opcional)
- `dataAvaliacao` - Data e hora da avaliação

**Como explicar:** "A entidade é como um molde que define quais informações vamos guardar no banco de dados sobre cada avaliação."

---

### 2️⃣ DTO (Data Transfer Object)

**Arquivos:** `AvaliacaoRequestDTO.java` e `AvaliacaoResponseDTO.java`

**O que faz:** Objetos para transferir dados entre o cliente e o servidor.

**Por que usar DTOs?**
- Segurança: não expõe diretamente a entidade do banco
- Validação: pode ter regras específicas para entrada e saída
- Flexibilidade: pode ter estrutura diferente da entidade

**Request DTO:** Recebe dados do cliente (sem ID e sem data)
**Response DTO:** Envia dados ao cliente (com ID e data gerados)

**Como explicar:** "DTOs são como formulários: um formulário para receber dados do usuário (Request) e outro para mostrar os dados salvos (Response)."

---

### 3️⃣ REPOSITORY (Repositório)

**Arquivo:** `AvaliacaoRepository.java`

**O que faz:** Interface que comunica com o banco de dados usando Spring Data JPA.

**Métodos automáticos do Spring:**
- `save()` - salvar ou atualizar
- `findById()` - buscar por ID
- `findAll()` - buscar todos
- `deleteById()` - excluir por ID

**Métodos customizados criados:**
- `findByPontoTuristicoId()` - buscar avaliações de um ponto turístico
- `findByUsuarioId()` - buscar avaliações de um usuário
- `findByPontoTuristicoIdAndNota()` - buscar por ponto e nota específica

**Como explicar:** "O Repository é como um assistente que sabe conversar com o banco de dados. Pedimos para ele buscar, salvar ou apagar dados e ele faz tudo automaticamente."

---

### 4️⃣ SERVICE (Serviço)

**Arquivo:** `AvaliacaoService.java`

**O que faz:** Contém toda a lógica de negócio e validações.

**Principais métodos:**
- `criar()` - criar nova avaliação
- `listarTodas()` - listar todas as avaliações
- `buscarPorId()` - buscar uma avaliação específica
- `listarPorPontoTuristico()` - listar avaliações de um ponto
- `atualizar()` - atualizar uma avaliação
- `excluir()` - excluir uma avaliação

**Validações implementadas:**
- ✅ Nota deve estar entre 1 e 5
- ✅ Data da avaliação é inserida automaticamente
- ✅ Verificação se a avaliação existe antes de atualizar/excluir

**Como explicar:** "O Service é o cérebro do sistema. Ele aplica as regras de negócio, como validar se a nota está entre 1 e 5, e converte os dados entre DTO e Entity."

---

### 5️⃣ CONTROLLER (Controlador)

**Arquivo:** `AvaliacaoController.java`

**O que faz:** Define os endpoints da API REST que o cliente pode acessar.

**Endpoints disponíveis:**

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/avaliacoes` | Criar nova avaliação |
| GET | `/avaliacoes` | Listar todas as avaliações |
| GET | `/avaliacoes/{id}` | Buscar avaliação por ID |
| GET | `/avaliacoes/ponto/{pontoId}` | Listar avaliações de um ponto |
| PUT | `/avaliacoes/{id}` | Atualizar avaliação |
| DELETE | `/avaliacoes/{id}` | Excluir avaliação |

**Como explicar:** "O Controller é a porta de entrada da API. Ele recebe as requisições HTTP, valida os dados e chama o Service para executar as operações."

---

### 6️⃣ EXCEPTION (Exceções)

**Arquivos:** 
- `ResourceNotFoundException.java` - quando não encontra um recurso
- `InvalidDataException.java` - quando os dados são inválidos
- `GlobalExceptionHandler.java` - tratamento centralizado de erros

**O que faz:** Captura erros e retorna mensagens amigáveis ao cliente.

**Exemplos de erros tratados:**
- 404 Not Found - avaliação não encontrada
- 400 Bad Request - dados inválidos (nota fora do intervalo)
- 400 Bad Request - erros de validação (campos obrigatórios)
- 500 Internal Server Error - erros inesperados

**Como explicar:** "As exceções tratam os erros de forma elegante, retornando mensagens claras para o cliente em vez de deixar a aplicação quebrar."

---

## 🔄 FLUXO DE UMA REQUISIÇÃO

### Exemplo: Criar uma avaliação (POST /avaliacoes)

```
1. CLIENTE envia requisição HTTP POST com JSON:
   {
     "pontoTuristicoId": 5,
     "usuarioId": 10,
     "nota": 4,
     "comentario": "Lugar lindo!"
   }

2. CONTROLLER recebe a requisição
   └─→ Valida o JSON com Bean Validation
   └─→ Chama service.criar(dto)

3. SERVICE aplica as regras de negócio
   └─→ Valida se nota está entre 1-5 ✓
   └─→ Converte DTO → Entity
   └─→ Define dataAvaliacao = agora()
   └─→ Chama repository.save(avaliacao)

4. REPOSITORY salva no banco de dados
   └─→ H2 gera um ID automaticamente
   └─→ Retorna a avaliação salva

5. SERVICE converte Entity → DTO
   └─→ Retorna AvaliacaoResponseDTO

6. CONTROLLER retorna HTTP 201 Created com JSON:
   {
     "id": 1,
     "pontoTuristicoId": 5,
     "usuarioId": 10,
     "nota": 4,
     "comentario": "Lugar lindo!",
     "dataAvaliacao": "2025-11-23T21:15:57"
   }
```

**Como explicar resumido:** 
"O cliente envia dados → Controller valida → Service aplica regras → Repository salva no banco → Service converte para DTO → Controller retorna resposta."

---

## 💾 BANCO DE DADOS H2

### O que é?
H2 é um banco de dados em memória/arquivo, ideal para desenvolvimento e testes.

### Configuração:
- **URL:** `jdbc:h2:file:./data/avaliacoes`
- **Console:** `http://localhost:8080/h2-console`
- **Modo:** Arquivo (dados persistem mesmo após reiniciar)

### Tabela criada automaticamente:
```sql
CREATE TABLE avaliacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ponto_turistico_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    nota INTEGER NOT NULL,
    comentario TEXT,
    data_avaliacao TIMESTAMP NOT NULL
);
```

---

## 🔧 TECNOLOGIAS UTILIZADAS

- **Java 17** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Bean Validation** - Validação de dados
- **H2 Database** - Banco de dados
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências

---

## 📝 ROTEIRO PARA APRESENTAÇÃO ORAL (3-5 minutos)

### Introdução (30 segundos)
"Desenvolvemos um módulo de avaliações de pontos turísticos usando Java com Spring Boot. Este módulo permite que usuários avaliem pontos turísticos com notas de 1 a 5 estrelas e comentários opcionais."

### Arquitetura (1 minuto)
"O sistema segue a arquitetura em camadas:
- **Controller**: recebe as requisições HTTP
- **Service**: aplica as regras de negócio
- **Repository**: acessa o banco de dados
- **Entity**: representa os dados no banco

Também criamos DTOs para transferir dados de forma segura e tratamento de exceções para erros."

### Funcionalidades (1 minuto)
"Implementamos 6 endpoints REST:
1. Criar avaliação
2. Listar todas as avaliações
3. Buscar por ID
4. Listar avaliações de um ponto turístico específico
5. Atualizar avaliação
6. Excluir avaliação

Todas as operações incluem validações, como garantir que a nota esteja entre 1 e 5."

### Fluxo de Dados (1 minuto)
"Quando um usuário cria uma avaliação:
1. O Controller recebe e valida os dados
2. O Service aplica regras de negócio e define a data automaticamente
3. O Repository salva no banco H2
4. A resposta retorna com ID e data gerados"

### Diferenciais (30 segundos)
"O módulo está completamente documentado, usa validações robustas, trata erros de forma elegante e está pronto para integração com o restante do projeto da equipe."

### Conclusão (30 segundos)
"Este módulo está funcional, testado e pronto para ser integrado ao projeto principal. Pode ser facilmente conectado aos módulos de usuários e pontos turísticos desenvolvidos pelos outros integrantes."

---

## 🔗 INTEGRAÇÃO COM O PROJETO DA EQUIPE

### Como integrar este módulo:

1. **Copiar os pacotes** para o projeto principal:
   - `entity/`
   - `dto/`
   - `repository/`
   - `service/`
   - `controller/`
   - `exception/`

2. **REMOVER** a classe `AvaliacoesApplication.java` (ela foi criada apenas para testes)

3. **Adicionar dependências** do `pom.xml` ao pom.xml principal

4. **Ajustar o pacote base** se necessário (ex: de `com.avaliacoes` para `com.projeto.avaliacoes`)

5. **Conectar com outros módulos:**
   - Quando a equipe tiver a entidade `PontoTuristico`, pode-se criar relacionamento JPA
   - Quando a equipe tiver a entidade `Usuario`, pode-se criar relacionamento JPA

---

## 🧪 COMO TESTAR

### Usando Postman ou Insomnia:

#### 1. Criar avaliação:
```
POST http://localhost:8080/avaliacoes
Content-Type: application/json

{
  "pontoTuristicoId": 1,
  "usuarioId": 1,
  "nota": 5,
  "comentario": "Excelente ponto turístico!"
}
```

#### 2. Listar todas:
```
GET http://localhost:8080/avaliacoes
```

#### 3. Buscar por ID:
```
GET http://localhost:8080/avaliacoes/1
```

#### 4. Listar por ponto turístico:
```
GET http://localhost:8080/avaliacoes/ponto/1
```

#### 5. Atualizar:
```
PUT http://localhost:8080/avaliacoes/1
Content-Type: application/json

{
  "pontoTuristicoId": 1,
  "usuarioId": 1,
  "nota": 4,
  "comentario": "Muito bom!"
}
```

#### 6. Excluir:
```
DELETE http://localhost:8080/avaliacoes/1
```

---

## ✅ CHECKLIST DO QUE FOI IMPLEMENTADO

- [x] Entidade Avaliacao com todos os campos solicitados
- [x] DTOs de requisição e resposta
- [x] Repository com Spring Data JPA
- [x] Service com validações e conversões
- [x] Controller REST com 6 endpoints
- [x] Validações com Bean Validation
- [x] Tratamento de exceções centralizado
- [x] Configuração do H2 Database
- [x] Código completamente documentado
- [x] Estrutura modular para fácil integração

---

## 💡 DICAS PARA A APRESENTAÇÃO

1. **Mostre o código rodando** - execute o projeto e faça uma requisição ao vivo
2. **Destaque a organização** - mostre a estrutura de pastas bem organizada
3. **Explique as validações** - mostre que o sistema não aceita nota 6 ou 0
4. **Demonstre o tratamento de erros** - tente buscar um ID que não existe
5. **Mencione a integração** - explique como o módulo se conectará ao projeto da equipe

---

## 📞 PERGUNTAS QUE O PROFESSOR PODE FAZER

### "Por que usar DTOs em vez da entidade diretamente?"
**Resposta:** "DTOs aumentam a segurança, pois não expõem a estrutura interna do banco de dados. Também permitem ter validações específicas para entrada e saída de dados."

### "Como funciona o Spring Data JPA?"
**Resposta:** "O Spring Data JPA gera automaticamente as queries SQL baseado nos nomes dos métodos. Por exemplo, `findByPontoTuristicoId` gera automaticamente a query SELECT * FROM avaliacoes WHERE ponto_turistico_id = ?"

### "Por que a data é inserida automaticamente?"
**Resposta:** "Para garantir consistência e evitar que o cliente envie uma data incorreta. O servidor sempre usa a data/hora exata do momento da criação."

### "Como este módulo se integrará ao projeto dos outros?"
**Resposta:** "Basta copiar os pacotes para o projeto principal e remover a classe Application de teste. Os IDs de ponto turístico e usuário já estão prontos para serem relacionados com as entidades que os outros criaram."

---

## 🎓 CONCEITOS-CHAVE PARA MENCIONAR

- **Arquitetura em camadas** (separação de responsabilidades)
- **API RESTful** (padrões HTTP corretos)
- **CRUD completo** (Create, Read, Update, Delete)
- **Bean Validation** (validação automática de dados)
- **Spring Data JPA** (abstração do banco de dados)
- **Tratamento de exceções** (erros tratados de forma elegante)
- **DTOs** (separação entre camadas)
- **H2 Database** (banco em memória/arquivo)

---

## 🏆 CONCLUSÃO

Você desenvolveu um **módulo completo, funcional e bem estruturado** de avaliações de pontos turísticos. O código está **documentado, validado e pronto para produção**. Este módulo demonstra conhecimento sólido em:

- Java e Spring Boot
- Arquitetura de software
- Desenvolvimento de APIs REST
- Persistência de dados
- Boas práticas de programação

**Boa apresentação! 🚀**
