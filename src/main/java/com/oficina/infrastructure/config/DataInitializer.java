package com.oficina.infrastructure.config;

import com.oficina.domain.model.*;
import com.oficina.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;
    private final PecaRepository pecaRepository;
    private final ServicoRepository servicoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.findAll().isEmpty()) {
            Cliente cliente = Cliente.builder()
                    .nome("João da Silva")
                    .cpfCnpj(new CpfCnpj("123.456.789-09"))
                    .email("joao@email.com")
                    .telefone("11999999999")
                    .build();
            cliente = clienteRepository.save(cliente);

            Veiculo veiculo = Veiculo.builder()
                    .placa(new Placa("ABC1D23"))
                    .marca("Volkswagen")
                    .modelo("Gol")
                    .ano(2020)
                    .cliente(cliente)
                    .build();
            veiculoRepository.save(veiculo);

            Peca peca = Peca.builder()
                    .nome("Óleo 5W30")
                    .precoUnitario(new BigDecimal("50.00"))
                    .quantidadeEstoque(10)
                    .build();
            pecaRepository.save(peca);

            Servico servico = Servico.builder()
                    .descricao("Troca de Óleo")
                    .precoBase(new BigDecimal("80.00"))
                    .build();
            servicoRepository.save(servico);
            
            System.out.println("Dados iniciais carregados com sucesso!");
        }
    }
}
