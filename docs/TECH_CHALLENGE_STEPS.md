# Tech Challenge - Sistema de Gestão de Oficina (MVP)

Este documento descreve os passos realizados para a entrega do desafio da Fase 1 da Pós-Tech FIAP.

## 1. Análise de Requisitos e Modelagem DDD

### 1.1. Linguagem Ubíqua
- **Cliente:** Pessoa física (CPF) ou jurídica (CNPJ) que contrata os serviços.
- **Veículo:** Objeto da manutenção (Placa, Marca, Modelo, Ano).
- **Serviço:** Atividade técnica realizada (ex: Troca de óleo, Alinhamento).
- **Peça/Insumo:** Componente utilizado na manutenção, com controle de estoque.
- **Ordem de Serviço (OS):** Documento que agrupa cliente, veículo, serviços e peças, gerando um orçamento.
- **Status da OS:** Ciclo de vida da OS (Recebida -> Em diagnóstico -> Aguardando aprovação -> Em execução -> Finalizada -> Entregue).

### 1.2. Mapeamento de Domínios
- **Domínio de Clientes e Veículos:** Gestão de cadastros.
- **Domínio de Inventário:** Peças, insumos e estoque.
- **Domínio de Serviços:** Catálogo de serviços disponíveis.
- **Domínio de Atendimento (Core):** Gestão de Ordens de Serviço e Orçamentos.

### 1.3. Justificativa do Banco de Dados
- **PostgreSQL:** Escolhido por ser um banco de dados relacional robusto, com suporte nativo a tipos de dados complexos, excelente integridade referencial e amplamente utilizado em ambientes produtivos que exigem segurança e consistência.

---

## 2. Estrutura do Projeto
A aplicação seguirá uma arquitetura em camadas organizada por domínios:
- `com.oficina.domain`: Entidades, Value Objects e Interfaces de Repositório.
- `com.oficina.application`: Casos de uso e DTOs.
- `com.oficina.infrastructure`: Implementações de persistência, segurança e configurações externas.
- `com.oficina.api`: Controllers REST e documentação.

---

## 3. Próximos Passos
- [x] Ajustar `pom.xml` com dependências de validação, JWT e Swagger.
- [x] Implementar Entidades e Value Objects.
- [x] Configurar Spring Security e JWT com credenciais administrativas.
- [x] Implementar APIs de CRUD completo e fluxo de OS.
- [x] Implementar consulta pública de OS para acompanhamento pelo cliente.
- [x] Implementar criação completa da OS por CPF/CNPJ, veículo, serviços e peças.
- [x] Implementar envio e aprovação de orçamento pelo cliente.
- [x] Desenvolver Testes Unitários e de Integração.
- [x] Configurar JaCoCo com cobertura mínima de 80% nos domínios críticos.
- [x] Configurar Docker.

## 4. Como Executar

### 4.1. Localmente (com Maven)
1. Instale JDK 21 e PostgreSQL.
2. Certifique-se de ter o PostgreSQL rodando.
3. Configure as variáveis de ambiente ou ajuste o `application.yml`.
4. Execute `./mvnw test`.
5. Execute `./mvnw spring-boot:run`.

### 4.2. Via Docker
1. Instale Docker Engine/Desktop e Docker Compose v2.
2. Verifique com `docker --version` e `docker compose version`.
3. Execute `docker compose up --build`.
4. A aplicação estará disponível em `http://localhost:8080`.
5. O Swagger estará em `http://localhost:8080/swagger-ui.html`.

### 4.3. Validação Realizada
- Build Docker validado com `docker build -t oficina-test .`.
- Testes e cobertura executados com `./mvnw verify`.
- Resultado dos testes: `Tests run: 7, Failures: 0, Errors: 0, Skipped: 0`.
- Resultado de cobertura: regra JaCoCo de 80% nos domínios críticos atendida. Relatório gerado em `target/site/jacoco/index.html`.
- Observação de ambiente: se a máquina local não tiver Java/JDK 21 ou `JAVA_HOME`, o Maven Wrapper não executa; nesse caso, instale o JDK 21 ou use o container Docker para rodar os testes.

## 5. Fluxos Principais
1. **Autenticação:** POST `/api/auth/login` com `{"username":"admin","password":"admin123"}` para obter o token JWT.
2. **Cliente:** POST `/api/clientes` (enviar JSON com nome, cpfCnpj, etc).
3. **Veículo:** POST `/api/veiculos` vinculando o `clienteId`.
4. **Ordem de Serviço:** POST `/api/os?clienteId=1&veiculoId=1` para iniciar.
5. **Orçamento:** POST `/api/os/1/servicos` e POST `/api/os/1/pecas`.
6. **Status:** PATCH `/api/os/1/status?status=EM_DIAGNOSTICO`.
7. **Consulta do cliente:** GET `/api/public/os?cpfCnpj=12345678909`.
8. **Fluxo completo:** POST `/api/os/completa` para identificar cliente por CPF/CNPJ, cadastrar veículo, incluir serviços e peças e gerar o orçamento.
9. **Envio do orçamento:** POST `/api/os/1/orcamento/enviar`.
10. **Aprovação pelo cliente:** POST `/api/public/os/1/aprovar?cpfCnpj=12345678909`.

## 6. APIs Implementadas

### Administrativas, protegidas por JWT
- `/api/clientes`: `POST`, `GET`, `GET /{id}`, `PUT /{id}`, `DELETE /{id}`.
- `/api/veiculos`: `POST`, `GET`, `GET /{id}`, `PUT /{id}`, `DELETE /{id}`.
- `/api/servicos`: `POST`, `GET`, `GET /{id}`, `PUT /{id}`, `DELETE /{id}`.
- `/api/pecas`: `POST`, `GET`, `GET /{id}`, `PUT /{id}`, `DELETE /{id}`.
- `/api/os`: criação simples, criação completa, listagem, detalhamento, exclusão, inclusão de peças/serviços, envio de orçamento, alteração de status e estatísticas.

### Públicas
- `/api/auth/login`: geração de token JWT administrativo com usuário e senha.
- `/api/public/os?cpfCnpj=...`: acompanhamento de ordens de serviço pelo cliente.
- `/api/public/os/{id}/aprovar?cpfCnpj=...`: aprovação do orçamento pelo cliente.
