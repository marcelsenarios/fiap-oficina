package com.oficina.application.usecase;

import com.oficina.application.dto.OrdemServicoDTO;
import com.oficina.application.dto.OrdemServicoPecaDTO;
import com.oficina.application.dto.OrdemServicoServicoDTO;
import com.oficina.domain.exception.BusinessException;
import com.oficina.domain.model.*;
import com.oficina.domain.repository.*;
import com.oficina.domain.service.OrdemServicoDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdemServicoUseCase {
    private final OrdemServicoDomainService domainService;
    private final OrdemServicoRepository repository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final PecaRepository pecaRepository;
    private final ServicoRepository servicoRepository;

    @Transactional
    public OrdemServicoDTO criar(Long clienteId, Long veiculoId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado: " + clienteId));
        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new BusinessException("Veículo não encontrado: " + veiculoId));

        if (!veiculo.getCliente().getId().equals(cliente.getId())) {
            throw new BusinessException("Veículo não pertence ao cliente informado.");
        }

        OrdemServico os = domainService.criarOrdemServico(cliente, veiculo);
        return toDTO(os);
    }

    @Transactional
    public void atualizarStatus(Long id, String status) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + id));
        try {
            domainService.atualizarStatus(os, StatusOrdemServico.valueOf(status));
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("Status inválido: " + status);
        }
    }

    @Transactional
    public void adicionarPeca(Long osId, Long pecaId, Integer quantidade) {
        OrdemServico os = repository.findById(osId)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + osId));
        Peca peca = pecaRepository.findById(pecaId)
                .orElseThrow(() -> new BusinessException("Peça não encontrada: " + pecaId));
        domainService.adicionarPeca(os, peca, quantidade);
    }

    @Transactional
    public void adicionarServico(Long osId, Long servicoId) {
        OrdemServico os = repository.findById(osId)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + osId));
        Servico servico = servicoRepository.findById(servicoId)
                .orElseThrow(() -> new BusinessException("Serviço não encontrado: " + servicoId));
        domainService.adicionarServico(os, servico);
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> listarTodas() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO buscarPorId(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> consultarPorCliente(String cpfCnpj) {
        CpfCnpj documento = new CpfCnpj(cpfCnpj);
        return repository.findAll().stream()
                .filter(os -> os.getCliente().getCpfCnpj().equals(documento))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + id));
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Double calcularTempoMedioExecution() {
        List<OrdemServico> finalizadas = repository.findAll().stream()
                .filter(os -> os.getStatus() == StatusOrdemServico.FINALIZADA && os.getDataFinalizacao() != null)
                .collect(Collectors.toList());
        
        if (finalizadas.isEmpty()) return 0.0;

        long totalMinutos = finalizadas.stream()
                .mapToLong(os -> java.time.Duration.between(os.getDataCriacao(), os.getDataFinalizacao()).toMinutes())
                .sum();
        
        return (double) totalMinutos / finalizadas.size();
    }

    public OrdemServicoDTO toDTO(OrdemServico os) {
        OrdemServicoDTO dto = new OrdemServicoDTO();
        dto.setId(os.getId());
        dto.setClienteId(os.getCliente().getId());
        dto.setVeiculoId(os.getVeiculo().getId());
        dto.setStatus(os.getStatus().name());
        dto.setDataCriacao(os.getDataCriacao());
        dto.setDataFinalizacao(os.getDataFinalizacao());
        dto.setValorTotal(os.getValorTotal());
        dto.setServicos(os.getServicos().stream().map(this::toServicoDTO).collect(Collectors.toList()));
        dto.setPecas(os.getPecas().stream().map(this::toPecaDTO).collect(Collectors.toList()));
        return dto;
    }

    private OrdemServicoServicoDTO toServicoDTO(OrdemServicoServico osServico) {
        OrdemServicoServicoDTO dto = new OrdemServicoServicoDTO();
        dto.setServicoId(osServico.getServico().getId());
        dto.setDescricao(osServico.getServico().getDescricao());
        dto.setPrecoCobrado(osServico.getPrecoCobrado());
        return dto;
    }

    private OrdemServicoPecaDTO toPecaDTO(OrdemServicoPeca osPeca) {
        OrdemServicoPecaDTO dto = new OrdemServicoPecaDTO();
        dto.setPecaId(osPeca.getPeca().getId());
        dto.setNome(osPeca.getPeca().getNome());
        dto.setQuantidade(osPeca.getQuantidade());
        dto.setPrecoUnitarioCobrado(osPeca.getPrecoUnitarioCobrado());
        dto.setSubtotal(osPeca.getPrecoUnitarioCobrado().multiply(java.math.BigDecimal.valueOf(osPeca.getQuantidade())));
        return dto;
    }
}
