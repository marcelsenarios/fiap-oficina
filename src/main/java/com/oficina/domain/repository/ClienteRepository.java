package com.oficina.domain.repository;

import com.oficina.domain.model.Cliente;
import com.oficina.domain.model.CpfCnpj;
import java.util.Optional;
import java.util.List;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByCpfCnpj(CpfCnpj cpfCnpj);
    List<Cliente> findAll();
    void deleteById(Long id);
}
