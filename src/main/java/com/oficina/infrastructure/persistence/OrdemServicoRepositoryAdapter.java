package com.oficina.infrastructure.persistence;

import com.oficina.domain.model.OrdemServico;
import com.oficina.domain.repository.OrdemServicoRepository;
import com.oficina.infrastructure.persistence.jpa.JpaOrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrdemServicoRepositoryAdapter implements OrdemServicoRepository {
    private final JpaOrdemServicoRepository jpaRepository;

    @Override public OrdemServico save(OrdemServico os) { return jpaRepository.save(os); }
    @Override public Optional<OrdemServico> findById(Long id) { return jpaRepository.findById(id); }
    @Override public List<OrdemServico> findAll() { return jpaRepository.findAll(); }
    @Override public void deleteById(Long id) { jpaRepository.deleteById(id); }
    
    @Override
    public List<OrdemServico> findByClienteCpfCnpj(String cpfCnpj) {
        return jpaRepository.findByClienteCpfCnpjValue(cpfCnpj);
    }

    @Override
    public List<OrdemServico> findByStatusAndDataFinalizacaoIsNotNull(com.oficina.domain.model.StatusOrdemServico status) {
        return jpaRepository.findByStatusAndDataFinalizacaoIsNotNull(status);
    }
}
