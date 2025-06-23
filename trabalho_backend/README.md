# 📋 API de Gerenciamento de Tarefas

**Desenvolvido por:** Ivan Augusto Hoinacki - RU 4620954

## Descrição

API RESTful desenvolvida em Java com Spring Boot para gerenciamento completo de tarefas. Permite criar, listar, buscar, atualizar e remover tarefas com informações detalhadas como nome, data de entrega e responsável.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **MySQL 8.0**
- **Lombok**
- **Maven**
- **JUnit 5**

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/ivanhoinacki/tarefa/
│   │   ├── controller/     # Controllers REST
│   │   ├── service/        # Camada de negócio
│   │   ├── repository/     # Repositórios JPA
│   │   ├── model/          # Entidades
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── exception/      # Tratamento de exceções
│   │   └── TarefaApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/ivanhoinacki/tarefa/
        ├── controller/     # Testes dos controllers
        └── TarefaApplicationTests.java
```

## Modelo de Dados

### Entidade Tarefa
- `id` (Long) - Identificador único
- `nome` (String) - Nome da tarefa
- `dataEntrega` (LocalDate) - Data de entrega
- `responsavel` (String) - Responsável pela tarefa
- `concluida` (Boolean) - Status de conclusão
- `dataCriacao` (LocalDateTime) - Data de criação
- `dataAtualizacao` (LocalDateTime) - Data da última atualização

## Endpoints da API

### Endpoints Principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/tarefas` | Lista todas as tarefas |
| `GET` | `/api/tarefas/{id}` | Busca tarefa por ID |
| `POST` | `/api/tarefas` | Cria nova tarefa |
| `PUT` | `/api/tarefas/{id}` | Atualiza tarefa existente |
| `DELETE` | `/api/tarefas/{id}` | Remove tarefa |

### Endpoints Adicionais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/tarefas/pendentes` | Lista tarefas pendentes |
| `GET` | `/api/tarefas/vencidas` | Lista tarefas vencidas |
| `GET` | `/api/tarefas/responsavel/{responsavel}` | Lista tarefas por responsável |
| `GET` | `/api/tarefas/status/{concluida}` | Lista tarefas por status |
| `PATCH` | `/api/tarefas/{id}/concluir` | Marca tarefa como concluída |
| `GET` | `/api/tarefas/contar/{concluida}` | Conta tarefas por status |
| `GET` | `/api/tarefas/health` | Health check da API |

## Configuração

### Pré-requisitos
- Java 17 ou superior
- MySQL 8.0 ou superior
- Maven 3.6+

### Configuração do Banco de Dados

1. **Instale e configure o MySQL**
2. **Crie o banco de dados:**
   ```sql
   CREATE DATABASE tarefa_db;
   ```

3. **Configure as credenciais no `application.properties`:**
   ```properties
   spring.datasource.username=root
   spring.datasource.password=20401359
   ```

### Executando a Aplicação

1. **Clone o repositório**
2. **Navegue até o diretório do projeto:**
   ```bash
   cd eclipse/trabalho_backend
   ```

3. **Compile o projeto:**
   ```bash
   mvn clean compile
   ```

4. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a API:**
   ```
   http://localhost:8080/api/tarefas/health
   ```

## Testes

### Executando os Testes
```bash
mvn test
```

### Testes Disponíveis
- **TarefaApplicationTests:** Teste de contexto da aplicação
- **TarefaControllerTest:** Testes unitários dos endpoints

## Exemplos de Uso

### 1. Criar Nova Tarefa (POST)

**URL:** `http://localhost:8080/api/tarefas`

**Body (JSON):**
```json
{
    "nome": "Estudar Java - Ivan Augusto Hoinacki",
    "dataEntrega": "2025-06-30",
    "responsavel": "Ivan Augusto Hoinacki - RU 4620954",
    "concluida": false
}
```

**Resposta:**
```json
{
    "id": 1,
    "nome": "Estudar Java - Ivan Augusto Hoinacki",
    "dataEntrega": "2025-06-30",
    "responsavel": "Ivan Augusto Hoinacki - RU 4620954",
    "concluida": false,
    "dataCriacao": "2024-01-15T10:30:00",
    "dataAtualizacao": "2024-01-15T10:30:00"
}
```

### 2. Listar Todas as Tarefas (GET)

**URL:** `http://localhost:8080/api/tarefas`

**Resposta:**
```json
[
    {
        "id": 1,
        "nome": "Estudar Java - Ivan Augusto Hoinacki",
        "dataEntrega": "2025-06-30",
        "responsavel": "Ivan Augusto Hoinacki - RU 4620954",
        "concluida": false,
        "dataCriacao": "2024-01-15T10:30:00",
        "dataAtualizacao": "2024-01-15T10:30:00"
    }
]
```

### 3. Buscar Tarefa por ID (GET)

**URL:** `http://localhost:8080/api/tarefas/1`

**Resposta:**
```json
{
    "id": 1,
    "nome": "Estudar Java - Ivan Augusto Hoinacki",
    "dataEntrega": "2025-06-30",
    "responsavel": "Ivan Augusto Hoinacki - RU 4620954",
    "concluida": false,
    "dataCriacao": "2024-01-15T10:30:00",
    "dataAtualizacao": "2024-01-15T10:30:00"
}
```

## Testando com Postman

### Collection do Postman

1. **Importe a collection** (se disponível)
2. **Configure a variável de ambiente:**
   - `baseUrl`: `http://localhost:8080/api`

### Exemplo de Teste Completo

1. **Health Check:**
   ```
   GET {{baseUrl}}/tarefas/health
   ```

2. **Criar Tarefa:**
   ```
   POST {{baseUrl}}/tarefas
   Content-Type: application/json
   
   {
       "nome": "Estudar Java - Ivan Augusto Hoinacki",
       "dataEntrega": "2025-06-30",
       "responsavel": "Ivan Augusto Hoinacki - RU 4620954"
   }
   ```

3. **Listar Tarefas:**
   ```
   GET {{baseUrl}}/tarefas
   ```

## 🏗️ Arquitetura

### Padrões Utilizados
- **MVC (Model-View-Controller)**
- **Repository Pattern**
- **Service Layer Pattern**
- **DTO Pattern**
- **Global Exception Handling**

### Camadas da Aplicação
1. **Controller:** Recebe requisições HTTP e retorna respostas
2. **Service:** Contém a lógica de negócio
3. **Repository:** Gerencia persistência de dados
4. **Model:** Representa entidades do banco de dados
5. **DTO:** Objetos de transferência de dados

## 🔒 Validações

- **Nome:** Obrigatório, não pode estar vazio
- **Data de Entrega:** Obrigatória, formato YYYY-MM-DD
- **Responsável:** Obrigatório, não pode estar vazio

## 📊 Logs

A aplicação utiliza logging estruturado com diferentes níveis:
- **DEBUG:** Informações detalhadas de desenvolvimento
- **INFO:** Informações gerais da aplicação
- **ERROR:** Erros e exceções

## 🚀 Deploy

### Build para Produção
```bash
mvn clean package -DskipTests
```

### Executar JAR
```bash
java -jar target/tarefa-api-0.0.1-SNAPSHOT.jar
```
 