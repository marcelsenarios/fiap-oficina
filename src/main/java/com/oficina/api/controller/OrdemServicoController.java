package com.oficina.api.controller;

import com.oficina.application.dto.CriarOrdemServicoRequestDTO;
import com.oficina.application.dto.OrdemServicoDTO;
import com.oficina.application.usecase.OrdemServicoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/os")
@RequiredArgsConstructor
public class OrdemServicoController {
    private final OrdemServicoUseCase useCase;

    @PostMapping
    public OrdemServicoDTO criar(
            @RequestBody(required = false) CriarOrdemServicoRequestDTO request,
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) Long veiculoId
    ) {
        if (request != null) {
            return useCase.criarCompleta(request);
        }
        if (clienteId != null && veiculoId != null) {
            return useCase.criar(clienteId, veiculoId);
        }
        throw new com.oficina.domain.exception.BusinessException("Dados insuficientes para criação da Ordem de Serviço.");
    }

    @PostMapping("/completa")
    public OrdemServicoDTO criarCompleta(@RequestBody CriarOrdemServicoRequestDTO request) {
        return useCase.criarCompleta(request);
    }

    @GetMapping("/{id}/status")
    public Map<String, String> consultarStatus(@PathVariable Long id) {
        String status = useCase.consultarStatus(id);
        return Map.of("id", id.toString(), "status", status);
    }

    @PatchMapping("/{id}/status")
    public void atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        useCase.atualizarStatus(id, status);
    }

    @PostMapping("/{id}/orcamento/enviar")
    public OrdemServicoDTO enviarOrcamento(@PathVariable Long id) {
        return useCase.enviarOrcamento(id);
    }

    @PostMapping("/{id}/pecas")
    public void adicionarPeca(@PathVariable Long id, @RequestParam Long pecaId, @RequestParam Integer quantidade) {
        useCase.adicionarPeca(id, pecaId, quantidade);
    }

    @PostMapping("/{id}/servicos")
    public void adicionarServico(@PathVariable Long id, @RequestParam Long servicoId) {
        useCase.adicionarServico(id, servicoId);
    }

    @GetMapping
    public List<OrdemServicoDTO> listar() {
        return useCase.listarTodas();
    }

    @GetMapping("/{id}")
    public OrdemServicoDTO buscar(@PathVariable Long id) {
        return useCase.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        useCase.excluir(id);
    }

    @GetMapping("/estatisticas/tempo-medio")
    public Double getTempoMedio() {
        return useCase.calcularTempoMedioExecution();
    }
}
