package com.oficina.application.usecase;

import com.oficina.application.dto.ClienteDTO;
import com.oficina.domain.exception.BusinessException;
import com.oficina.domain.model.Cliente;
import com.oficina.domain.model.CpfCnpj;
import com.oficina.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteUseCase {
    private final ClienteRepository repository;

    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .cpfCnpj(new CpfCnpj(dto.getCpfCnpj()))
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .build();
        cliente = repository.save(cliente);
        return toDTO(cliente);
    }

    public List<ClienteDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado: " + id));
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado: " + id));

        cliente.setNome(dto.getNome());
        cliente.setCpfCnpj(new CpfCnpj(dto.getCpfCnpj()));
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());

        return toDTO(repository.save(cliente));
    }

    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado: " + id));
        repository.deleteById(id);
    }

    public ClienteDTO toDTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setCpfCnpj(c.getCpfCnpj().getValue());
        dto.setEmail(c.getEmail());
        dto.setTelefone(c.getTelefone());
        return dto;
    }
}
