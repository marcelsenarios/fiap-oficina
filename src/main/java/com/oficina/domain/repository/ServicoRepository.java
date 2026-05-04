package com.oficina.domain.repository;

import com.oficina.domain.model.Servico;
import java.util.Optional;
import java.util.List;

public interface ServicoRepository {
    Servico save(Servico servico);
    Optional<Servico> findById(Long id);
    List<Servico> findAll();
    void deleteById(Long id);
}
