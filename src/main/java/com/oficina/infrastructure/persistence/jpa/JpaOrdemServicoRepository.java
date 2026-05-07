package com.oficina.infrastructure.persistence.jpa;

import com.oficina.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaOrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByClienteCpfCnpjValue(String value);
    List<OrdemServico> findByStatusAndDataFinalizacaoIsNotNull(com.oficina.domain.model.StatusOrdemServico status);
}
