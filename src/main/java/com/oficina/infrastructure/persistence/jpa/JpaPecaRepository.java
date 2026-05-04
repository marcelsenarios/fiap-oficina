package com.oficina.infrastructure.persistence.jpa;

import com.oficina.domain.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPecaRepository extends JpaRepository<Peca, Long> {
}
