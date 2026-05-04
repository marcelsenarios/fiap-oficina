# Documentação DDD - Sistema de Gestão de Oficina

Esta documentação detalha a modelagem de domínio aplicada ao projeto, conforme exigido pelos requisitos da Fase 1.

## 1. Linguagem Ubíqua

| Termo | Definição |
|-------|-----------|
| **Ordem de Serviço (OS)** | Entidade central que gerencia o ciclo de manutenção de um veículo. |
| **Cliente** | Pessoa física ou jurídica proprietária do veículo e responsável pela aprovação do orçamento. |
| **Veículo** | Objeto da manutenção técnica. |
| **Peça/Insumo** | Componente físico utilizado no reparo, sujeito a controle de estoque. |
| **Serviço** | Atividade técnica (mão de obra) realizada por um mecânico. |
| **Orçamento** | Cálculo financeiro automático da soma de peças e serviços antes da execução. |
| **Status da OS** | Estados da OS: Recebida, Em diagnóstico, Aguardando aprovação, Em execução, Finalizada, Entregue. |

## 2. Event Storming (Fluxo de OS)

```mermaid
graph LR
    A[OS Criada] --> B[Entrada em Diagnóstico]
    B --> C[Peças/Serviços Adicionados]
    C --> D[Orçamento Gerado]
    D --> E[Aguardando Aprovação]
    E --> F[Aprovação Recebida]
    F --> G[Em Execução]
    G --> H[Peças Consumidas no Estoque]
    H --> I[Serviço Finalizado]
    I --> J[Veículo Entregue]
```

## 3. Diagrama de Domínio (Agregados)

```mermaid
classDiagram
    class OrdemServico {
        +Long id
        +Status status
        +LocalDateTime dataCriacao
        +BigDecimal valorTotal
        +calcularTotal()
        +atualizarStatus()
    }
    class Cliente {
        +Long id
        +String nome
        +CpfCnpj cpfCnpj
    }
    class Veiculo {
        +Long id
        +Placa placa
        +String marca
    }
    class Peca {
        +Long id
        +Integer quantidadeEstoque
        +reduzirEstoque()
    }
    class Servico {
        +Long id
        +BigDecimal precoBase
    }

    OrdemServico "1" -- "1" Cliente
    OrdemServico "1" -- "1" Veiculo
    OrdemServico "1" -- "n" OrdemServicoPeca
    OrdemServico "1" -- "n" OrdemServicoServico
    OrdemServicoPeca "*" -- "1" Peca
    OrdemServicoServico "*" -- "1" Servico
```

## 4. Contextos Delimitados
- **Contexto de Atendimento:** Gestão de Clientes, Veículos e abertura de chamados.
- **Contexto de Operação:** Execução da OS, diagnóstico e atribuição de serviços.
- **Contexto de Inventário:** Controle de estoque de peças e insumos.

## 5. Regras de Negócio Implementadas
- A OS nasce com status `RECEBIDA` e valor total zerado.
- Serviços e peças podem ser adicionados apenas em `RECEBIDA` ou `EM_DIAGNOSTICO`.
- O orçamento é calculado automaticamente pela soma de serviços e peças.
- A baixa de estoque acontece ao mudar de `AGUARDANDO_APROVACAO` para `EM_EXECUCAO`.
- Transições de status inválidas são bloqueadas pelo domínio.
- CPF/CNPJ e placa são Value Objects com validação real.
- O cliente pode acompanhar suas OS pela API pública `/api/public/os?cpfCnpj=...`.
