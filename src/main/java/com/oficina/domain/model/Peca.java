package com.oficina.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pecas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private Integer quantidadeEstoque;
}
