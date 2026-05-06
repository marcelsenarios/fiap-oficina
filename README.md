# Sistema de Gestão de Oficina - Tech Challenge (Fase 1)

Este projeto representa o MVP de um sistema de gestão de oficina mecânica, desenvolvido como parte do **Tech Challenge da Fase 1 da Pós-Tech FIAP (Pós-Graduação em Arquitetura de Software Java)**.

## 🏛️ Arquitetura e Design

O sistema foi desenhado seguindo princípios de **Domain-Driven Design (DDD)** e organizado em uma estrutura de camadas que favorece a manutenção e a evolução:

- **api**: Porta de entrada (REST Controllers), tratamento global de exceções e documentação OpenAPI (Swagger).
- **application**: Camada de orquestração com Casos de Uso (UseCases) e objetos de transferência (DTOs).
- **domain**: O "Core" do software, contendo Entidades, Value Objects (como CPF/CNPJ e Placa com regras de validação ricas) e interfaces de Repositórios.
- **infrastructure**: Detalhes técnicos e de suporte, como persistência com Spring Data JPA, adaptadores de repositório e configurações de segurança.

---

## 📄 Documentação de Entrega

Para uma compreensão completa do projeto e conformidade com os requisitos do desafio, consulte os documentos na pasta `docs/`:

- [**Modelagem DDD**](docs/DDD_DOCUMENTATION.md): Entidades, agregados, linguagem ubíqua e mapeamento de contextos.
- [**Guia de Demonstração**](docs/GUIA_DEMONSTRACAO.md): Guia passo a passo para validar os fluxos da API no Swagger.
- [**Relatório de Vulnerabilidades**](docs/VULNERABILITY_REPORT.md): Análise de segurança e mitigação de riscos (OWASP).
- [**Decisões Técnicas**](docs/TECH_CHALLENGE_STEPS.md): Racional sobre a escolha do banco de dados e passos de implementação.
- [**Transcrição do Vídeo**](docs/TRANSCRICAO_TECH_CHALLENGE.md): Conteúdo de apoio à apresentação gravada.
- [**Checklist Final**](docs/FINAL_DELIVERY_CHECKLIST.md): Lista operacional para revisar antes de enviar.
- [**Documento Final de Entrega**](docs/FINAL_DELIVERY_DOCUMENT.md): Modelo para preencher e exportar em PDF.
- [**Roteiro do Vídeo**](docs/VIDEO_DEMO_SCRIPT.md): Roteiro objetivo para gravação da demonstração.
- [**Guia de Repositório e PDF**](docs/REPOSITORY_AND_PDF_GUIDE.md): Passos para acesso do avaliador e exportação do PDF.

---

## 🛠️ Stack Tecnológica

- **Linguagem:** Java 21 (LTS)
- **Framework:** Spring Boot 3.2
- **Segurança:** Spring Security + JWT (Autenticação Stateless)
- **Banco de Dados:** PostgreSQL (Persistência relacional robusta)
- **Documentação:** Swagger/OpenAPI 3
- **Containerização:** Docker & Docker Compose v2

---

## 🚀 Como Executar

### Via Docker (Recomendado)

Na raiz do diretório `oficina/`, execute:

```bash
docker compose up --build
```

A aplicação estará disponível nos seguintes endereços:
- **API:** `http://localhost:8080`
- **Swagger UI:** `http://localhost:8080/swagger-ui.html` (Acesso simplificado à documentação interativa)
- **PostgreSQL:** `localhost:5432`

### Execução Local

Requisitos: JDK 21 e PostgreSQL 15+.

1. Certifique-se de que um banco de dados chamado `oficina` exista no PostgreSQL.
2. Configure as variáveis de ambiente (ou aceite os defaults em `application.yml`):
   ```bash
   export JWT_SECRET=minha-chave-secreta-muito-segura-e-longa-para-o-jwt
   ```
3. Execute via Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🧪 Testes e Validação

O projeto prioriza a qualidade através de testes unitários e de integração utilizando **JUnit 5**, **AssertJ** e **H2 Database** (profile `test`).

### Executar testes localmente:
```bash
./mvnw test
```

### Executar testes via Docker (Garante isolamento):
```bash
docker run --rm -w /app -v "$PWD":/app eclipse-temurin:21-jdk-alpine sh -c "chmod +x mvnw && ./mvnw test"
```

---

## 🔐 Segurança e Autenticação

O sistema utiliza **JWT (JSON Web Token)** para proteger rotas administrativas.

1. **Obter Token:** Realize um `POST` em `/api/auth/login`.
   ```json
   {
     "username": "admin",
     "password": "admin123"
   }
   ```
2. **Autorizar no Swagger:** Clique no botão **Authorize** e insira o valor no formato: `Bearer SEU_TOKEN_AQUI`.

> **Nota:** As rotas de consulta pública (`/api/public/**`) e a documentação do Swagger são acessíveis sem autenticação, conforme os requisitos de negócio para acompanhamento de clientes.

## 🔄 Fluxo principal da OS

- `POST /api/os/completa`: cria a OS identificando o cliente por CPF/CNPJ, cadastra ou reaproveita o veículo pela placa, inclui serviços e peças e calcula o orçamento.
- `POST /api/os/{id}/orcamento/enviar`: envia o orçamento e move a OS para `AGUARDANDO_APROVACAO`.
- `POST /api/public/os/{id}/aprovar?cpfCnpj=...`: permite a aprovação pelo cliente e inicia a execução, com baixa automática de estoque.

O fluxo legado por IDs (`POST /api/os?clienteId=...&veiculoId=...`) continua disponível.
