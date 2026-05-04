package com.oficina.infrastructure.persistence.jpa;

import com.oficina.domain.model.Cliente;
import com.oficina.domain.model.CpfCnpj;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpfCnpj(CpfCnpj cpfCnpj);
}
