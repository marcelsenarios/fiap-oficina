package com.oficina.application.usecase;

import com.oficina.application.dto.VeiculoDTO;
import com.oficina.domain.exception.BusinessException;
import com.oficina.domain.model.Cliente;
import com.oficina.domain.model.Veiculo;
import com.oficina.domain.model.Placa;
import com.oficina.domain.repository.ClienteRepository;
import com.oficina.domain.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeiculoUseCase {
    private final VeiculoRepository repository;
    private final ClienteRepository clienteRepository;

    public VeiculoDTO salvar(VeiculoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado: " + dto.getClienteId()));

        Veiculo veiculo = Veiculo.builder()
                .placa(new Placa(dto.getPlaca()))
                .marca(dto.getMarca())
                .modelo(dto.getModelo())
                .ano(dto.getAno())
                .cliente(cliente)
                .build();
        
        veiculo = repository.save(veiculo);
        return toDTO(veiculo);
    }

    public List<VeiculoDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public VeiculoDTO buscarPorId(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new BusinessException("Veículo não encontrado: " + id));
    }

    public VeiculoDTO atualizar(Long id, VeiculoDTO dto) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Veículo não encontrado: " + id));
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado: " + dto.getClienteId()));

        veiculo.setPlaca(new Placa(dto.getPlaca()));
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setAno(dto.getAno());
        veiculo.setCliente(cliente);

        return toDTO(repository.save(veiculo));
    }

    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new BusinessException("Veículo não encontrado: " + id));
        repository.deleteById(id);
    }

    public VeiculoDTO toDTO(Veiculo v) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(v.getId());
        dto.setPlaca(v.getPlaca().getValue());
        dto.setMarca(v.getMarca());
        dto.setModelo(v.getModelo());
        dto.setAno(v.getAno());
        dto.setClienteId(v.getCliente().getId());
        return dto;
    }
}
