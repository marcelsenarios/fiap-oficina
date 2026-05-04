package com.oficina.application.usecase;

import com.oficina.application.dto.ServicoDTO;
import com.oficina.domain.exception.BusinessException;
import com.oficina.domain.model.Servico;
import com.oficina.domain.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoUseCase {
    private final ServicoRepository repository;

    public ServicoDTO salvar(ServicoDTO dto) {
        Servico servico = Servico.builder()
                .descricao(dto.getDescricao())
                .precoBase(dto.getPrecoBase())
                .build();
        servico = repository.save(servico);
        return toDTO(servico);
    }

    public List<ServicoDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ServicoDTO buscarPorId(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new BusinessException("Serviço não encontrado: " + id));
    }

    public ServicoDTO atualizar(Long id, ServicoDTO dto) {
        Servico servico = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Serviço não encontrado: " + id));

        servico.setDescricao(dto.getDescricao());
        servico.setPrecoBase(dto.getPrecoBase());

        return toDTO(repository.save(servico));
    }

    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new BusinessException("Serviço não encontrado: " + id));
        repository.deleteById(id);
    }

    public ServicoDTO toDTO(Servico s) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(s.getId());
        dto.setDescricao(s.getDescricao());
        dto.setPrecoBase(s.getPrecoBase());
        return dto;
    }
}
