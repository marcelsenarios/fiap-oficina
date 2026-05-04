package com.oficina.domain.repository;

import com.oficina.domain.model.Veiculo;
import com.oficina.domain.model.Placa;
import java.util.Optional;
import java.util.List;

public interface VeiculoRepository {
    Veiculo save(Veiculo veiculo);
    Optional<Veiculo> findById(Long id);
    Optional<Veiculo> findByPlaca(Placa placa);
    List<Veiculo> findAll();
    void deleteById(Long id);
}
