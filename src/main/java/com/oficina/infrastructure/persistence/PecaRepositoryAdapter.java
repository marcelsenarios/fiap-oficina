package com.oficina.infrastructure.persistence;

import com.oficina.domain.model.Peca;
import com.oficina.domain.repository.PecaRepository;
import com.oficina.infrastructure.persistence.jpa.JpaPecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PecaRepositoryAdapter implements PecaRepository {
    private final JpaPecaRepository jpaRepository;

    @Override public Peca save(Peca peca) { return jpaRepository.save(peca); }
    @Override public Optional<Peca> findById(Long id) { return jpaRepository.findById(id); }
    @Override public List<Peca> findAll() { return jpaRepository.findAll(); }
    @Override public void deleteById(Long id) { jpaRepository.deleteById(id); }
}
