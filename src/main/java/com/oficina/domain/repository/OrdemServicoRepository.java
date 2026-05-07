package com.oficina.domain.repository;

import com.oficina.domain.model.OrdemServico;
import java.util.Optional;
import java.util.List;

public interface OrdemServicoRepository {
    OrdemServico save(OrdemServico os);
    Optional<OrdemServico> findById(Long id);
    List<OrdemServico> findAll();
    void deleteById(Long id);
    List<OrdemServico> findByClienteCpfCnpj(String cpfCnpj);
    List<OrdemServico> findByStatusAndDataFinalizacaoIsNotNull(com.oficina.domain.model.StatusOrdemServico status);
}
