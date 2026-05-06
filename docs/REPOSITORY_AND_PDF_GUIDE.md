# Guia de Repositório e PDF Final

## 1. Repositório Privado

Repositório configurado no projeto:

```text
https://github.com/marcelsenarios/fiap-oficina.git
```

Antes da entrega:

1. Confirmar no GitHub se o repositório está privado.
2. Fazer commit das alterações.
3. Enviar para o remoto.
4. Adicionar o usuário `soatarchitecture` com permissão de leitura.

## 2. Comandos de Commit e Push

Revisar alterações:

```bash
git status
git diff --stat
```

Adicionar arquivos:

```bash
git add .
```

Criar commit:

```bash
git commit -m "Finaliza pendencias do Tech Challenge Fase 1"
```

Enviar para o GitHub:

```bash
git push origin main
```

Se a branch principal tiver outro nome, conferir com:

```bash
git branch --show-current
```

E usar:

```bash
git push origin <nome-da-branch>
```

## 3. Como Adicionar `soatarchitecture` no GitHub

No GitHub:

1. Abrir o repositório.
2. Entrar em **Settings**.
3. Entrar em **Collaborators and teams**.
4. Clicar em **Add people**.
5. Informar:

```text
soatarchitecture
```

6. Conceder permissão de leitura ou acesso equivalente exigido pela FIAP.
7. Confirmar que o convite foi enviado.

## 4. Documento Final em PDF

Arquivo base:

```text
docs/FINAL_DELIVERY_DOCUMENT.md
```

Preencher os campos marcados como `PREENCHER` antes de exportar.

Campos obrigatórios:

- Nome do grupo.
- Participantes.
- RM de cada participante.
- Username no Discord.
- Link do repositório.
- Link da documentação DDD.
- Link do vídeo.
- Resultado real do scan de vulnerabilidades.

## 5. Opções para Exportar Markdown para PDF

### Opção A: GitHub

1. Abrir `docs/FINAL_DELIVERY_DOCUMENT.md` no GitHub.
2. Usar a visualização renderizada.
3. Imprimir a página pelo navegador.
4. Escolher **Salvar como PDF**.

### Opção B: VS Code

1. Abrir `docs/FINAL_DELIVERY_DOCUMENT.md`.
2. Abrir Preview do Markdown.
3. Usar extensão de exportação para PDF ou imprimir pelo navegador.

### Opção C: Google Docs

1. Copiar o conteúdo de `docs/FINAL_DELIVERY_DOCUMENT.md`.
2. Colar no Google Docs.
3. Ajustar tabelas e links.
4. Exportar em **Arquivo > Fazer download > PDF**.

### Opção D: Pandoc

Se `pandoc` estiver instalado:

```bash
pandoc docs/FINAL_DELIVERY_DOCUMENT.md -o docs/FINAL_DELIVERY_DOCUMENT.pdf
```

Neste ambiente, `pandoc` não está instalado, mas `libreoffice` está disponível.

## 6. Conferência Final do PDF

Antes de enviar:

- [ ] Links clicáveis.
- [ ] Repositório abre para quem tem permissão.
- [ ] Vídeo abre.
- [ ] Documentação DDD abre.
- [ ] Relatório de vulnerabilidades contém resultado real ou justificativa clara.
- [ ] PDF contém nome do grupo, participantes e Discord.
