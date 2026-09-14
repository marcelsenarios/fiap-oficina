# FIAP Oficina - Aplicação Back-end Principal

Repositório da **Aplicação Principal Back-end (Java 21 / Spring Boot 3.2)** para gestão completa de ordens de serviço, insumos, clientes e veículos da oficina mecânica, refatorada em **Arquitetura Hexagonal (Clean Architecture)** e instrumentada com **Logs JSON Estruturados e Observabilidade (Micrometer/Actuator)**.

---

## 1. Propósito e Arquitetura

```mermaid
graph TD
    subgraph Adaptadores de Entrada (Drivers)
        REST1[REST Controllers /api/os]
        REST2[REST Controllers /api/public]
    end

    subgraph Portas de Entrada (Use Cases)
        UC1[OrdemServicoUseCase]
        UC2[ClienteUseCase]
    end

    subgraph Núcleo do Domínio (Domain)
        D1[Entidades: OrdemServico, Cliente, Veiculo, Peca, Servico]
        D2[Domain Service: OrdemServicoDomainService]
        D3[Value Objects: CpfCnpj, Placa]
        D4[Interfaces/Ports: Repositories, EmailService]
    end

    subgraph Adaptadores de Saída (Driven)
        JPA[JPA Persistence Adapter]
        LOG[Logstash / JSON Logger Adapter]
    end

    subgraph Infraestrutura Externa
        DB[(AWS RDS PostgreSQL)]
        OBS[CloudWatch / OpenTelemetry]
    end

    REST1 & REST2 --> UC1
    UC1 --> D2
    D2 --> D1
    D2 --> D4
    JPA -.->|Implementa| D4
    LOG -.->|Implementa| D4
    JPA --> DB
    LOG --> OBS
```

---

## 2. Tecnologias Utilizadas

- **Linguagem**: Java 21 LTS
- **Framework**: Spring Boot 3.2.5 (Web, Data JPA, Security, Actuator, Validation)
- **Documentação de API**: Springdoc OpenAPI / Swagger UI
- **Logs Estruturados**: Logstash Logback Encoder (JSON Format)
- **Testes & Cobertura**: JUnit 5, Mockito, H2 Database e JaCoCo (>= 80% nos domínios críticos)
- **Segurança**: OWASP Dependency-Check & JWT Validation

---

## 3. Instruções de Execução e Testes

### Execução dos Testes e Cobertura (JaCoCo)
```bash
./mvnw clean verify
```

### Execução Local com Docker Compose
```bash
docker compose up --build
```
- API Base: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Healthcheck Probes: `http://localhost:8080/actuator/health`

---

## 4. Endpoints Principais

- `POST /api/auth/login`: Autenticação e obtenção do Token JWT.
- `POST /api/os`: Abertura de Ordem de Serviço unificada (cliente, veículo, serviços, peças).
- `GET /api/os`: Listagem priorizada de OSs ativas.
- `GET /api/public/os/{id}/status`: Consulta pública de status da OS.
- `POST /api/public/os/{id}/aprovar`: Aprovação pública de orçamento pelo cliente.
- `POST /api/public/os/{id}/recusar`: Recusa pública de orçamento (OS retorna para `EM_DIAGNOSTICO`).

---

## 5. Pipeline de CI/CD (GitHub Actions)

A pipeline está configurada em `.github/workflows/ci-cd.yml`:
1. Compilação, testes unitários/integrados e verificação de regra de JaCoCo (80%).
2. Build multi-stage da imagem Docker e push para o Docker Hub (`marcelsenarios/fiap-oficina-app:latest`).
3. Deploy em formato Rolling Update no cluster EKS sem indisponibilidade.

---

## 6. Colaboradores Obrigatórios

- `soat-architecture` (Incluso nas permissões do repositório)
