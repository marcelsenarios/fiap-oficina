package com.oficina.domain.service;

import com.oficina.domain.exception.BusinessException;
import com.oficina.domain.model.*;
import com.oficina.domain.repository.OrdemServicoRepository;
import com.oficina.domain.repository.PecaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrdemServicoDomainService {

    private final OrdemServicoRepository repository;
    private final PecaRepository pecaRepository;
    private final EmailService emailService;
    
    @jakarta.persistence.PersistenceContext
    private jakarta.persistence.EntityManager entityManager;

    @Transactional
    public OrdemServico criarOrdemServico(Cliente cliente, Veiculo veiculo) {
        OrdemServico os = OrdemServico.builder()
                .cliente(cliente)
                .veiculo(veiculo)
                .status(StatusOrdemServico.RECEBIDA)
                .dataCriacao(LocalDateTime.now())
                .valorTotal(BigDecimal.ZERO)
                .build();
        OrdemServico saved = repository.save(os);
        if (cliente != null && cliente.getEmail() != null) {
            emailService.enviarEmailStatusOrdemServico(cliente.getEmail(), saved.getId(), saved.getStatus().name());
        }
        return saved;
    }

    @Transactional
    public void adicionarServico(OrdemServico os, Servico servico) {
        os.adicionarServico(servico);
        entityManager.persist(os.getServicos().get(os.getServicos().size() - 1));
        repository.save(os);
    }

    @Transactional
    public void adicionarPeca(OrdemServico os, Peca peca, Integer quantidade) {
        os.adicionarPeca(peca, quantidade);
        entityManager.persist(os.getPecas().get(os.getPecas().size() - 1));
        repository.save(os);
    }

    @Transactional
    public void enviarOrcamento(OrdemServico os) {
        os.enviarOrcamento();
        repository.save(os);
    }

    @Transactional
    public void aprovarOrcamento(OrdemServico os) {
        atualizarStatus(os, StatusOrdemServico.EM_EXECUCAO);
    }

    @Transactional
    public void atualizarStatus(OrdemServico os, StatusOrdemServico novoStatus) {
        StatusOrdemServico statusAnterior = os.getStatus();
        if (novoStatus == StatusOrdemServico.EM_EXECUCAO && statusAnterior == StatusOrdemServico.AGUARDANDO_APROVACAO) {
            baixarEstoque(os);
        }
        os.atualizarStatus(novoStatus);
        repository.save(os);
        
        if (statusAnterior != novoStatus && os.getCliente() != null && os.getCliente().getEmail() != null) {
            emailService.enviarEmailStatusOrdemServico(os.getCliente().getEmail(), os.getId(), novoStatus.name());
        }
    }

    private void baixarEstoque(OrdemServico os) {
        for (OrdemServicoPeca osPeca : os.getPecas()) {
            Peca peca = osPeca.getPeca();
            if (peca.getQuantidadeEstoque() < osPeca.getQuantidade()) {
                throw new BusinessException("Estoque insuficiente para iniciar execução: " + peca.getNome());
            }
            peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - osPeca.getQuantidade());
            pecaRepository.save(peca);
        }
    }
}
