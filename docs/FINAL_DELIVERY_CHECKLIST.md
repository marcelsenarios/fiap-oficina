# Checklist Final de Entrega

Use este checklist antes de enviar a entrega da Fase 1.

## 1. Código e Validação Técnica

- [ ] Rodar validação completa:

```bash
./mvnw verify
```

Resultado esperado:

```text
BUILD SUCCESS
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
All coverage checks have been met.
```

- [ ] Subir ambiente completo com Docker:

```bash
docker compose up --build
```

- [ ] Validar Swagger:

```text
http://localhost:8080/swagger-ui.html
```

- [ ] Validar login administrativo:

```http
POST /api/auth/login
```

```json
{
  "username": "admin",
  "password": "admin123"
}
```

## 2. Scan de Vulnerabilidades

- [ ] Obter uma chave NVD API Key.
- [ ] Exportar a chave:

```bash
export NVD_API_KEY=<sua-chave>
```

- [ ] Executar o scan:

```bash
./mvnw -Psecurity-scan verify
```

- [ ] Conferir os relatórios:

```text
target/dependency-check-report.html
target/dependency-check-report.json
```

- [ ] Atualizar `docs/VULNERABILITY_REPORT.md` com o resultado real do scan.

## 3. Documentação DDD

- [ ] Revisar `docs/DDD_DOCUMENTATION.md`.
- [ ] Confirmar que a documentação contém:
  - Linguagem ubíqua.
  - Event Storming de criação e acompanhamento da OS.
  - Event Storming de gestão de peças e insumos.
  - Diagrama de domínio.
  - Contextos delimitados.
  - Regras de negócio.

- [ ] Se necessário, publicar a documentação DDD em Miro, FigJam, Draw.io, Mermaid Live ou ferramenta equivalente.
- [ ] Copiar o link da documentação publicada para o documento final de entrega.

## 4. Repositório

- [ ] Confirmar que o repositório está privado.
- [ ] Fazer commit das alterações.
- [ ] Enviar as alterações para o repositório remoto.
- [ ] Adicionar o usuário `soatarchitecture` com permissão de leitura.
- [ ] Copiar o link do repositório para o documento final de entrega.

## 5. Vídeo

- [ ] Gravar vídeo de até 15 minutos.
- [ ] Demonstrar:
  - Estrutura do projeto.
  - Swagger.
  - Login JWT.
  - CRUDs principais.
  - Criação completa da OS por CPF/CNPJ.
  - Envio do orçamento.
  - Aprovação pública pelo cliente.
  - Baixa de estoque.
  - Consulta pública da OS.
  - Testes e cobertura.
  - Docker Compose.

- [ ] Gerar link público ou acessível do vídeo.

## 6. Documento Final em PDF

- [ ] Preencher `docs/FINAL_DELIVERY_DOCUMENT.md`.
- [ ] Exportar para PDF.
- [ ] Conferir se o PDF contém:
  - Nome do grupo.
  - Participantes.
  - Usernames no Discord.
  - Link da documentação DDD.
  - Link do repositório.
  - Link do vídeo.
  - Relatório de vulnerabilidades.

## 7. Entrega

- [ ] Anexar ou informar o PDF final na plataforma da FIAP.
- [ ] Conferir se os links do PDF abrem sem autenticação extra, exceto o repositório privado com acesso liberado para `soatarchitecture`.
