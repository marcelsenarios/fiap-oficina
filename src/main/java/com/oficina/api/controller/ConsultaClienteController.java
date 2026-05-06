package com.oficina.api.controller;

import com.oficina.application.dto.OrdemServicoDTO;
import com.oficina.application.usecase.OrdemServicoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/public/os")
@RequiredArgsConstructor
public class ConsultaClienteController {
    private final OrdemServicoUseCase useCase;

    @GetMapping
    public List<OrdemServicoDTO> consultarPorCliente(@RequestParam String cpfCnpj) {
        return useCase.consultarPorCliente(cpfCnpj);
    }

    @PostMapping("/{id}/aprovar")
    public OrdemServicoDTO aprovarOrcamento(@PathVariable Long id, @RequestParam String cpfCnpj) {
        return useCase.aprovarOrcamento(id, cpfCnpj);
    }
}
