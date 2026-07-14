package com.oficina.application.usecase;

import com.oficina.application.dto.ClienteDTO;
import com.oficina.application.dto.CriarOrdemServicoRequestDTO;
import com.oficina.application.dto.OrdemServicoDTO;
import com.oficina.application.dto.OrdemServicoPecaDTO;
import com.oficina.application.dto.OrdemServicoServicoDTO;
import com.oficina.application.dto.PecaSolicitadaDTO;
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
    public OrdemServicoDTO criarCompleta(CriarOrdemServicoRequestDTO request) {
        if (request == null) {
            throw new BusinessException("Dados da Ordem de Serviço são obrigatórios.");
        }

        Cliente cliente = obterOuCadastrarCliente(request);
        Veiculo veiculo = obterOuCadastrarVeiculo(cliente, request);
        OrdemServico os = domainService.criarOrdemServico(cliente, veiculo);

        for (Long servicoId : request.getServicosIds()) {
            Servico servico = servicoRepository.findById(servicoId)
                    .orElseThrow(() -> new BusinessException("Serviço não encontrado: " + servicoId));
            domainService.adicionarServico(os, servico);
        }

        for (PecaSolicitadaDTO item : request.getPecas()) {
            Peca peca = pecaRepository.findById(item.getPecaId())
                    .orElseThrow(() -> new BusinessException("Peça não encontrada: " + item.getPecaId()));
            domainService.adicionarPeca(os, peca, item.getQuantidade());
        }

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

    @Transactional
    public OrdemServicoDTO enviarOrcamento(Long id) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + id));
        domainService.enviarOrcamento(os);
        return toDTO(os);
    }

    @Transactional
    public OrdemServicoDTO aprovarOrcamento(Long id, String cpfCnpj) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + id));
        CpfCnpj documento = new CpfCnpj(cpfCnpj);
        if (!os.getCliente().getCpfCnpj().equals(documento)) {
            throw new BusinessException("CPF/CNPJ não pertence ao cliente da OS.");
        }
        domainService.aprovarOrcamento(os);
        return toDTO(os);
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> listarTodas() {
        return repository.findAll().stream()
                .filter(os -> os.getStatus() != StatusOrdemServico.FINALIZADA && os.getStatus() != StatusOrdemServico.ENTREGUE)
                .sorted((os1, os2) -> {
                    int p1 = getStatusPriority(os1.getStatus());
                    int p2 = getStatusPriority(os2.getStatus());
                    if (p1 != p2) {
                        return Integer.compare(p1, p2);
                    }
                    return os1.getDataCriacao().compareTo(os2.getDataCriacao());
                })
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public String consultarStatus(Long id) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + id));
        return os.getStatus().name();
    }

    @Transactional
    public OrdemServicoDTO recusarOrcamento(Long id, String cpfCnpj) {
        OrdemServico os = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + id));
        CpfCnpj documento = new CpfCnpj(cpfCnpj);
        if (!os.getCliente().getCpfCnpj().equals(documento)) {
            throw new BusinessException("CPF/CNPJ não pertence ao cliente da OS.");
        }
        domainService.atualizarStatus(os, StatusOrdemServico.EM_DIAGNOSTICO);
        return toDTO(os);
    }

    private int getStatusPriority(StatusOrdemServico status) {
        return switch (status) {
            case EM_EXECUCAO -> 1;
            case AGUARDANDO_APROVACAO -> 2;
            case EM_DIAGNOSTICO -> 3;
            case RECEBIDA -> 4;
            default -> 5;
        };
    }

    private Cliente obterOuCadastrarCliente(CriarOrdemServicoRequestDTO request) {
        String doc = request.getCpfCnpj();
        ClienteDTO cliDto = request.getCliente();

        if (cliDto != null && (doc == null || doc.trim().isEmpty())) {
            doc = cliDto.getCpfCnpj();
        }

        if (doc == null || doc.trim().isEmpty()) {
            throw new BusinessException("CPF/CNPJ do cliente é obrigatório.");
        }

        CpfCnpj cpfCnpjVal = new CpfCnpj(doc);
        var clienteOpt = clienteRepository.findByCpfCnpj(cpfCnpjVal);
        if (clienteOpt.isPresent()) {
            return clienteOpt.get();
        }

        if (cliDto == null) {
            throw new BusinessException("Cliente não cadastrado e dados de cadastro não fornecidos.");
        }

        if (cliDto.getNome() == null || cliDto.getNome().trim().isEmpty() ||
            cliDto.getEmail() == null || cliDto.getEmail().trim().isEmpty() ||
            cliDto.getTelefone() == null || cliDto.getTelefone().trim().isEmpty()) {
            throw new BusinessException("Para cadastrar um novo cliente, informe nome, email e telefone.");
        }

        Cliente novoCliente = Cliente.builder()
                .nome(cliDto.getNome())
                .cpfCnpj(cpfCnpjVal)
                .email(cliDto.getEmail())
                .telefone(cliDto.getTelefone())
                .build();

        return clienteRepository.save(novoCliente);
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO buscarPorId(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new BusinessException("Ordem de Serviço não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> consultarPorCliente(String cpfCnpj) {
        CpfCnpj documento = new CpfCnpj(cpfCnpj);
        return repository.findByClienteCpfCnpj(documento.getValue()).stream()
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
        List<OrdemServico> finalizadas = repository.findByStatusAndDataFinalizacaoIsNotNull(StatusOrdemServico.FINALIZADA);
        
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

    private Veiculo obterOuCadastrarVeiculo(Cliente cliente, CriarOrdemServicoRequestDTO request) {
        if (request.getVeiculo() == null) {
            throw new BusinessException("Dados do veículo são obrigatórios.");
        }

        Placa placa = new Placa(request.getVeiculo().getPlaca());
        return veiculoRepository.findByPlaca(placa)
                .map(veiculoExistente -> {
                    if (!veiculoExistente.getCliente().getId().equals(cliente.getId())) {
                        throw new BusinessException("Veículo não pertence ao cliente informado.");
                    }
                    return veiculoExistente;
                })
                .orElseGet(() -> veiculoRepository.save(Veiculo.builder()
                        .placa(placa)
                        .marca(request.getVeiculo().getMarca())
                        .modelo(request.getVeiculo().getModelo())
                        .ano(request.getVeiculo().getAno())
                        .cliente(cliente)
                        .build()));
    }
}
