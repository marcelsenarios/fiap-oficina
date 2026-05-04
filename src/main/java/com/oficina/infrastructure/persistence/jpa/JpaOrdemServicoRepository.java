package com.oficina.infrastructure.persistence.jpa;

import com.oficina.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
