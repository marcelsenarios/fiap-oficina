package com.oficina.infrastructure.persistence;

import com.oficina.domain.model.Veiculo;
import com.oficina.domain.model.Placa;
import com.oficina.domain.repository.VeiculoRepository;
import com.oficina.infrastructure.persistence.jpa.JpaVeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VeiculoRepositoryAdapter implements VeiculoRepository {
    private final JpaVeiculoRepository jpaRepository;

    @Override public Veiculo save(Veiculo veiculo) { return jpaRepository.save(veiculo); }
    @Override public Optional<Veiculo> findById(Long id) { return jpaRepository.findById(id); }
    @Override public Optional<Veiculo> findByPlaca(Placa placa) { return jpaRepository.findByPlaca(placa); }
    @Override public List<Veiculo> findAll() { return jpaRepository.findAll(); }
    @Override public void deleteById(Long id) { jpaRepository.deleteById(id); }
}
