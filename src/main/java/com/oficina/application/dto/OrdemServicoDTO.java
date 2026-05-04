package com.oficina.application.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdemServicoDTO {
    private Long id;
    private Long clienteId;
    private Long veiculoId;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFinalizacao;
    private BigDecimal valorTotal;
    private List<OrdemServicoServicoDTO> servicos;
    private List<OrdemServicoPecaDTO> pecas;
}
