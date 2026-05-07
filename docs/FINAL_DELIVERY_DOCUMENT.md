# Documento de Entrega - Tech Challenge Fase 1

## 1. Identificação

**Nome do grupo:** CMSR

**Projeto:** Sistema Integrado de Atendimento e Execução de Serviços para Oficina Mecânica

**Fase:** Tech Challenge - Fase 1

## 2. Participantes

| Nome | RM | Discord | GitHub |
|------|----|---------|--------|
| Carlos Marcel Sena Rios | RM365838 | marcelsenarios | https://github.com/marcelsenarios |

## 3. Links da Entrega

**Repositório privado:** https://github.com/marcelsenarios/fiap-oficina

**Documentação DDD:** [DDD_DOCUMENTATION.md](https://drive.google.com/file/d/1tRThn6TZZkQSRiwm5Bc0Rus20blHTV5R/view?usp=sharing)

**Vídeo de demonstração:** [Vídeo](https://drive.google.com/file/d/19tclslTDuTmGig_ZhtJ_xZSv9hWIxYl8/view?usp=sharing)

**Relatório de vulnerabilidade** [VULNERABILITY_REPORT.md](https://drive.google.com/file/d/1JS0XhZb2wjaoObz5si7eSbZo5cbvMgbA/view?usp=sharing)

**Readme do projeto** [README.md](https://drive.google.com/file/d/1nWA3JdteJQoT8Id_aDk1P5iTklDOsjer/view?usp=sharing)

**Outras Informações:** [OTHERS.md](https://drive.google.com/file/d/1NcD_lYjkJeID5l5VqHSMX0KX9KKOCTxH/view?usp=sharing)

## 4. Objetivo do Projeto

O projeto entrega o MVP de um back-end monolítico para uma oficina mecânica de médio porte, com foco em gestão de ordens de serviço, clientes, veículos, serviços, peças e insumos.

O sistema permite criar ordens de serviço, gerar orçamento automaticamente, enviar orçamento para aprovação, permitir aprovação pública pelo cliente, acompanhar status da OS e controlar baixa de estoque durante a execução.

## 5. Arquitetura e Tecnologias

- Java 21.
- Spring Boot 3.2.
- Spring Web.
- Spring Data JPA.
- Spring Security.
- JWT.
- PostgreSQL.
- H2 para testes.
- Swagger/OpenAPI.
- Docker e Docker Compose.
- JaCoCo.
- OWASP Dependency-Check.

## 6. Funcionalidades Implementadas

### 6.1. Criação da Ordem de Serviço

- Identificação do cliente por CPF/CNPJ.
- Cadastro ou reutilização de veículo por placa.
- Inclusão de serviços solicitados.
- Inclusão de peças e insumos.
- Orçamento gerado automaticamente pela soma de serviços e peças.
- Envio do orçamento para aprovação.

### 6.2. Acompanhamento da OS

Status suportados:

- `RECEBIDA`
- `EM_DIAGNOSTICO`
- `AGUARDANDO_APROVACAO`
- `EM_EXECUCAO`
- `FINALIZADA`
- `ENTREGUE`

O cliente pode consultar suas ordens por CPF/CNPJ pela API pública.

### 6.3. Gestão Administrativa

- CRUD de clientes.
- CRUD de veículos.
- CRUD de serviços.
- CRUD de peças e insumos.
- Controle de estoque.
- Listagem e detalhamento de ordens de serviço.
- Monitoramento do tempo médio de execução dos serviços.

### 6.4. Segurança

- Rotas administrativas protegidas por JWT.
- Login administrativo com usuário e senha.
- Consulta e aprovação pública limitadas por CPF/CNPJ.
- Validação de CPF/CNPJ.
- Validação de placa.

## 7. Qualidade e Testes

Comando utilizado:

```bash
./mvnw verify
```

Resultado:

```text
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
JaCoCo: All coverage checks have been met
```

O JaCoCo foi configurado para validar cobertura mínima de 80% nos domínios críticos.

## 8. Docker

O ambiente completo pode ser executado com:

```bash
docker compose up --build
```

Serviços disponíveis:

- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`
- PostgreSQL: `localhost:5432`

## 9. Relatório de Vulnerabilidades

O projeto possui profile Maven para execução do OWASP Dependency-Check:

```bash
NVD_API_KEY=<sua-chave> ./mvnw -Psecurity-scan verify
```

Relatórios esperados:

- `target/dependency-check-report.html`

Resultado do scan: 0 vulnerabilidades críticas ou altas encontradas nas dependências principais (Spring Boot 3.2.x, PostgreSQL Driver).

Análise dos achados: O sistema utiliza bibliotecas estáveis e atualizadas. As recomendações de segurança (JWT e Validação de VO) foram implementadas para mitigar riscos de injeção e acessos não autorizados.

## 10. Considerações Finais

O MVP implementa os fluxos principais exigidos pelo desafio, com APIs REST documentadas via Swagger, autenticação JWT para rotas administrativas, validações de dados sensíveis, testes automatizados, cobertura mínima nos domínios críticos e ambiente completo orquestrado com Docker Compose.
