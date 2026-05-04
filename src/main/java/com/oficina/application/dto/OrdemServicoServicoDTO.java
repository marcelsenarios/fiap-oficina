package com.oficina.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrdemServicoServicoDTO {
    private Long servicoId;
    private String descricao;
    private BigDecimal precoCobrado;
}
