package com.oficina.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrdemServicoPecaDTO {
    private Long pecaId;
    private String nome;
    private Integer quantidade;
    private BigDecimal precoUnitarioCobrado;
    private BigDecimal subtotal;
}
