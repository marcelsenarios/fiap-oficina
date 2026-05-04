package com.oficina.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServicoDTO {
    private Long id;
    private String descricao;
    private BigDecimal precoBase;
}
