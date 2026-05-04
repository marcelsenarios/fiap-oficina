package com.oficina.domain.repository;

import com.oficina.domain.model.Peca;
import java.util.Optional;
import java.util.List;

public interface PecaRepository {
    Peca save(Peca peca);
    Optional<Peca> findById(Long id);
    List<Peca> findAll();
    void deleteById(Long id);
}
