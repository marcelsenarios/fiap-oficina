package com.oficina.application.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String telefone;
}
