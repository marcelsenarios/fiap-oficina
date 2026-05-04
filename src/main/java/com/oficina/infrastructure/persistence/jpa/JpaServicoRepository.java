package com.oficina.infrastructure.persistence.jpa;

import com.oficina.domain.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaServicoRepository extends JpaRepository<Servico, Long> {
}
