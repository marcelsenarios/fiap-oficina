package com.oficina.application.dto;

import lombok.Data;

@Data
public class VeiculoDTO {
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private Long clienteId;
}
