package com.oficina.domain.service;

public interface EmailService {
    void enviarEmailStatusOrdemServico(String destinatario, Long osId, String status);
}
