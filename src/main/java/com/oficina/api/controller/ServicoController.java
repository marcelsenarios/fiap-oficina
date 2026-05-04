package com.oficina.api.controller;

import com.oficina.application.dto.ServicoDTO;
import com.oficina.application.usecase.ServicoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicoController {
    private final ServicoUseCase useCase;

    @PostMapping
    public ServicoDTO criar(@RequestBody ServicoDTO dto) {
        return useCase.salvar(dto);
    }

    @GetMapping
    public List<ServicoDTO> listar() {
        return useCase.listarTodos();
    }

    @GetMapping("/{id}")
    public ServicoDTO buscar(@PathVariable Long id) {
        return useCase.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ServicoDTO atualizar(@PathVariable Long id, @RequestBody ServicoDTO dto) {
        return useCase.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        useCase.excluir(id);
    }
}
