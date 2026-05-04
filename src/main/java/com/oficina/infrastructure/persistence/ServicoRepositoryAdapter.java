package com.oficina.infrastructure.persistence;

import com.oficina.domain.model.Servico;
import com.oficina.domain.repository.ServicoRepository;
import com.oficina.infrastructure.persistence.jpa.JpaServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ServicoRepositoryAdapter implements ServicoRepository {
    private final JpaServicoRepository jpaRepository;

    @Override public Servico save(Servico servico) { return jpaRepository.save(servico); }
    @Override public Optional<Servico> findById(Long id) { return jpaRepository.findById(id); }
    @Override public List<Servico> findAll() { return jpaRepository.findAll(); }
    @Override public void deleteById(Long id) { jpaRepository.deleteById(id); }
}
