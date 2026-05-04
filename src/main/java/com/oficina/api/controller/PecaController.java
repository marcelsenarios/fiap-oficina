package com.oficina.api.controller;

import com.oficina.application.dto.PecaDTO;
import com.oficina.application.usecase.PecaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pecas")
@RequiredArgsConstructor
public class PecaController {
    private final PecaUseCase useCase;

    @PostMapping
    public PecaDTO criar(@RequestBody PecaDTO dto) {
        return useCase.salvar(dto);
    }

    @GetMapping
    public List<PecaDTO> listar() {
        return useCase.listarTodas();
    }

    @GetMapping("/{id}")
    public PecaDTO buscar(@PathVariable Long id) {
        return useCase.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PecaDTO atualizar(@PathVariable Long id, @RequestBody PecaDTO dto) {
        return useCase.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        useCase.excluir(id);
    }
}
