package com.oficina.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PecaDTO {
    private Long id;
    private String nome;
    private BigDecimal precoUnitario;
    private Integer quantidadeEstoque;
}
