package com.oficina.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "os_pecas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoPeca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "os_id", nullable = false)
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "peca_id", nullable = false)
    private Peca peca;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitarioCobrado;
}
