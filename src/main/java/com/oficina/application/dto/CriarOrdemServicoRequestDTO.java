package com.oficina.application.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class CriarOrdemServicoRequestDTO {
    private String cpfCnpj;
    private VeiculoDTO veiculo;
    private List<Long> servicosIds = new ArrayList<>();
    private List<PecaSolicitadaDTO> pecas = new ArrayList<>();
}
