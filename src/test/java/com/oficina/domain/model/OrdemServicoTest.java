package com.oficina.domain.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class OrdemServicoTest {

    @Test
    void deveCalcularValorTotalCorretamente() {
        OrdemServico os = OrdemServico.builder()
                .servicos(new ArrayList<>())
                .pecas(new ArrayList<>())
                .build();
        
        OrdemServicoServico s1 = OrdemServicoServico.builder()
                .precoCobrado(new BigDecimal("100.00"))
                .build();
        
        OrdemServicoPeca p1 = OrdemServicoPeca.builder()
                .precoUnitarioCobrado(new BigDecimal("50.00"))
                .quantidade(2)
                .build();
        
        os.getServicos().add(s1);
        os.getPecas().add(p1);
        
        os.calcularValorTotal();
        
        assertEquals(new BigDecimal("200.00"), os.getValorTotal());
    }
}
