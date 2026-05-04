package com.oficina.api.controller;

import com.oficina.application.dto.ClienteDTO;
import com.oficina.application.usecase.ClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteUseCase useCase;

    @PostMapping
    public ClienteDTO criar(@RequestBody ClienteDTO dto) {
        return useCase.salvar(dto);
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return useCase.listarTodos();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscar(@PathVariable Long id) {
        return useCase.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        return useCase.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        useCase.excluir(id);
    }
}
