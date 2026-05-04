package com.oficina.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private CpfCnpj cpfCnpj;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;
}
