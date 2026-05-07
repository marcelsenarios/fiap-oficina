# Documentação DDD - Sistema de Gestão de Oficina

Este documento detalha a modelagem de domínio (DDD) aplicada ao projeto Oficina MVP, seguindo os requisitos técnicos da Fase 1.

## 1. Linguagem Ubíqua

Abaixo, os termos centrais do domínio utilizados tanto no código quanto na comunicação com especialistas de negócio:

| Termo | Definição | Contexto |
|-------|-----------|----------|
| **Ordem de Serviço (OS)** | Documento que centraliza o ciclo de vida da manutenção de um veículo. | Operação |
| **Cliente** | Pessoa física ou jurídica (validada por CPF/CNPJ) dona do veículo. | Atendimento |
| **Veículo** | Objeto da manutenção (identificado por Placa Mercosul/Antiga). | Atendimento |
| **Peça/Insumo** | Componente físico retirado do estoque para uso no reparo. | Inventário |
| **Serviço** | Atividade técnica de mão de obra realizada pelo mecânico. | Operação |
| **Orçamento** | Proposta financeira composta pela soma de serviços e peças. | Operação |
| **Baixa de Estoque** | Redução automática da quantidade de peças ao iniciar a execução da OS. | Inventário |

---

## 2. Event Storming (Design Estratégico)

O Event Storming mapeia a linha do tempo do negócio através de Eventos (Laranja), Comandos (Azul) e Atores (Amarelo).

### 2.1. Criação e Acompanhamento da OS

```mermaid
viewContext
graph LR
    subgraph "Abertura e Diagnóstico"
        C1[Atendente] -- "Comando: Identificar Cliente" --> E1(Evento: Cliente Identificado)
        C1 -- "Comando: Vincular Veículo" --> E2(Evento: Veículo Localizado)
        C1 -- "Comando: Criar OS" --> E3(Evento: OS Recebida)
        C2[Mecânico] -- "Comando: Adicionar Serviços/Peças" --> E4(Evento: Diagnóstico Realizado)
    end

    subgraph "Orçamento e Aprovação"
        E4 --> E5(Evento: Orçamento Calculado)
        C1 -- "Comando: Enviar Orçamento" --> E6(Evento: Orçamento Enviado)
        C3[Cliente] -- "Comando: Aprovar Orçamento (via API Pública)" --> E7(Evento: Orçamento Aprovado)
    end

    subgraph "Execução e Entrega"
        E7 -- "Regra: Baixar Estoque" --> E8(Evento: Estoque Atualizado)
        E8 --> E9(Evento: OS em Execução)
        C2 -- "Comando: Finalizar OS" --> E10(Evento: OS Finalizada)
        C1 -- "Comando: Entregar Veículo" --> E11(Evento: Veículo Entregue)
    end

    style E1 fill:#ff9f43,stroke:#333
    style E2 fill:#ff9f43,stroke:#333
    style E3 fill:#ff9f43,stroke:#333
    style E4 fill:#ff9f43,stroke:#333
    style E5 fill:#ff9f43,stroke:#333
    style E6 fill:#ff9f43,stroke:#333
    style E7 fill:#ff9f43,stroke:#333
    style E8 fill:#ff9f43,stroke:#333
    style E9 fill:#ff9f43,stroke:#333
    style E10 fill:#ff9f43,stroke:#333
    style E11 fill:#ff9f43,stroke:#333
```

### 2.2. Gestão de Peças e Insumos

```mermaid
graph LR
    subgraph "Gestão de Inventário"
        A[Gestor] -- "Comando: Cadastrar Peça" --> B(Evento: Peça Cadastrada)
        B --> C(Evento: Estoque Inicial Definido)
        D[Operação OS] -- "Comando: Reservar Peça" --> E{Validar Disponibilidade}
        E -- "Sim" --> F(Evento: Peça Adicionada ao Orçamento)
        E -- "Não" --> G(Evento: Falta de Estoque Alertada)
        H[Aprovação Cliente] -- "Comando: Iniciar Execução" --> I(Evento: Baixa de Estoque Confirmada)
    end

    style B fill:#ff9f43,stroke:#333
    style C fill:#ff9f43,stroke:#333
    style F fill:#ff9f43,stroke:#333
    style G fill:#ff9f43,stroke:#333
    style I fill:#ff9f43,stroke:#333
```

---

## 3. Mapa de Contextos (Context Map)

O sistema é dividido em Bounded Contexts lógicos para garantir alta coesão.

```mermaid
graph TD
    subgraph "Contexto de Atendimento"
        A[Gestão de Clientes e Veículos]
    end

    subgraph "Contexto de Operação"
        B[Gestão de OS e Diagnósticos]
    end

    subgraph "Contexto de Inventário"
        C[Gestão de Peças e Estoque]
    end

    A -- "Upstream (Fornece Dados)" --> B
    C -- "Supplier (Fornece Insumos)" --> B
    B -- "Customer (Consome e Notifica)" --> C
```

---

## 4. Diagrama de Agregados (Design Tático)

Este diagrama detalha a estrutura das entidades e agregados implementados no código.

```mermaid
classDiagram
    class OrdemServico {
        <<Aggregate Root>>
        +Long id
        +Status status
        +LocalDateTime dataCriacao
        +BigDecimal valorTotal
        +adicionarPeca()
        +adicionarServico()
        +aprovar()
        +atualizarStatus()
    }

    class OrdemServicoPeca {
        <<Entity>>
        +Integer quantidade
        +BigDecimal precoUnitarioCobrado
    }

    class OrdemServicoServico {
        <<Entity>>
        +BigDecimal precoCobrado
    }

    class Peca {
        <<Aggregate Root>>
        +Long id
        +String nome
        +Integer quantidadeEstoque
    }

    class Cliente {
        <<Aggregate Root>>
        +Long id
        +CpfCnpj cpfCnpj
    }

    class Veiculo {
        <<Aggregate Root>>
        +Long id
        +Placa placa
    }

    OrdemServico "1" *-- "n" OrdemServicoPeca
    OrdemServico "1" *-- "n" OrdemServicoServico
    OrdemServico --> Cliente : refere-se a
    OrdemServico --> Veiculo : refere-se a
    OrdemServicoPeca --> Peca : consome
```

---

## 5. Decisões de Modelagem
- **Entidades Ricas:** A lógica de transição de status e cálculos financeiros foi movida para a classe `OrdemServico`, evitando o antipadrão de serviços anêmicos.
- **Value Objects:** `CpfCnpj` e `Placa` garantem a integridade dos dados desde a criação, impedindo estados inválidos no sistema.
- **Baixa Automática:** A regra de baixa de estoque é disparada pelo `OrdemServicoDomainService` ao detectar a transição para `EM_EXECUCAO`, garantindo atomicidade entre o status da OS e o saldo físico.
