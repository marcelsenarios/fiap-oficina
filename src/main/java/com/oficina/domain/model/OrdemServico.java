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
