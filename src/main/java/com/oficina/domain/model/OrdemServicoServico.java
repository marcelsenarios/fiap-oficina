package com.oficina.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "os_servicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "os_id", nullable = false)
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @Column(nullable = false)
    private BigDecimal precoCobrado;
}
