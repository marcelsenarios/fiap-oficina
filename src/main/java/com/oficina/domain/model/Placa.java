package com.oficina.domain.model;

import com.oficina.domain.exception.BusinessException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Placa {

    @Column(name = "placa", nullable = false)
    private String value;

    public Placa(String value) {
        if (value == null || !isValid(value)) {
            throw new BusinessException("Placa inválida: " + value);
        }
        this.value = value.toUpperCase().replace("-", "");
    }

    private boolean isValid(String value) {
        // Regex básica para placa Mercosul e antiga
        return value.matches("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") || value.matches("[A-Z]{3}-[0-9]{4}") || value.matches("[A-Z]{3}[0-9]{4}");
    }

    @Override
    public String toString() {
        return value;
    }
}
