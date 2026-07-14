package com.oficina.infrastructure.email;

import com.oficina.domain.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceAdapter implements EmailService {
    
    @Override
    public void enviarEmailStatusOrdemServico(String destinatario, Long osId, String status) {
        log.info("Simulação de E-mail: Enviando e-mail para {} informando que o status da OS #{} mudou para {}.", 
                destinatario, osId, status);
    }
}
