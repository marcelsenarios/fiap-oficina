# Roteiro do Vídeo de Demonstração

Tempo máximo: 15 minutos.

## 1. Abertura - 1 minuto

Apresentar:

- Nome do grupo.
- Participantes.
- Objetivo do MVP: back-end para gestão de atendimento e execução de serviços em oficina mecânica.

Falar brevemente:

> O sistema organiza o fluxo de clientes, veículos, ordens de serviço, orçamento, aprovação, execução, estoque e acompanhamento público da OS.

## 2. Arquitetura - 2 minutos

Mostrar a estrutura do projeto:

```text
src/main/java/com/oficina
```

Destacar:

- `api`: controllers REST.
- `application`: casos de uso e DTOs.
- `domain`: entidades, value objects, regras e serviços de domínio.
- `infrastructure`: persistência, segurança e configurações.

Mostrar também:

- `Dockerfile`
- `docker-compose.yml`
- `pom.xml`
- `docs/DDD_DOCUMENTATION.md`

## 3. Execução - 1 minuto

Executar ou mostrar o ambiente rodando:

```bash
docker compose up --build
```

Abrir:

```text
http://localhost:8080/swagger-ui.html
```

## 4. Autenticação JWT - 2 minutos

No Swagger, executar:

```http
POST /api/auth/login
```

Body:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Copiar o token e autorizar no Swagger com:

```text
Bearer <token>
```

Explicar:

- Rotas administrativas exigem JWT.
- Rotas públicas são usadas para acompanhamento/aprovação pelo cliente.

## 5. Cadastros Administrativos - 3 minutos

Criar ou mostrar:

### Cliente

```http
POST /api/clientes
```

```json
{
  "nome": "Cliente Demo",
  "cpfCnpj": "12345678909",
  "email": "cliente@demo.com",
  "telefone": "11999999999"
}
```

### Serviço

```http
POST /api/servicos
```

```json
{
  "descricao": "Troca de oleo",
  "precoBase": 120.00
}
```

### Peça

```http
POST /api/pecas
```

```json
{
  "nome": "Filtro de oleo",
  "precoUnitario": 40.00,
  "quantidadeEstoque": 10
}
```

## 6. Fluxo Completo da OS - 4 minutos

Executar:

```http
POST /api/os/completa
```

Body:

```json
{
  "cpfCnpj": "12345678909",
  "veiculo": {
    "placa": "ABC1D23",
    "marca": "Fiat",
    "modelo": "Uno",
    "ano": 2015
  },
  "servicosIds": [1],
  "pecas": [
    {
      "pecaId": 1,
      "quantidade": 2
    }
  ]
}
```

Destacar:

- Cliente identificado por CPF/CNPJ.
- Veículo cadastrado pela placa.
- Serviços e peças adicionados.
- Orçamento calculado automaticamente.
- Status alterado para `EM_DIAGNOSTICO`.

Enviar orçamento:

```http
POST /api/os/{id}/orcamento/enviar
```

Destacar status:

```text
AGUARDANDO_APROVACAO
```

Aprovar como cliente:

```http
POST /api/public/os/{id}/aprovar?cpfCnpj=12345678909
```

Destacar:

- Endpoint público.
- Status `EM_EXECUCAO`.
- Baixa automática de estoque.

Consultar peça:

```http
GET /api/pecas/{id}
```

Mostrar que o estoque diminuiu.

## 7. Acompanhamento e Métricas - 1 minuto

Consultar OS pública:

```http
GET /api/public/os?cpfCnpj=12345678909
```

Consultar tempo médio:

```http
GET /api/os/estatisticas/tempo-medio
```

## 8. Testes e Cobertura - 1 minuto

Mostrar execução:

```bash
./mvnw verify
```

Resultado esperado:

```text
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
All coverage checks have been met.
```

Mostrar o relatório:

```text
target/site/jacoco/index.html
```

## 9. Vulnerabilidades - 30 segundos

Mostrar o comando configurado:

```bash
NVD_API_KEY=<sua-chave> ./mvnw -Psecurity-scan verify
```

Explicar que o relatório é gerado em:

```text
target/dependency-check-report.html
```

## 10. Encerramento - 30 segundos

Concluir:

> O MVP atende os fluxos obrigatórios do desafio, com DDD, segurança JWT, documentação Swagger, Docker Compose, testes automatizados, cobertura mínima e profile de análise de vulnerabilidades.
