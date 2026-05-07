package com.oficina.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordens_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrdemServico status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataFinalizacao;

    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrdemServicoServico> servicos = new ArrayList<>();

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrdemServicoPeca> pecas = new ArrayList<>();

    public void adicionarServico(Servico servico) {
        if (this.status != StatusOrdemServico.RECEBIDA && this.status != StatusOrdemServico.EM_DIAGNOSTICO) {
            throw new com.oficina.domain.exception.BusinessException("Não é possível adicionar serviços nesta fase da OS.");
        }
        
        OrdemServicoServico osServico = OrdemServicoServico.builder()
                .ordemServico(this)
                .servico(servico)
                .precoCobrado(servico.getPrecoBase())
                .build();
        this.servicos.add(osServico);
        this.calcularValorTotal();
        
        if (this.status == StatusOrdemServico.RECEBIDA) {
            this.status = StatusOrdemServico.EM_DIAGNOSTICO;
        }
    }

    public void adicionarPeca(Peca peca, Integer quantidade) {
        if (this.status != StatusOrdemServico.RECEBIDA && this.status != StatusOrdemServico.EM_DIAGNOSTICO) {
            throw new com.oficina.domain.exception.BusinessException("Não é possível adicionar peças nesta fase da OS.");
        }
        if (quantidade == null || quantidade <= 0) {
            throw new com.oficina.domain.exception.BusinessException("Quantidade de peças deve ser maior que zero.");
        }
        if (peca.getQuantidadeEstoque() < quantidade) {
            throw new com.oficina.domain.exception.BusinessException("Estoque insuficiente para a peça: " + peca.getNome());
        }

        OrdemServicoPeca osPeca = OrdemServicoPeca.builder()
                .ordemServico(this)
                .peca(peca)
                .quantidade(quantidade)
                .precoUnitarioCobrado(peca.getPrecoUnitario())
                .build();
        this.pecas.add(osPeca);
        this.calcularValorTotal();

        if (this.status == StatusOrdemServico.RECEBIDA) {
            this.status = StatusOrdemServico.EM_DIAGNOSTICO;
        }
    }

    public void enviarOrcamento() {
        if (this.servicos.isEmpty() && this.pecas.isEmpty()) {
            throw new com.oficina.domain.exception.BusinessException("Não é possível enviar orçamento sem serviços ou peças.");
        }
        this.atualizarStatus(StatusOrdemServico.AGUARDANDO_APROVACAO);
    }

    public void aprovar() {
        this.atualizarStatus(StatusOrdemServico.EM_EXECUCAO);
    }

    public void atualizarStatus(StatusOrdemServico novoStatus) {
        validarTransicao(this.status, novoStatus);
        
        if (novoStatus == StatusOrdemServico.FINALIZADA) {
            this.dataFinalizacao = LocalDateTime.now();
        }
        
        this.status = novoStatus;
    }

    private void validarTransicao(StatusOrdemServico atual, StatusOrdemServico novo) {
        if (atual == novo) return;

        boolean valida = switch (atual) {
            case RECEBIDA -> novo == StatusOrdemServico.EM_DIAGNOSTICO;
            case EM_DIAGNOSTICO -> novo == StatusOrdemServico.AGUARDANDO_APROVACAO;
            case AGUARDANDO_APROVACAO -> novo == StatusOrdemServico.EM_EXECUCAO || novo == StatusOrdemServico.EM_DIAGNOSTICO;
            case EM_EXECUCAO -> novo == StatusOrdemServico.FINALIZADA;
            case FINALIZADA -> novo == StatusOrdemServico.ENTREGUE;
            case ENTREGUE -> false;
        };

        if (!valida) {
            throw new com.oficina.domain.exception.BusinessException("Transição de status inválida: " + atual + " -> " + novo);
        }
    }

    public void calcularValorTotal() {
        BigDecimal totalServicos = servicos.stream()
                .map(OrdemServicoServico::getPrecoCobrado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalPecas = pecas.stream()
                .map(p -> p.getPrecoUnitarioCobrado().multiply(BigDecimal.valueOf(p.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        this.valorTotal = totalServicos.add(totalPecas);
    }
}
