package com.oficina.infrastructure.persistence.jpa;

import com.oficina.domain.model.Veiculo;
import com.oficina.domain.model.Placa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaVeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(Placa placa);
}
