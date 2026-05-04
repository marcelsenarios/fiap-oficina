package com.oficina.application.usecase;

import com.oficina.application.dto.PecaDTO;
import com.oficina.domain.exception.BusinessException;
import com.oficina.domain.model.Peca;
import com.oficina.domain.repository.PecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PecaUseCase {
    private final PecaRepository repository;

    public PecaDTO salvar(PecaDTO dto) {
        Peca peca = Peca.builder()
                .nome(dto.getNome())
                .precoUnitario(dto.getPrecoUnitario())
                .quantidadeEstoque(dto.getQuantidadeEstoque())
                .build();
        peca = repository.save(peca);
        return toDTO(peca);
    }

    public List<PecaDTO> listarTodas() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PecaDTO buscarPorId(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new BusinessException("Peça não encontrada: " + id));
    }

    public PecaDTO atualizar(Long id, PecaDTO dto) {
        Peca peca = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Peça não encontrada: " + id));

        peca.setNome(dto.getNome());
        peca.setPrecoUnitario(dto.getPrecoUnitario());
        peca.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        return toDTO(repository.save(peca));
    }

    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new BusinessException("Peça não encontrada: " + id));
        repository.deleteById(id);
    }

    public PecaDTO toDTO(Peca p) {
        PecaDTO dto = new PecaDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setPrecoUnitario(p.getPrecoUnitario());
        dto.setQuantidadeEstoque(p.getQuantidadeEstoque());
        return dto;
    }
}
