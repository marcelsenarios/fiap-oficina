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
public class CpfCnpj {

    @Column(name = "cpf_cnpj", nullable = false)
    private String value;

    public CpfCnpj(String value) {
        if (value == null || !isValid(value)) {
            throw new BusinessException("CPF/CNPJ inválido: " + value);
        }
        this.value = value.replaceAll("[^0-9]", "");
    }

    private boolean isValid(String value) {
        String cleaned = value.replaceAll("[^0-9]", "");
        if (cleaned.length() == 11) {
            return isValidCPF(cleaned);
        } else if (cleaned.length() == 14) {
            return isValidCNPJ(cleaned);
        }
        return false;
    }

    private boolean isValidCPF(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int d1 = 0, d2 = 0;
            int digit1, digit2, rest;
            int nCount;

            for (nCount = 1; nCount < cpf.length() - 1; nCount++) {
                int digit = Integer.parseInt(cpf.substring(nCount - 1, nCount));
                d1 += (11 - nCount) * digit;
                d2 += (12 - nCount) * digit;
            }

            rest = (d1 % 11);
            if (rest < 2) digit1 = 0;
            else digit1 = 11 - rest;

            d2 += 2 * digit1;
            rest = (d2 % 11);
            if (rest < 2) digit2 = 0;
            else digit2 = 11 - rest;

            String calculated = cpf.substring(0, 9) + digit1 + digit2;
            return cpf.equals(calculated);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidCNPJ(String cnpj) {
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int[] weight1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] weight2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int sum = 0;
            for (int i = 0; i < 12; i++) {
                sum += Integer.parseInt(cnpj.substring(i, i + 1)) * weight1[i];
            }
            int rest = sum % 11;
            int digit1 = (rest < 2) ? 0 : 11 - rest;

            sum = 0;
            for (int i = 0; i < 12; i++) {
                sum += Integer.parseInt(cnpj.substring(i, i + 1)) * weight2[i];
            }
            sum += digit1 * weight2[12];
            rest = sum % 11;
            int digit2 = (rest < 2) ? 0 : 11 - rest;

            return cnpj.equals(cnpj.substring(0, 12) + digit1 + digit2);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
