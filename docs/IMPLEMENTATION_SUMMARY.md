# Resumo de Implementação das Pendências

Este documento registra as melhorias implementadas no projeto `oficina` após a revisão de aderência ao arquivo `15SOAT-Fase1-TechChallenge.pdf`.

## 1. Segurança e Autenticação

- O endpoint `POST /api/auth/login` passou a exigir corpo JSON com `username` e `password`.
- As credenciais administrativas foram externalizadas via variáveis:
  - `ADMIN_USERNAME`
  - `ADMIN_PASSWORD`
- O `docker-compose.yml`, `application.yml` e `application-test.yml` foram atualizados com os defaults de MVP.
- O Swagger/OpenAPI recebeu configuração Bearer JWT para facilitar a autorização das rotas administrativas.

Exemplo de login:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

## 2. Fluxo de Ordem de Serviço

Foi implementado um fluxo mais aderente ao desafio:

- `POST /api/os/completa`
  - Identifica o cliente por CPF/CNPJ.
  - Cadastra ou reutiliza o veículo pela placa.
  - Inclui serviços solicitados.
  - Inclui peças e insumos.
  - Calcula automaticamente o orçamento.

- `POST /api/os/{id}/orcamento/enviar`
  - Envia o orçamento.
  - Move a OS para `AGUARDANDO_APROVACAO`.

- `POST /api/public/os/{id}/aprovar?cpfCnpj=...`
  - Permite aprovação pelo cliente sem token administrativo.
  - Move a OS para `EM_EXECUCAO`.
  - Realiza baixa automática de estoque.

O fluxo legado por IDs (`POST /api/os?clienteId=...&veiculoId=...`) foi mantido para compatibilidade.

## 3. Regras de Domínio

Foram reforçadas as regras de negócio da OS:

- A OS inicia em `RECEBIDA`.
- Ao adicionar peça ou serviço, a OS entra automaticamente em `EM_DIAGNOSTICO`.
- Orçamento só pode ser enviado se houver peças ou serviços.
- Aprovação pública valida se o CPF/CNPJ informado pertence ao cliente da OS.
- Baixa de estoque ocorre ao iniciar execução.
- Transições inválidas continuam bloqueadas pelo domínio.

## 4. Testes e Cobertura

O projeto passou a usar JaCoCo para validar cobertura mínima nos domínios críticos.

Comando validado:

```bash
./mvnw verify
```

Resultado:

```text
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
JaCoCo: All coverage checks have been met
```

Fluxos cobertos pelos testes:

- Login administrativo válido.
- Login administrativo inválido.
- Bloqueio de rota administrativa sem token.
- Fluxo completo de OS.
- Criação completa por CPF/CNPJ.
- Envio de orçamento.
- Aprovação pública pelo cliente.
- Baixa automática de estoque.
- Consulta pública da OS por CPF/CNPJ.

## 5. Documentação Atualizada

Arquivos atualizados:

- `README.md`
- `docs/DDD_DOCUMENTATION.md`
- `docs/GUIA_DEMONSTRACAO.md`
- `docs/TECH_CHALLENGE_STEPS.md`
- `docs/VULNERABILITY_REPORT.md`

A documentação DDD agora inclui:

- Event Storming de criação e acompanhamento da OS.
- Event Storming de gestão de peças e insumos.
- Regras de negócio atualizadas.

## 6. Scan de Vulnerabilidades

Foi configurado um profile Maven para OWASP Dependency-Check:

```bash
NVD_API_KEY=<sua-chave> ./mvnw -Psecurity-scan verify
```

Observação: a execução local sem `NVD_API_KEY` foi bloqueada pela API do NVD com erro 403/404. O profile está pronto para gerar os relatórios quando uma chave NVD for informada.

Artefatos esperados:

- `target/dependency-check-report.html`
- `target/dependency-check-report.json`

## 7. Validação com Docker

O ambiente foi iniciado com:

```bash
docker compose up --build
```

Resultado:

- Aplicação iniciada na porta `8080`.
- PostgreSQL iniciado na porta `5432`.
- Swagger acessível em `http://localhost:8080/swagger-ui.html`.
- Login em `POST /api/auth/login` validado com HTTP `200`.

## 8. Status Atual

O projeto agora atende melhor aos pontos críticos identificados na revisão:

- Autenticação administrativa com credenciais.
- Fluxo de orçamento e aprovação explícito.
- Criação de OS por CPF/CNPJ com veículo, serviços e peças.
- Baixa automática de estoque após aprovação.
- Cobertura mínima automatizada nos domínios críticos.
- Documentação de entrega mais completa.
- Profile reprodutível para análise de vulnerabilidades.
