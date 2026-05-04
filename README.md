# Sistema de Gestão de Oficina

MVP de back-end para gestão de oficina mecânica, desenvolvido para o Tech Challenge da Fase 1 da Pós-Tech FIAP. A aplicação expõe APIs REST para cadastro administrativo, criação e acompanhamento de Ordens de Serviço, controle de peças/insumos e cálculo de orçamento.

## Visão geral

- **Stack:** Java 21, Spring Boot 3.2, Spring Web, Spring Data JPA, Spring Security, JWT, PostgreSQL, Swagger/OpenAPI e Docker.
- **Arquitetura:** monólito em camadas com separação por `api`, `application`, `domain` e `infrastructure`.
- **Banco:** PostgreSQL, escolhido pela consistência transacional, integridade referencial e aderência a sistemas administrativos.
- **Documentação da API:** Swagger em `http://localhost:8080/swagger-ui.html`.

## Funcionalidades

- CRUD administrativo de clientes, veículos, serviços e peças/insumos.
- Criação de Ordem de Serviço vinculando cliente e veículo.
- Inclusão de serviços e peças na OS.
- Cálculo automático do valor total do orçamento.
- Fluxo de status da OS: `RECEBIDA`, `EM_DIAGNOSTICO`, `AGUARDANDO_APROVACAO`, `EM_EXECUCAO`, `FINALIZADA`, `ENTREGUE`.
- Baixa de estoque ao iniciar a execução da OS.
- Consulta pública de OS por CPF/CNPJ do cliente.
- Monitoramento do tempo médio de execução.
- Autenticação JWT para rotas administrativas.
- Validação real de CPF/CNPJ e placa.

## Pré-requisitos

### Opção recomendada: Docker

Instale:

- Docker Engine ou Docker Desktop.
- Docker Compose v2.

No Ubuntu/Debian, uma instalação típica é:

```bash
sudo apt update
sudo apt install -y docker.io docker-compose-v2
sudo usermod -aG docker "$USER"
```

Depois de adicionar o usuário ao grupo `docker`, encerre a sessão e entre novamente.

Verifique a instalação:

```bash
docker --version
docker compose version
```

Se `docker compose version` retornar `docker: unknown command: docker compose`, instale o plugin Compose v2 (`docker-compose-v2`) ou use uma instalação compatível de `docker-compose`.

### Opção local: Java e PostgreSQL

Instale:

- JDK 21.
- PostgreSQL 15 ou superior.

No Ubuntu/Debian:

```bash
sudo apt update
sudo apt install -y openjdk-21-jdk postgresql postgresql-contrib
```

O projeto usa Maven Wrapper, então não é obrigatório instalar Maven globalmente.

Verifique o Java:

```bash
java -version
echo "$JAVA_HOME"
```

Se `./mvnw test` retornar `The JAVA_HOME environment variable is not defined correctly`, instale o JDK 21 e configure `JAVA_HOME`.

## Como executar com Docker

Na pasta `oficina`:

```bash
docker compose up --build
```

A aplicação ficará disponível em:

- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`
- PostgreSQL: `localhost:5432`

O `docker-compose.yml` sobe o PostgreSQL e aguarda o banco ficar saudável antes de iniciar a aplicação.

## Como executar localmente

Crie o banco:

```bash
sudo -u postgres createdb oficina
```

Configure variáveis de ambiente se necessário:

```bash
export DATABASE_URL=jdbc:postgresql://localhost:5432/oficina
export DATABASE_USER=postgres
export DATABASE_PASS=postgres
export JWT_SECRET=minha-chave-secreta-muito-segura-e-longa-para-o-jwt
export JWT_EXPIRATION_MS=86400000
```

Execute:

```bash
./mvnw spring-boot:run
```

## Testes

Na pasta `oficina`:

```bash
./mvnw test
```

Os testes usam H2 em memória pelo profile `test`, configurado em `src/test/resources/application-test.yml`.

Se a máquina não tiver Java instalado, é possível rodar os testes usando Docker:

```bash
docker run --rm -w /app -v "$PWD":/app eclipse-temurin:21-jdk-alpine sh -c "chmod +x mvnw && ./mvnw test"
```

Validação realizada neste projeto:

- `docker build -t oficina-test .`: executado com sucesso.
- `./mvnw test` dentro de container JDK 21: `Tests run: 5, Failures: 0, Errors: 0, Skipped: 0`.

Observação: ao rodar testes com volume Docker em Linux, alguns arquivos em `target/` podem ser criados com outro dono. Se isso acontecer, corrija com:

```bash
sudo chown -R "$USER":"$USER" target
```

## Autenticação

Gere um token:

```bash
curl -X POST "http://localhost:8080/api/auth/login?username=admin"
```

Use o retorno como Bearer Token:

```bash
curl -H "Authorization: Bearer SEU_TOKEN" http://localhost:8080/api/clientes
```

No Swagger, clique em **Authorize** e informe:

```text
Bearer SEU_TOKEN
```

## Fluxo básico de demonstração

1. Gerar token em `POST /api/auth/login?username=admin`.
2. Criar cliente em `POST /api/clientes`.
3. Criar veículo em `POST /api/veiculos`.
4. Criar peça em `POST /api/pecas`.
5. Criar serviço em `POST /api/servicos`.
6. Abrir OS em `POST /api/os?clienteId=1&veiculoId=1`.
7. Adicionar peça em `POST /api/os/1/pecas?pecaId=1&quantidade=2`.
8. Adicionar serviço em `POST /api/os/1/servicos?servicoId=1`.
9. Avançar status com `PATCH /api/os/1/status?status=EM_DIAGNOSTICO`.
10. Avançar para `AGUARDANDO_APROVACAO`, depois `EM_EXECUCAO`, depois `FINALIZADA` e `ENTREGUE`.
11. Consultar tempo médio em `GET /api/os/estatisticas/tempo-medio`.
12. Consultar como cliente em `GET /api/public/os?cpfCnpj=12345678909`.

## Exemplos de JSON

Cliente:

```json
{
  "nome": "João da Silva",
  "cpfCnpj": "12345678909",
  "email": "joao@email.com",
  "telefone": "11999999999"
}
```

Veículo:

```json
{
  "placa": "ABC1D23",
  "marca": "Volkswagen",
  "modelo": "Gol",
  "ano": 2020,
  "clienteId": 1
}
```

Peça:

```json
{
  "nome": "Óleo 5W30",
  "precoUnitario": 50.00,
  "quantidadeEstoque": 10
}
```

Serviço:

```json
{
  "descricao": "Troca de Óleo",
  "precoBase": 80.00
}
```

## Estrutura

```text
src/main/java/com/oficina
├── api              # Controllers REST e tratamento de exceções
├── application      # Casos de uso e DTOs
├── domain           # Entidades, Value Objects, regras e contratos
└── infrastructure   # Persistência, segurança e configuração
```

## Observações

- As rotas administrativas exigem JWT.
- As rotas `/api/auth/**`, `/api/public/**` e Swagger são públicas.
- Em ambiente produtivo, troque `JWT_SECRET` por uma chave própria com pelo menos 32 caracteres.
- O envio de orçamento ao cliente é representado no MVP pelo status `AGUARDANDO_APROVACAO`.
- A baixa de estoque ocorre quando a OS muda de `AGUARDANDO_APROVACAO` para `EM_EXECUCAO`.
