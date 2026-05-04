package com.oficina.api.controller;

import com.oficina.application.dto.VeiculoDTO;
import com.oficina.application.usecase.VeiculoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
public class VeiculoController {
    private final VeiculoUseCase useCase;

    @PostMapping
    public VeiculoDTO criar(@RequestBody VeiculoDTO dto) {
        return useCase.salvar(dto);
    }

    @GetMapping
    public List<VeiculoDTO> listar() {
        return useCase.listarTodos();
    }

    @GetMapping("/{id}")
    public VeiculoDTO buscar(@PathVariable Long id) {
        return useCase.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public VeiculoDTO atualizar(@PathVariable Long id, @RequestBody VeiculoDTO dto) {
        return useCase.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        useCase.excluir(id);
    }
}
