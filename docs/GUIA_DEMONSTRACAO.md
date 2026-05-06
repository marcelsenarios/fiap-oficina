# Guia de Execução e Roteiro de Demonstração

Este guia auxilia na execução local do projeto e fornece um roteiro estruturado para a gravação do vídeo de entrega do Tech Challenge (Fase 1).

---

## 1. Como Rodar o Projeto

### Opção A: Via Docker (Recomendado)
Esta opção sobe o banco de dados e a aplicação automaticamente.
1. Abra o terminal na pasta raiz do projeto (`oficina/`).
2. Verifique se Docker e Compose estão instalados:
   ```bash
   docker --version
   docker compose version
   ```
   Se `docker compose` não existir, instale o plugin `docker-compose-v2` ou use uma instalação compatível de `docker-compose`.
3. Execute o comando:
   ```bash
   docker compose up --build
   ```
4. Aguarde o log: `Started OficinaApplication in ... seconds`.
5. Acesse o Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Opção B: Via Maven (Local)
1. Instale JDK 21 e PostgreSQL.
2. Certifique-se de ter um PostgreSQL rodando e crie um banco chamado `oficina`.
3. Configure as credenciais no `application.yml` ou por variáveis `DATABASE_URL`, `DATABASE_USER` e `DATABASE_PASS`.
4. Na pasta `oficina/`, compile e rode:
   ```bash
   ./mvnw clean test
   ./mvnw spring-boot:run
   ```

### Validação Técnica
Execute a validação completa com testes e cobertura:
```bash
./mvnw verify
```
Resultado obtido: `Tests run: 7, Failures: 0, Errors: 0, Skipped: 0`.
O JaCoCo valida cobertura mínima de 80% nos domínios críticos e gera o relatório em `target/site/jacoco/index.html`.

---

## 2. Roteiro Sugerido para o Vídeo (Máx. 15 min)

### Parte 1: Introdução (2 min)
- Apresente-se (e ao grupo, se houver).
- Explique brevemente a proposta: MVP de um sistema de oficina com foco em DDD, Segurança e Qualidade.
- Mostre a estrutura de pastas seguindo o DDD (`domain`, `application`, `infrastructure`, `api`).

### Parte 2: Segurança e Autenticação (2 min)
- Abra o Swagger.
- Demonstre o endpoint `/api/auth/login`.
- Gere um token JWT enviando `username` e `password`.
- Explique que as APIs administrativas estão protegidas por este token (clique no botão "Authorize" do Swagger e cole o token).

### Parte 3: Cadastros Administrativos (CRUDs) (3 min)
- **Cliente:** Crie um cliente. Mostre a validação do `CpfCnpj` (tente um CPF inválido para mostrar o erro e use `12345678909` para o sucesso).
- **Peça:** Crie uma peça (ex: "Óleo 5W30") com preço unitário e quantidade em estoque (ex: 10 unidades).
- **Serviço:** Crie um serviço (ex: "Troca de Óleo") com preço base.

### Parte 4: Fluxo da Ordem de Serviço (OS) (5 min)
1. **Criação:** Abra uma OS vinculando o cliente e o veículo. Mostre o status inicial como `RECEBIDA`.
2. **Fluxo completo:** Use `POST /api/os/completa` para identificar o cliente por CPF/CNPJ, cadastrar ou localizar o veículo pela placa, incluir serviços e peças e gerar o orçamento automaticamente.
3. **Envio do orçamento:** Use `POST /api/os/{id}/orcamento/enviar` e mostre o status `AGUARDANDO_APROVACAO`.
4. **Aprovação pelo cliente:** Use `POST /api/public/os/{id}/aprovar?cpfCnpj=...` sem token administrativo. Destaque que a OS entra em `EM_EXECUCAO` e as peças são abatidas do estoque.
5. **Finalização:** Altere para `FINALIZADA`.

### Parte 5: Monitoramento e Encerramento (3 min)
- **Tempo Médio:** Chame o endpoint `/api/os/estatisticas/tempo-medio` e mostre o cálculo do tempo de execução baseado na OS finalizada.
- **Consulta do Cliente:** Chame `GET /api/public/os?cpfCnpj=12345678909` sem token e mostre que o cliente consegue acompanhar a OS.
- **Testes:** Mostre rapidamente o código dos testes unitários no projeto e mencione a cobertura.
- **Docker:** Mostre o arquivo `docker-compose.yml`.
- **Conclusão:** Finalize reforçando que o MVP atende a todos os requisitos do PDF.

---

## 3. Dicas de Gravação
- **Ferramentas:** Use o OBS Studio, Zoom (gravando a reunião sozinho) ou Loom.
- **Áudio:** Use um microfone para garantir clareza.
- **Resolução:** Grave em 1080p se possível para que o código fique legível.
- **Preparação:** Deixe os JSONs de exemplo prontos para copiar e colar no Swagger, economizando tempo.
