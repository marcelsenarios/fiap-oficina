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
    @PersistenceContext
    private EntityManager entityManager;
    private static final Map<StatusOrdemServico, Set<StatusOrdemServico>> TRANSICOES_PERMITIDAS = new EnumMap<>(StatusOrdemServico.class);

    static {
        TRANSICOES_PERMITIDAS.put(StatusOrdemServico.RECEBIDA, Set.of(StatusOrdemServico.EM_DIAGNOSTICO));
        TRANSICOES_PERMITIDAS.put(StatusOrdemServico.EM_DIAGNOSTICO, Set.of(StatusOrdemServico.AGUARDANDO_APROVACAO));
        TRANSICOES_PERMITIDAS.put(StatusOrdemServico.AGUARDANDO_APROVACAO, Set.of(StatusOrdemServico.EM_EXECUCAO, StatusOrdemServico.EM_DIAGNOSTICO));
        TRANSICOES_PERMITIDAS.put(StatusOrdemServico.EM_EXECUCAO, Set.of(StatusOrdemServico.FINALIZADA));
        TRANSICOES_PERMITIDAS.put(StatusOrdemServico.FINALIZADA, Set.of(StatusOrdemServico.ENTREGUE));
        TRANSICOES_PERMITIDAS.put(StatusOrdemServico.ENTREGUE, Set.of());
    }

    @Transactional
    public OrdemServico criarOrdemServico(Cliente cliente, Veiculo veiculo) {
        OrdemServico os = OrdemServico.builder()
                .cliente(cliente)
                .veiculo(veiculo)
                .status(StatusOrdemServico.RECEBIDA)
                .dataCriacao(LocalDateTime.now())
                .valorTotal(BigDecimal.ZERO)
                .build();
        return repository.save(os);
    }

    @Transactional
    public void adicionarServico(OrdemServico os, Servico servico) {
        if (os.getStatus() != StatusOrdemServico.RECEBIDA && os.getStatus() != StatusOrdemServico.EM_DIAGNOSTICO) {
            throw new BusinessException("Não é possível adicionar serviços nesta fase da OS.");
        }
        OrdemServicoServico osServico = OrdemServicoServico.builder()
                .ordemServico(os)
                .servico(servico)
                .precoCobrado(servico.getPrecoBase())
                .build();
        os.getServicos().add(osServico);
        entityManager.persist(osServico);
        os.calcularValorTotal();
        moverParaDiagnosticoAoMontarOrcamento(os);
        repository.save(os);
    }

    @Transactional
    public void adicionarPeca(OrdemServico os, Peca peca, Integer quantidade) {
        if (os.getStatus() != StatusOrdemServico.RECEBIDA && os.getStatus() != StatusOrdemServico.EM_DIAGNOSTICO) {
            throw new BusinessException("Não é possível adicionar peças nesta fase da OS.");
        }
        if (quantidade == null || quantidade <= 0) {
            throw new BusinessException("Quantidade de peças deve ser maior que zero.");
        }
        if (peca.getQuantidadeEstoque() < quantidade) {
            throw new BusinessException("Estoque insuficiente para a peça: " + peca.getNome());
        }
        
        OrdemServicoPeca osPeca = OrdemServicoPeca.builder()
                .ordemServico(os)
                .peca(peca)
                .quantidade(quantidade)
                .precoUnitarioCobrado(peca.getPrecoUnitario())
                .build();
        
        os.getPecas().add(osPeca);
        entityManager.persist(osPeca);
        os.calcularValorTotal();
        moverParaDiagnosticoAoMontarOrcamento(os);
        repository.save(os);
    }

    @Transactional
    public void enviarOrcamento(OrdemServico os) {
        if (os.getServicos().isEmpty() && os.getPecas().isEmpty()) {
            throw new BusinessException("Não é possível enviar orçamento sem serviços ou peças.");
        }
        if (os.getStatus() == StatusOrdemServico.RECEBIDA) {
            os.setStatus(StatusOrdemServico.EM_DIAGNOSTICO);
        }
        atualizarStatus(os, StatusOrdemServico.AGUARDANDO_APROVACAO);
    }

    @Transactional
    public void aprovarOrcamento(OrdemServico os) {
        atualizarStatus(os, StatusOrdemServico.EM_EXECUCAO);
    }

    @Transactional
    public void atualizarStatus(OrdemServico os, StatusOrdemServico novoStatus) {
        validarTransicao(os.getStatus(), novoStatus);

        if (novoStatus == StatusOrdemServico.EM_EXECUCAO && os.getStatus() == StatusOrdemServico.AGUARDANDO_APROVACAO) {
            for (OrdemServicoPeca osPeca : os.getPecas()) {
                Peca peca = osPeca.getPeca();
                if (peca.getQuantidadeEstoque() < osPeca.getQuantidade()) {
                    throw new BusinessException("Estoque insuficiente para iniciar execução: " + peca.getNome());
                }
                peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - osPeca.getQuantidade());
                pecaRepository.save(peca);
            }
        }
        
        if (novoStatus == StatusOrdemServico.FINALIZADA) {
            os.setDataFinalizacao(LocalDateTime.now());
        }
        
        os.setStatus(novoStatus);
        repository.save(os);
    }

    private void validarTransicao(StatusOrdemServico statusAtual, StatusOrdemServico novoStatus) {
        if (statusAtual == novoStatus) {
            return;
        }

        Set<StatusOrdemServico> permitidos = TRANSICOES_PERMITIDAS.getOrDefault(statusAtual, Set.of());
        if (!permitidos.contains(novoStatus)) {
            throw new BusinessException("Transição de status inválida: " + statusAtual + " -> " + novoStatus);
        }
    }

    private void moverParaDiagnosticoAoMontarOrcamento(OrdemServico os) {
        if (os.getStatus() == StatusOrdemServico.RECEBIDA) {
            os.setStatus(StatusOrdemServico.EM_DIAGNOSTICO);
        }
    }
}
