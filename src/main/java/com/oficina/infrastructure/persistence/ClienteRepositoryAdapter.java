package com.oficina.infrastructure.persistence;

import com.oficina.domain.model.Cliente;
import com.oficina.domain.model.CpfCnpj;
import com.oficina.domain.repository.ClienteRepository;
import com.oficina.infrastructure.persistence.jpa.JpaClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepository {
    private final JpaClienteRepository jpaRepository;

    @Override public Cliente save(Cliente cliente) { return jpaRepository.save(cliente); }
    @Override public Optional<Cliente> findById(Long id) { return jpaRepository.findById(id); }
    @Override public Optional<Cliente> findByCpfCnpj(CpfCnpj cpfCnpj) { return jpaRepository.findByCpfCnpj(cpfCnpj); }
    @Override public List<Cliente> findAll() { return jpaRepository.findAll(); }
    @Override public void deleteById(Long id) { jpaRepository.deleteById(id); }
}
