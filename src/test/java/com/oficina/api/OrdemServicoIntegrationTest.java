package com.oficina.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oficina.application.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrdemServicoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveExecutarFluxoCompletoDeOS() throws Exception {
        String token = obterTokenAdmin();

        // 1. Criar Cliente
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Cliente Teste");
        clienteDTO.setCpfCnpj("12345678909");
        clienteDTO.setEmail("teste@teste.com");
        clienteDTO.setTelefone("11988887777");

        MvcResult resultCliente = mockMvc.perform(post("/api/clientes")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andReturn();
        
        Long clienteId = objectMapper.readTree(resultCliente.getResponse().getContentAsString()).get("id").asLong();

        // 2. Criar Veiculo
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setPlaca("XYZ9A88");
        veiculoDTO.setMarca("Fiat");
        veiculoDTO.setModelo("Uno");
        veiculoDTO.setAno(2015);
        veiculoDTO.setClienteId(clienteId);

        MvcResult resultVeiculo = mockMvc.perform(post("/api/veiculos")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(veiculoDTO)))
                .andExpect(status().isOk())
                .andReturn();
        
        Long veiculoId = objectMapper.readTree(resultVeiculo.getResponse().getContentAsString()).get("id").asLong();

        // 3. Criar Peca
        PecaDTO pecaDTO = new PecaDTO();
        pecaDTO.setNome("Filtro");
        pecaDTO.setPrecoUnitario(new BigDecimal("30.00"));
        pecaDTO.setQuantidadeEstoque(100);

        MvcResult resultPeca = mockMvc.perform(post("/api/pecas")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pecaDTO)))
                .andExpect(status().isOk())
                .andReturn();
        
        Long pecaId = objectMapper.readTree(resultPeca.getResponse().getContentAsString()).get("id").asLong();

        // 4. Criar Servico
        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setDescricao("Mão de Obra");
        servicoDTO.setPrecoBase(new BigDecimal("100.00"));

        MvcResult resultServico = mockMvc.perform(post("/api/servicos")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoDTO)))
                .andExpect(status().isOk())
                .andReturn();
        
        Long servicoId = objectMapper.readTree(resultServico.getResponse().getContentAsString()).get("id").asLong();

        // 5. Criar OS
        MvcResult resultOS = mockMvc.perform(post("/api/os")
                .header("Authorization", token)
                .param("clienteId", clienteId.toString())
                .param("veiculoId", veiculoId.toString()))
                .andExpect(status().isOk())
                .andReturn();
        
        Long osId = objectMapper.readTree(resultOS.getResponse().getContentAsString()).get("id").asLong();

        // 6. Adicionar Peça e Serviço
        mockMvc.perform(post("/api/os/" + osId + "/pecas")
                .header("Authorization", token)
                .param("pecaId", pecaId.toString())
                .param("quantidade", "2"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/os/" + osId + "/servicos")
                .header("Authorization", token)
                .param("servicoId", servicoId.toString()))
                .andExpect(status().isOk());

        // 7. Avançar Status
        mockMvc.perform(patch("/api/os/" + osId + "/status")
                .header("Authorization", token)
                .param("status", "EM_DIAGNOSTICO"))
                .andExpect(status().isOk());

        mockMvc.perform(patch("/api/os/" + osId + "/status")
                .header("Authorization", token)
                .param("status", "AGUARDANDO_APROVACAO"))
                .andExpect(status().isOk());

        mockMvc.perform(patch("/api/os/" + osId + "/status")
                .header("Authorization", token)
                .param("status", "EM_EXECUCAO"))
                .andExpect(status().isOk());

        mockMvc.perform(patch("/api/os/" + osId + "/status")
                .header("Authorization", token)
                .param("status", "FINALIZADA"))
                .andExpect(status().isOk());

        // 8. Verificar Estatísticas
        mockMvc.perform(get("/api/os/estatisticas/tempo-medio")
                .header("Authorization", token))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/os/" + osId)
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("FINALIZADA"));

        mockMvc.perform(get("/api/public/os")
                .param("cpfCnpj", "12345678909"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(osId));
    }

    @Test
    public void deveCriarOrdemCompletaEnviarOrcamentoEAprovarPeloCliente() throws Exception {
        String token = obterTokenAdmin();

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Cliente Orcamento");
        clienteDTO.setCpfCnpj("52998224725");
        clienteDTO.setEmail("orcamento@teste.com");
        clienteDTO.setTelefone("11999990000");

        mockMvc.perform(post("/api/clientes")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk());

        PecaDTO pecaDTO = new PecaDTO();
        pecaDTO.setNome("Pastilha de Freio");
        pecaDTO.setPrecoUnitario(new BigDecimal("80.00"));
        pecaDTO.setQuantidadeEstoque(4);

        MvcResult resultPeca = mockMvc.perform(post("/api/pecas")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pecaDTO)))
                .andExpect(status().isOk())
                .andReturn();
        Long pecaId = objectMapper.readTree(resultPeca.getResponse().getContentAsString()).get("id").asLong();

        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setDescricao("Troca de freio");
        servicoDTO.setPrecoBase(new BigDecimal("120.00"));

        MvcResult resultServico = mockMvc.perform(post("/api/servicos")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoDTO)))
                .andExpect(status().isOk())
                .andReturn();
        Long servicoId = objectMapper.readTree(resultServico.getResponse().getContentAsString()).get("id").asLong();

        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setPlaca("ABC1D23");
        veiculoDTO.setMarca("Honda");
        veiculoDTO.setModelo("Fit");
        veiculoDTO.setAno(2020);

        PecaSolicitadaDTO pecaSolicitadaDTO = new PecaSolicitadaDTO();
        pecaSolicitadaDTO.setPecaId(pecaId);
        pecaSolicitadaDTO.setQuantidade(2);

        CriarOrdemServicoRequestDTO request = new CriarOrdemServicoRequestDTO();
        request.setCpfCnpj("52998224725");
        request.setVeiculo(veiculoDTO);
        request.setServicosIds(List.of(servicoId));
        request.setPecas(List.of(pecaSolicitadaDTO));

        MvcResult resultOS = mockMvc.perform(post("/api/os/completa")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_DIAGNOSTICO"))
                .andExpect(jsonPath("$.valorTotal").value(280.00))
                .andReturn();
        Long osId = objectMapper.readTree(resultOS.getResponse().getContentAsString()).get("id").asLong();

        mockMvc.perform(post("/api/os/" + osId + "/orcamento/enviar")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("AGUARDANDO_APROVACAO"));

        mockMvc.perform(post("/api/public/os/" + osId + "/aprovar")
                .param("cpfCnpj", "52998224725"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_EXECUCAO"));

        mockMvc.perform(get("/api/pecas/" + pecaId)
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantidadeEstoque").value(2));
    }

    @Test
    public void deveTestarNovosFluxosFase2() throws Exception {
        String token = obterTokenAdmin();

        // 1. Criar OS com Cliente novo (que não existe) e Veiculo novo
        ClienteDTO novoCli = new ClienteDTO();
        novoCli.setNome("Cliente Novo Autocriado");
        novoCli.setCpfCnpj("11144477735");
        novoCli.setEmail("novoautocriado@teste.com");
        novoCli.setTelefone("11977778888");

        VeiculoDTO novoVeic = new VeiculoDTO();
        novoVeic.setPlaca("AAA1B22");
        novoVeic.setMarca("Ford");
        novoVeic.setModelo("Ka");
        novoVeic.setAno(2018);

        CriarOrdemServicoRequestDTO request = new CriarOrdemServicoRequestDTO();
        request.setCliente(novoCli);
        request.setVeiculo(novoVeic);

        MvcResult result = mockMvc.perform(post("/api/os")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        Long osId = objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
        org.junit.jupiter.api.Assertions.assertNotNull(osId);

        // 2. Consultar status da OS (Admin e Publico)
        mockMvc.perform(get("/api/os/" + osId + "/status")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RECEBIDA"));

        mockMvc.perform(get("/api/public/os/" + osId + "/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RECEBIDA"));

        // 3. Adicionar serviço e enviar orçamento
        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setDescricao("Revisão");
        servicoDTO.setPrecoBase(new BigDecimal("150.00"));
        MvcResult resServ = mockMvc.perform(post("/api/servicos")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicoDTO)))
                .andExpect(status().isOk())
                .andReturn();
        Long servId = objectMapper.readTree(resServ.getResponse().getContentAsString()).get("id").asLong();

        mockMvc.perform(post("/api/os/" + osId + "/servicos")
                .header("Authorization", token)
                .param("servicoId", servId.toString()))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/os/" + osId + "/orcamento/enviar")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("AGUARDANDO_APROVACAO"));

        // 4. Testar Recusa de Orçamento
        mockMvc.perform(post("/api/public/os/" + osId + "/recusar")
                .param("cpfCnpj", "11144477735"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_DIAGNOSTICO"));

        // 5. Testar Notificação de Orçamento (Unified Endpoint) - Recusar novamente
        mockMvc.perform(post("/api/os/" + osId + "/orcamento/enviar")
                .header("Authorization", token))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/public/os/" + osId + "/notificacao-orcamento")
                .param("cpfCnpj", "11144477735")
                .param("aprovado", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_DIAGNOSTICO"));

        // 6. Testar Notificação de Orçamento (Unified Endpoint) - Aprovar
        mockMvc.perform(post("/api/os/" + osId + "/orcamento/enviar")
                .header("Authorization", token))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/public/os/" + osId + "/notificacao-orcamento")
                .param("cpfCnpj", "11144477735")
                .param("aprovado", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_EXECUCAO"));

        // 7. Listagem de OS e validação de ordenação/filtragem
        MvcResult resList = mockMvc.perform(get("/api/os")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn();
        
        String listJson = resList.getResponse().getContentAsString();
        List<OrdemServicoDTO> osList = objectMapper.readValue(listJson, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, OrdemServicoDTO.class));
        
        boolean found = false;
        for (OrdemServicoDTO os : osList) {
            if (os.getId().equals(osId)) {
                found = true;
            }
            org.junit.jupiter.api.Assertions.assertNotEquals("FINALIZADA", os.getStatus());
            org.junit.jupiter.api.Assertions.assertNotEquals("ENTREGUE", os.getStatus());
        }
        org.junit.jupiter.api.Assertions.assertTrue(found);
    }

    @Test
    public void deveProtegerRotasAdministrativasEValidarLogin() throws Exception {
        LoginRequestDTO loginInvalido = new LoginRequestDTO();
        loginInvalido.setUsername("admin");
        loginInvalido.setPassword("senha-incorreta");

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginInvalido)))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isForbidden());
    }

    private String obterTokenAdmin() throws Exception {
        LoginRequestDTO login = new LoginRequestDTO();
        login.setUsername("admin");
        login.setPassword("admin123");

        MvcResult result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andReturn();

        return "Bearer " + objectMapper.readTree(result.getResponse().getContentAsString()).get("token").asText();
    }
}
