package com.oficina.domain.model;

import com.oficina.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CpfCnpjTest {

    @Test
    void deveCriarCpfValido() {
        CpfCnpj cpf = new CpfCnpj("123.456.789-09");
        assertEquals("12345678909", cpf.getValue());
    }

    @Test
    void deveLancarExcecaoParaCpfInvalido() {
        assertThrows(BusinessException.class, () -> new CpfCnpj("123"));
    }
}
