# Documento de Entrega - Tech Challenge Fase 2

## 1. Identificação

**Nome do grupo:** CMSR  
**Projeto:** Sistema Integrado de Atendimento e Execução de Serviços para Oficina Mecânica  
**Fase:** Tech Challenge - Fase 2  

## 2. Participantes

| Nome | RM | Discord | GitHub |
|------|----|---------|--------|
| Carlos Marcel Sena Rios | RM365838 | marcelsenarios | https://github.com/marcelsenarios |

## 3. Links da Entrega

- **Repositório GitHub:** https://github.com/marcelsenarios/fiap-oficina  
- **Vídeo de Demonstração:** [Vídeo no Google Drive/YouTube](https://drive.google.com/file/d/1N2cGCmgDj0zeKW5HnWF2SmZTY45hYc5m/view?usp=sharing)  
- **Readme do Projeto:** [README.md](https://drive.google.com/file/d/18gT0BHM9lNaJf4xXLPGV-WY58tVAs69-/view?usp=sharing)  
- **Manifestos Kubernetes:** [Pasta /k8s](https://drive.google.com/drive/folders/1cX_TXzdtJ_7gpgPyc667dF7HvliyazXo?usp=sharing)  
- **Scripts Terraform:** [Pasta /infra](https://drive.google.com/drive/folders/1IFoqyKx5LKfGt-WlRPE-hSVlk8GbBU_t?usp=sharing)  

---

## 4. Objetivo da Fase 2

O objetivo desta fase foi evoluir a aplicação monolítica de gerenciamento de oficina mecânica desenvolvida na Fase 1 para garantir maior qualidade de código, resiliência, e escalabilidade em nuvem. As metas principais foram:
1. **Refatoração estrutural** aplicando Clean Code e Clean Architecture (Arquitetura Hexagonal).
2. **Implementação de novas APIs** e aperfeiçoamento dos fluxos críticos de negócios (OS, Orçamento, Notificações).
3. **Provisionamento de Infraestrutura como Código (IaC)** com Terraform para provisionamento de Kubernetes (AWS EKS) e banco de dados relacional (AWS RDS).
4. **Orquestração e Escalabilidade** automática usando Kubernetes (HPA, Deployments, Services, ConfigMaps e Secrets).
5. **Automação de Deploy** com pipeline de CI/CD completa no GitHub Actions.

---

## 5. Arquitetura e Tecnologias

- **Java 21** e **Spring Boot 3.2**.
- **Spring Boot Actuator** (Adicionado para prover probes de Liveness/Readiness no Kubernetes).
- **Spring Security** e **JWT** para segurança administrativa.
- **PostgreSQL** como banco de dados em produção/nuvem.
- **H2** em memória para execução rápida de testes locais.
- **Terraform** para provisionamento de IaC na AWS.
- **Kubernetes (K8s)** para orquestração de containers.
- **GitHub Actions** para automação de esteira de CI/CD.
- **JaCoCo** para análise e validação de cobertura de testes.

---

## 6. Funcionalidades Desenvolvidas e Refatoradas

### 6.1. Refatoração Clean Architecture (Hexagonal)
A separação de camadas foi fortalecida para manter o domínio totalmente independente de frameworks externos:
- **Domínio (`com.oficina.domain`)**: Contém as entidades ricas de domínio (`OrdemServico`), value objects (`CpfCnpj`, `Placa`), regras de negócio e interfaces/ports de saída (ex: `EmailService`, repositórios).
- **Aplicação (`com.oficina.application`)**: Contém as portas de entrada/casos de uso (`OrdemServicoUseCase`) coordenando a orquestração do fluxo de negócio e DTOs de dados.
- **Adaptadores (`com.oficina.api` / `com.oficina.infrastructure`)**: Controladores REST externos, configurações do Spring Security/JWT, adaptadores de banco de dados JPA e de notificação por e-mail.

### 6.2. Novas APIs e Fluxos
- **Abertura de OS Unificada**: Endpoint `POST /api/os` agora suporta o recebimento completo de cliente (com auto-cadastro caso não exista), veículo (auto-cadastro/reuso por placa), peças e serviços de uma só vez, retornando o ID único da OS. É 100% retrocompatível com parâmetros via query strings.
- **Consulta de Status da OS**: Endpoints `GET /api/os/{id}/status` (administrativo) e `GET /api/public/os/{id}/status` (público) que retornam o status atual da OS de maneira simples.
- **Aprovação e Recusa Externa de Orçamento**: O cliente pode aprovar (`POST /api/public/os/{id}/aprovar`) ou recusar (`POST /api/public/os/{id}/recusar`) o orçamento. Caso recuse, a OS retrocede automaticamente ao status `EM_DIAGNOSTICO` para reavaliação. Adicionalmente, implementou-se o endpoint unificado `POST /api/public/os/{id}/notificacao-orcamento` para receber notificações externas de decisão.
- **Listagem Ordenada e Filtrada**: O endpoint `GET /api/os` retorna a lista de ordens de serviço ativas. Ele realiza um filtro (lógica não física) ocultando OS nos status `FINALIZADA` e `ENTREGUE`. A lista é ordenada por prioridade de status (`EM_EXECUCAO` > `AGUARDANDO_APROVACAO` > `EM_DIAGNOSTICO` > `RECEBIDA`) e data de criação (mais antigas primeiro).
- **Notificação via E-mail**: Implementou-se a porta `EmailService` e o adaptador correspondente `EmailServiceAdapter` que registra nos logs o envio simulado de um e-mail para o cliente no momento da criação da OS e a cada transição de status.

---

## 7. Qualidade, Testes e Cobertura (JaCoCo)

A suíte de testes de integração foi estendida para garantir a cobertura dos novos fluxos de cadastro automático, recusa de orçamentos, notificação de status e ordenação customizada de listagem.

Os testes foram executados localmente via Maven Wrapper:
```bash
cd oficina
./mvnw clean verify
```

**Resultado:**
- **Tests run:** 8, **Failures:** 0, **Errors:** 0, **Skipped:** 0.
- **JaCoCo:** A regra de validação de 80% de cobertura mínima de linhas no pacote `com.oficina.domain` e na classe `OrdemServicoUseCase` foi atendida com sucesso, garantindo a integridade dos domínios críticos de negócios.

---

## 8. Infraestrutura e Kubernetes (K8s)

Os manifestos estão definidos na pasta `/k8s`:
- **`configmap.yaml`**: Guarda as variáveis não confidenciais (DATABASE_URL, etc.).
- **`secrets.yaml`**: Guarda as credenciais confidenciais codificadas em base64 (senha de DB, segredo JWT, etc.).
- **`db-deployment.yaml`**: Provisiona um pod com imagem do PostgreSQL 15 e expõe o serviço na porta 5432.
- **`app-deployment.yaml`**: Instancia a imagem Docker do back-end (`marcelsenarios/fiap-oficina:latest`) com 2 réplicas redundantes, serviço do tipo `LoadBalancer` na porta 8080, além de liveness e readiness probes apontando para `/actuator/health`.
- **`hpa.yaml`**: Define o autoescalador horizontal para manter entre 2 e 10 réplicas baseadas na utilização de CPU (> 70%) ou Memória (> 80%).

---

## 9. Infraestrutura como Código (IaC) com Terraform

Os scripts Terraform em `/infra` provisionam um ambiente em nuvem real na AWS:
- **`vpc.tf`**: Cria uma VPC isolada com 2 subnets públicas (para o Load Balancer) e 2 subnets privadas (para os nós do EKS e a instância RDS PostgreSQL), além de NAT Gateways para acesso outbound seguro.
- **`rds.tf`**: Cria uma instância do Amazon RDS PostgreSQL (`db.t4g.micro`) em subnets privadas, configurando um grupo de segurança que aceita conexões unicamente vindas de dentro da VPC.
- **`eks.tf`**: Cria o cluster do Amazon EKS e associa um Node Group autoescalável (mínimo 2, máximo 5) contendo instâncias do tipo `t3.medium`.
- **`outputs.tf`**: Retorna os endpoints do banco e EKS necessários para deploy.

---

## 10. Pipeline de CI/CD (GitHub Actions)

A automação da entrega contínua está definida em `.github/workflows/ci-cd.yml` e consiste em três etapas:
1. **`build-and-test`**: Clona o repositório, instala o JDK 21 e compila/valida o projeto com `./mvnw clean verify` (incluindo testes e jaCoCo).
2. **`docker-build-push`**: Se a branch for a `main`, efetua login no Docker Hub, compila a imagem via Dockerfile multi-stage e envia com as tags `latest` e com o número do build atual.
3. **`kubernetes-deploy`**: Conecta na AWS, atualiza o contexto do `kubeconfig` para o EKS, aplica os manifestos da pasta `/k8s` e atualiza a imagem do back-end, fazendo um rolling update sem indisponibilidade de serviço.
