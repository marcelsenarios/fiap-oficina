# Transcrição do vídeo Tech Challenge

- **Arquivo:** `tech_chalange.mp4`
- **Modelo de transcrição:** `tiny`
- **Observação:** transcrição automática gerada a partir do áudio do vídeo.

## Análise do vídeo

O vídeo é uma explicação do Tech Challenge da Fase 1 do curso de Software Architecture. O professor apresenta o enunciado do desafio, esclarece dúvidas dos alunos e detalha o que será avaliado na entrega.

### Objetivo do desafio

Desenvolver a primeira versão MVP do back-end de um sistema de oficina mecânica, com foco em ordens de serviço, clientes, veículos, serviços, peças/insumos, orçamento, acompanhamento de status, segurança e qualidade.

### Requisitos funcionais destacados

- Criar Ordem de Serviço identificando cliente por CPF/CNPJ.
- Cadastrar veículo com placa, marca, modelo e ano.
- Incluir serviços solicitados e peças/insumos necessários.
- Gerar orçamento automaticamente com base em serviços e peças.
- Enviar ou simular envio do orçamento ao cliente para aprovação.
- Acompanhar a OS por status: `Recebida`, `Em diagnóstico`, `Aguardando aprovação`, `Em execução`, `Finalizada` e `Entregue`.
- Permitir consulta via API para o cliente acompanhar o progresso.
- Implementar gestão administrativa com CRUD de clientes, veículos, serviços e peças/insumos.
- Controlar estoque, baixando peças usadas nas ordens de serviço.
- Monitorar tempo médio de execução dos serviços.

### Requisitos técnicos e de qualidade destacados

- Back-end monolítico em arquitetura em camadas.
- Aplicar DDD, incluindo linguagem ubíqua, event storming e diagramas.
- Documentar APIs com Swagger ou ferramenta similar.
- Proteger APIs administrativas com autenticação JWT.
- Validar dados sensíveis, especialmente CPF/CNPJ e placa de veículo.
- Incluir testes unitários e de integração para os fluxos principais.
- Entregar Dockerfile e `docker-compose.yml`.
- Preparar README com instruções de uso.
- Gerar relatório de análise de vulnerabilidades.

### Pontos de esclarecimento do professor

- O projeto pode ser feito em grupo de até cinco pessoas ou individualmente.
- A entrega fora do prazo sofre desconto automático de nota.
- É permitido ir além do solicitado, mas a avaliação principal considera os requisitos obrigatórios.
- A documentação DDD pode ser feita em ferramenta visual como Miro, o que foi recomendado.
- O envio do orçamento pode ser simulado no MVP, desde que o fluxo e a mudança de status estejam claros.
- O domínio pode ser simplificado; não é necessário modelar compra de peças externas ou processos muito robustos.
- O tempo médio deve representar o tempo de execução dos serviços, não o tempo técnico de resposta da API.
- As regras de negócio que não estiverem explícitas devem ser inferidas pelo grupo e documentadas.
- O projeto será evoluído em fases futuras do curso, então decisões de arquitetura devem facilitar continuidade.

### Observação sobre a transcrição

A transcrição abaixo foi gerada automaticamente em ambiente local. Como o vídeo tem cerca de 1h40min e o ambiente não suportou modelos maiores, foi usado o modelo `tiny`; por isso, alguns trechos podem conter erros de reconhecimento e devem ser revisados manualmente antes de uso formal.

## Transcrição com timestamps
**[00:00:05 - 00:00:08]** Bom, galera, eu vou começar aqui, é primeiramente bom.

**[00:00:08 - 00:00:11]** Não te atose, eu me enfim um desses que...

**[00:00:11 - 00:00:15]** Aí eu professor Rogério, que hoje eu vou vocês.

**[00:00:15 - 00:00:20]** E hoje, como falamos na última semana,

**[00:00:20 - 00:00:24]** eu vou dizer onde irá apresentarmos aqui um pouco do desafio.

**[00:00:24 - 00:00:29]** Então, o que te anjequie, que vocês serão aqui entregado a final aqui da primeira fase.

**[00:00:29 - 00:00:35]** Então, o que é um espaço onde eu vou passar o PDF com vocês,

**[00:00:35 - 00:00:40]** vem nos desafios, todos os requisitos, tudo que a gente precisa entregar,

**[00:00:40 - 00:00:43]** da forma que a gente precisa entregar.

**[00:00:43 - 00:00:45]** E aí, eu espasse o bem aberto que, para...

**[00:00:45 - 00:00:48]** vocês tira em dúvidas, está tudo relacionado hoje,

**[00:00:48 - 00:00:52]** especificamente aqui a entrega tal dos desafios.

**[00:00:52 - 00:00:57]** Duvidas de conteúdo da matéria, mais dessa semana continuem ali,

**[00:00:57 - 00:01:01]** mandando um discorde, e logo, teremos um próximo encontro aqui,

**[00:01:01 - 00:01:04]** nas próximas semanas, com os encontros de grupo de estudantes,

**[00:01:04 - 00:01:07]** onde a gente pode discutir aqui, tem uma das alvas,

**[00:01:07 - 00:01:10]** está trazer algum tipo algum outro tipo de conteúdo, tudo mais.

**[00:01:10 - 00:01:15]** Mas hoje, o objetivo aqui é passar com vocês, de conta ponta,

**[00:01:15 - 00:01:18]** assim, traga aqui do que a gente está esperando,

**[00:01:18 - 00:01:23]** melhor forma de fazer, enfim, tudo que vocês sabem, sabe aí, tá bom?

**[00:01:23 - 00:01:25]** O vídeo pro professor Rogério, que fica de olho,

**[00:01:25 - 00:01:28]** na galera que tá entrando ainda, porque vai chegar a gente,

**[00:01:28 - 00:01:29]** e na terra, umas ou bitas de chegar a gente,

**[00:01:29 - 00:01:32]** o favor de mentir pessoal,

**[00:01:32 - 00:01:35]** quem tiver dúvida levanta mão aqui,

**[00:01:35 - 00:01:37]** eu não posso saber como falei, para falar,

**[00:01:37 - 00:01:40]** também no chat está disponível aqui para dúvida,

**[00:01:40 - 00:01:41]** eu tô de olho aqui do lado, hoje é do também,

**[00:01:41 - 00:01:46]** tá, no show, e eu não essa tá,

**[00:01:46 - 00:01:53]** bora lá, estão vendo minha tela?

**[00:01:53 - 00:01:54]** Sim, tem que ver.

**[00:01:54 - 00:02:02]** Então, vamos ver, bola, vamos lá, acho que acredito que,

**[00:02:02 - 00:02:05]** que a gente pode você já tira curiosidade,

**[00:02:05 - 00:02:11]** já tira baixados desafios, já tiveram,

**[00:02:11 - 00:02:12]** já saiu desafio lá também,

**[00:02:12 - 00:02:16]** se isso já sei lá para tá falando, provavelmente, é.

**[00:02:16 - 00:02:18]** Já assim, ver o professor.

**[00:02:18 - 00:02:20]** Bom, boa, deixa eu.

**[00:02:20 - 00:02:22]** Mas vamos passar aqui de ponta ponta, acho que nem tudo mundo,

**[00:02:22 - 00:02:24]** eu enfim, esse passo para a gente,

**[00:02:24 - 00:02:27]** quando a tiradas do beleza aqui necessária está,

**[00:02:27 - 00:02:28]** vou maralar então.

**[00:02:28 - 00:02:31]** É, como eu falei na aula de abertura,

**[00:02:31 - 00:02:33]** né, até que já não tinha que eu projear que dá faz,

**[00:02:33 - 00:02:36]** que em Glova que todos os conhecimentos

**[00:02:36 - 00:02:37]** serão obitídos aqui,

**[00:02:37 - 00:02:39]** todos as disciplinas das fazes,

**[00:02:39 - 00:02:42]** que no caso, na primeira faz.

**[00:02:42 - 00:02:43]** Então, uma motividade que,

**[00:02:43 - 00:02:45]** a princípios e da deslo de Glova do grupo,

**[00:02:45 - 00:02:48]** não é poder indo ou não se deslo de Glova do grupo,

**[00:02:48 - 00:02:49]** você não é individual também.

**[00:02:49 - 00:02:52]** O grupo até cinco pessoas,

**[00:02:52 - 00:02:54]** como falei também na última live,

**[00:02:54 - 00:02:55]** é importante, se a gente atentar,

**[00:02:55 - 00:02:58]** se é o prazo de entrega,

**[00:02:58 - 00:03:00]** é, pra baixo.

**[00:03:00 - 00:03:07]** entregue, posa a data combinada, da data determinada no próprio sistema da FIP, terá automaticamente

**[00:03:07 - 00:03:14]** um disconto de 30% de valor da nota, trazando, você pode trazer a 10 segundos, terá um

**[00:03:14 - 00:03:24]** desconto da nota, via sistema, então, um setente se atente ao prazo, beleza, como já comentem

**[00:03:24 - 00:03:29]** também, falando que esse trabalho vale 90 pontos, juntando de cooperate ao apresentar

**[00:03:29 - 00:03:34]** vocês vão fazer na frente, isso vai dar 100 pontos no final, tá? Bora por dizer

**[00:03:34 - 00:03:40]** afio, só pra gente alinhar que não sei se tudo é uma aqui, participar da live

**[00:03:40 - 00:03:50]** de abertura, eu tenho a oportunidade de ver alguém ficou com alguma dúvida em relação à nota,

**[00:03:50 - 00:03:55]** vamos ver que ele aqui não, tá, tranquilo, não sei, nem colocando no chat também, pode

**[00:03:55 - 00:04:02]** colocar aquele que eu tenho praia que eu não tenho, vamos ver desde, agora lá então,

**[00:04:02 - 00:04:14]** agora te, porra, que ele entendeu, a senhora se são das quatro fase sempre, quatro

**[00:04:14 - 00:04:22]** tem que ele te alente de 90 pontos, isso mesmo, isso aí, e mais uma coisa, vai ser,

**[00:04:22 - 00:04:27]** ouvi lá que existe uma somatória, 90, mais 10, e esses 10 pontos das

**[00:04:27 - 00:04:34]** solididades presencial, você vai ser somatima ou uma a cada final de fase, somatima, no final

**[00:04:34 - 00:04:41]** de curso, tá, certo, então tem de certo, beleza, olha só isso, uma aqui vai usar

**[00:04:41 - 00:04:52]** te usar pra compor, a minha nota de cada fase, beleza, beleza, vamos lá,

**[00:04:52 - 00:04:56]** desafio, qual que eu desafio aqui, uma voce na mecânica de mediportes, especializar em

**[00:04:56 - 00:05:00]** uma notência onde veículos, tem enfrentado desafios para expandir seus serviços com qualidade

**[00:05:00 - 00:05:06]** e eficiência, atualmente o processo de atendimento, diagnóstico, as execuições de serviços,

**[00:05:06 - 00:05:12]** entrega dos seus efeitos de forma desorganizada, utilizando atações manuales planilhas,

**[00:05:12 - 00:05:19]** o que gera alguns problemas, como, erro na priorização dos atendimentos, falhos no controle

**[00:05:19 - 00:05:26]** de peças em sul, dificuldade em acompanhar os status dos serviços, perda de histórico,

**[00:05:26 - 00:05:34]** de clientes e veículos, ineficiência no fluxo de orçamentos e autorizações, gente disso

**[00:05:34 - 00:05:39]** aficina decidiu investir em um sistema integrado de atendimento e a execução dos serviços,

**[00:05:39 - 00:05:45]** de serviços, que permitirá aos clientes acompanhar em tempo real, o andamento dos serviços,

**[00:05:45 - 00:05:50]** autorizar repar os atacionais de aplicativo e garantir uma gestão internaficiente

**[00:05:50 - 00:05:56]** e segura. Qual que a proposta? Então, nossa proposta quer desenvolver a primeira versão

**[00:05:56 - 00:05:59]** na MPP do back-end do sistema do ofcina.

**[00:06:00 - 00:06:06]** com foco na gestão de audience de serviço, cliente e peças, aplicando

**[00:06:06 - 00:06:11]** o Domain Drive in design, ddd e garantir nas boas práticas aqui de qualidade

**[00:06:11 - 00:06:16]** e desenvolvimento no geral do software, tá, e segurança.

**[00:06:16 - 00:06:19]** Beleza, quais são as funcionalidades obrigatórias aqui?

**[00:06:19 - 00:06:26]** Então, tudo que eu passar aqui de funcionalidade é o que vai entrar de fato

**[00:06:26 - 00:06:30]** na valhação da nota, vai compor a alhação à nota.

**[00:06:31 - 00:06:36]** A professora posso fazer mais do que estar aqui pode,

**[00:06:36 - 00:06:39]** legal, a gente avaria, eu avaria, eu passo no feedback que sempre eu usei

**[00:06:39 - 00:06:44]** diferenciais do projeto, mas, para contar que desalo a nota, para ser

**[00:06:44 - 00:06:47]** justo com todos, vai entrar somente do que está aqui, tal que a gente está

**[00:06:47 - 00:06:50]** pídido com obrigatório.

**[00:06:50 - 00:06:55]** Então, a fluxo de principais, criação da OS, da ordem de serviço,

**[00:06:55 - 00:06:57]** então, o que vai ter na criação da ordem de serviço?

**[00:06:57 - 00:07:02]** A edificação do cliente, por ser pédio, ser pédio, cada sort de veículo,

**[00:07:02 - 00:07:07]** claca, marca, modeliano, inclusive, solicitados,

**[00:07:07 - 00:07:10]** da um troca de olha, né, a minha almento, o frede por de reparo ali,

**[00:07:10 - 00:07:13]** porque é tipo de serviço.

**[00:07:13 - 00:07:17]** Possibilidade de incluir peças em sumos necessários,

**[00:07:17 - 00:07:22]** orçamento da cirurgia do automaticamente com base no serviço e peças

**[00:07:22 - 00:07:24]** necessários para fazer aqui da atendimento,

**[00:07:24 - 00:07:28]** o invíulador somente ao cliente para a provação.

**[00:07:28 - 00:07:31]** Temos outra fluxo que há acompanhamento da OS,

**[00:07:31 - 00:07:35]** então, estamos queriam um fluxo ali,

**[00:07:35 - 00:07:37]** através de uma peripla, a gente pode fazer o acompanhamento

**[00:07:37 - 00:07:41]** dos Estados da OS e dá uma mudança de estatus também, né?

**[00:07:41 - 00:07:45]** Então, esses são os Estados aqui, sempre tem a dúvida

**[00:07:45 - 00:07:48]** para o peço, possa adicionar estatos amais,

**[00:07:48 - 00:07:53]** possa criar estatos diferentes, pode, desde que você entra aí os Estados

**[00:07:53 - 00:07:56]** que estamos pedindo aqui, tá?

**[00:07:56 - 00:07:59]** E isso você quer criar mais fluxos que é uma oficina,

**[00:07:59 - 00:08:03]** um pouco mais robustas com mais estatos, né?

**[00:08:03 - 00:08:06]** A pode também, tá? Tranquilo, mas atentes,

**[00:08:06 - 00:08:10]** sempre, é o que está sendo solicitado em desafio.

**[00:08:10 - 00:08:14]** É, alteração automática dos Estados conforme a sua ex-systema,

**[00:08:14 - 00:08:17]** tá, então, quando eu, já vou carrer lá para o mecânea,

**[00:08:17 - 00:08:19]** que o entriendo diagnóstico,

**[00:08:19 - 00:08:22]** quando o mando o orçamento ali,

**[00:08:22 - 00:08:25]** que foi gerado automaticamente para a provação,

**[00:08:25 - 00:08:28]** o DNA, a OS tem que mudar para estar a gravar na provação.

**[00:08:28 - 00:08:31]** E, enfim, essa mudança de estatus automática baseada,

**[00:08:31 - 00:08:35]** com o Fionando, né? A LLS,

**[00:08:35 - 00:08:37]** e permite ter consulta por parte do cliente viafeir

**[00:08:37 - 00:08:39]** para acompanhar o seu preço.

**[00:08:39 - 00:08:42]** Então, como eu falei, não é uma peia que vai explorar

**[00:08:42 - 00:08:48]** de o Estado de OS, por o acompanhamento do cliente, tá?

**[00:08:48 - 00:08:51]** E agora, toda a parte de gestão administrativa também,

**[00:08:51 - 00:08:53]** que é um fluxo que a gente tá pedindo.

**[00:08:53 - 00:08:55]** Crou de disclinhe, escruz de diferentes,

**[00:08:55 - 00:09:00]** escruz de serviços, crou de piezas em sumos, com controle de...

**[00:09:00 - 00:09:07]** do stock, estágem, detalhamento de ordens de serviço, monitoramento do tempo médio,

**[00:09:07 - 00:09:09]** dias de circunção dos serviços.

**[00:09:09 - 00:09:15]** Então, quando a gente fala que crude de peças insubos com controle de stock, significa

**[00:09:15 - 00:09:24]** que as peças utilizadas aqui para o atendimento da OS, deverão ser baixadas ali de stock.

**[00:09:24 - 00:09:29]** Então, a gente perde aqui no desafio que seja feita, assim, a integração entre o fluxo

**[00:09:29 - 00:09:36]** da OS com o sistema de esteão de stock, que está de controle de stock.

**[00:09:36 - 00:09:42]** O monitoramento de tempo médio de execução dos serviços, então, também aqui, não está

**[00:09:42 - 00:09:48]** explícito, não desafia, mas é uma peita, é uma rota ali, onde pode que vai tornar

**[00:09:48 - 00:09:54]** monitoramento de monitorinal tempo médio de execução dos serviços.

**[00:09:54 - 00:10:00]** Então, a nível geral, todo o inferno.

**[00:10:00 - 00:10:05]** Isso seria somente do estado de execução ou da entrada da OS até entrega.

**[00:10:05 - 00:10:10]** Não, da execução dos serviços, isso é uma execução, né?

**[00:10:10 - 00:10:11]** É, isso é isso.

**[00:10:11 - 00:10:17]** Tem que demorar o que demorou para ser realizado de fato ou serviço aqui, né?

**[00:10:17 - 00:10:18]** Que você é excitado.

**[00:10:19 - 00:10:22]** Ou o monitoramento, isso é uma coisa mais uma pergunta.

**[00:10:22 - 00:10:25]** Mas não é o tempo da requisição, né?

**[00:10:25 - 00:10:32]** Visam de, sei lá, mil e seguros que por feita requisição, não chega nesse merito, né?

**[00:10:32 - 00:10:35]** É, você vai ter que colocar ali, né?

**[00:10:35 - 00:10:43]** Um time tempo de quando ela entrou de fato nessa necessidade, e quando ela mudou de estratos, né?

**[00:10:43 - 00:10:44]** Tá, você vê, né?

**[00:10:45 - 00:10:50]** Então, a gente pode ser até mil e seguros, mas não que isso seja necessário, né?

**[00:10:50 - 00:10:54]** Para a gente monitorar, porque é um tempo médio, tá?

**[00:10:54 - 00:10:56]** Berenzo, vai dar.

**[00:10:56 - 00:11:03]** É, vou aproveitar que tão pobre aqui, é, não, mas deixa eu terminar só que o fluxo,

**[00:11:03 - 00:11:08]** segurança e qualidade, que já entra meio que, pouco de requisito técnico também,

**[00:11:08 - 00:11:14]** mas implementação de autenticação de autadabilter para a Peiza administrativas, tá?

**[00:11:14 - 00:11:21]** Então, todo esse acesso aqui de crúdito, do mais, tem que ter a tenta de cação,

**[00:11:21 - 00:11:27]** validação dos dados sensíveis, então CPF, CNPJ, o atrapalho de veículo,

**[00:11:27 - 00:11:31]** e testes unitários e de integração para os principais fluxos, tá?

**[00:11:31 - 00:11:36]** Pensei pais fluxos, são esses fluxos aqui, principais mesmas, então,

**[00:11:36 - 00:11:42]** criação de alguém de serviço, acompanhamento, e essa parte de gestão de mensativa,

**[00:11:42 - 00:11:47]** todos isso daqui, são os fluxos que a gente posile a principais, tá?

**[00:11:47 - 00:11:52]** Beleza, aqui, validação dos dados sensíveis também, CPF, CNPJ,

**[00:11:52 - 00:11:56]** uma dica até para vocês aqui, faça a validação real, tá?

**[00:11:56 - 00:12:00]** Não só de tamanho de campo, tem aí...

**[00:12:00 - 00:12:04]** eu teca disponíveis de monte para toda linguagem, que a gente consegue validar a

**[00:12:04 - 00:12:11]** paca, me acusou, placa normal, no formato antigo, você percebe que é joa, então

**[00:12:11 - 00:12:15]** uma dica que para vocês, tá? E aí eu vou abrir agora antes de que a gente cai

**[00:12:15 - 00:12:20]** para o exito o técnico para receber em alguma dúvida em relação aos fluxos aqui.

**[00:12:20 - 00:12:23]** Só se eu me reenistar aqui.

**[00:12:23 - 00:12:29]** Geralmente a gente tem que extrair um pouco, que geralmente eu tenho

**[00:12:29 - 00:12:34]** uma vez que tem ofcina e ele entrega para o cliente ao a S com a necessidade

**[00:12:34 - 00:12:38]** de compra da peça x, por exemplo, com x-coz, de eletrólica, elétrica do

**[00:12:38 - 00:12:43]** carro, ele não vai ter em stock, então a peça de 3 mil reais. Então ele vai

**[00:12:43 - 00:12:46]** neutralizada, passou de somente para o cliente e autoriza e ele vai

**[00:12:46 - 00:12:52]** lá e compra. Pra esse caso a gente tem que fazer uma protatima mais perto do

**[00:12:52 - 00:12:58]** real, por exemplo, filtro de olhos, essas coisas, hoje a gente abstrair tem

**[00:12:58 - 00:13:02]** uma mecânica mais simples de manutenção de coisas que normalmente

**[00:13:02 - 00:13:06]** moficina teriam no stock e tem que esperar, assim.

**[00:13:06 - 00:13:10]** É assim, o que esperar, o mateix que você falou, tá? Então a

**[00:13:10 - 00:13:16]** abstrair esse tipo de coisa para a gente não deixar muito robusto o sistema

**[00:13:17 - 00:13:22]** porém tem vários grupos aqui de outras turmas, tá aqui, entregam algo

**[00:13:22 - 00:13:26]** parecido com que você falou, tá? Então não tem a ter aí, se pode

**[00:13:26 - 00:13:30]** ter algo para a gente compra, é isso? E o cliente joga como um

**[00:13:30 - 00:13:33]** conhecedor que teria só aí. Não pode ter o sistema extremisté, não

**[00:13:33 - 00:13:37]** deixamos uma terila de compa, tá? E o que? Uma outra pergunta que não

**[00:13:37 - 00:13:43]** é ali é seria a válida, a gente restringir e inventar um pouco, por exemplo,

**[00:13:43 - 00:13:47]** a minha oficina é a preparação de Honda Civic. Aí eu consigo fazer um negócio

**[00:13:47 - 00:13:52]** mais maneiro, só que só com as peças daquele carros, peças, ou com

**[00:13:52 - 00:13:56]** coisa assim. Cara, por exemplo, a gente assim é bem aberto,

**[00:13:56 - 00:14:01]** uma terza ou desafio, assim, desde que uma forma entregue esses

**[00:14:01 - 00:14:05]** equipos mínimos, tá? Sim. Agora, ideias, cara, pode ter de

**[00:14:05 - 00:14:10]** monte aí. A gente não vai avaliar esse tipo de distlação, tá?

**[00:14:10 - 00:14:14]** A gente vai avaliar, de fato se eu sei que os mínimos aqui dos fluxos

**[00:14:14 - 00:14:18]** estão falando desenvolver, eles enfim foram amapiados ali,

**[00:14:18 - 00:14:22]** não todo fluxo ali, no Jagamas, você não mudar,

**[00:14:22 - 00:14:25]** domenistas terem, é vim store, muito do marista.

**[00:14:25 - 00:14:29]** Bem, gente, mas a gente pode ser um pouco creativo assim, no caso de

**[00:14:29 - 00:14:32]** preta-lo. E eu olho, tá?

**[00:14:32 - 00:14:35]** Antes assim, vocês vão ver no final de na

**[00:14:35 - 00:14:40]** coisa, só dar uma dada de vocês, que eu não fico de ver que bem legal assim,

**[00:14:40 - 00:14:43]** bem dividido ao mesmo pra cara através, tá?

**[00:14:43 - 00:14:45]** A manhã.

**[00:14:45 - 00:14:47]** Bem, inclusive, a gente vai participar no momento, também,

**[00:14:47 - 00:14:50]** o procanei, né, é sobre o projeto que está sendo feito.

**[00:14:50 - 00:14:51]** E vai estar a mente.

**[00:14:51 - 00:14:57]** Isso não é só no final, né, que a gente entra.

**[00:14:57 - 00:14:58]** Isso aí.

**[00:14:58 - 00:15:00]** É, tem algumas perguntas...

**[00:15:00 - 00:15:07]** que eu, Mateus, para responder, é para a primeira pergunta do Lucas, como deve ser essa questão

**[00:15:07 - 00:15:12]** de envio da S para a proporcismo seria vieim, meio?

**[00:15:12 - 00:15:19]** É assim, vieim, é mais como a gente não tá pedindo, a gente não tá especificando,

**[00:15:19 - 00:15:24]** né, como se é, então pode ser de forma mocado a mesma, tá?

**[00:15:24 - 00:15:33]** Espar ali, um, sei lá, eu não serviria um método mesmo de envio de orçamento e tem uma resposta

**[00:15:33 - 00:15:38]** de orçamento enviada, guardando a provação em mudos Estados da OS e boa, esse passo.

**[00:15:38 - 00:15:44]** Agora, se quiser criar, aí eu te desalgo uma ferramenta que é isso, para fazer envio de meio

**[00:15:44 - 00:15:46]** de fato, beleza, hoje boa, tá?

**[00:15:46 - 00:15:52]** Mas pode mocar mesmo, se cara, outra Austática da OS e seguim frente, como se tivesse enviado.

**[00:15:52 - 00:15:59]** Pois até declarar que vai que a ideia do envio de alguma ferramenta na documentação,

**[00:15:59 - 00:16:05]** mas no código, se não precisa fazer, é um mídi, essa é isso.

**[00:16:05 - 00:16:12]** É, tem mais algumas perguntas aqui, na Tolivera, ela fez quatro perguntas no contexto

**[00:16:12 - 00:16:17]** sóta, a documentação de poder ser entregue, marquidá-lo, com de agramas, uma emmede,

**[00:16:17 - 00:16:22]** dentro do reposo-tório, ou é obrigatório, ou seja, não é uma ferramenta visor com o

**[00:16:24 - 00:16:25]** o primeiro.

**[00:16:25 - 00:16:28]** Comendo fortemente, ferramenta visor com o muniro, tá?

**[00:16:28 - 00:16:35]** Para donizar 99% dos grupos, sentré-go, um comirro, vira marquidá-lo, um pouco mais

**[00:16:35 - 00:16:45]** chato, até para a gente, avaliar, já teve a gente já mandou aqui da última turma,

**[00:16:45 - 00:16:49]** marquidá-lo, um zalmelo tudo, por escrito mesmo, aí falar para o preústalo, uma ferramenta

**[00:16:49 - 00:16:55]** aqui, para subir uma quidá-la, um traígê-la aos diagramas, faz de amir o que atende bem,

**[00:16:55 - 00:16:56]** tá?

**[00:16:56 - 00:17:00]** Facelita aqui a coissão, a visualização do projeto também, com todo o padrão,

**[00:17:00 - 00:17:04]** e isso aqui é grande parte, é grande maioria, mesmo, um traígano, um miro, um filme

**[00:17:04 - 00:17:05]** maravilhoso, tá?

**[00:17:05 - 00:17:07]** Não, não.

**[00:17:07 - 00:17:13]** Qual o peso relativo entre DVD, código e testes?

**[00:17:13 - 00:17:18]** Depois eu posso até mostrar para vocês aqui, no final abre que eu vou que sê,

**[00:17:18 - 00:17:22]** o depente do da distribução de peso, tá?

**[00:17:22 - 00:17:28]** Então, é o mesmo peso, pra cada... vamos dizer assim, cada disciplina, tá?

**[00:17:28 - 00:17:36]** Então, a gente tem teste, tem o evento storme, é do evento start-telling,

**[00:17:36 - 00:17:41]** tem o peso pra lhe doaker, não vou lembrar exatamente tudo que tem ali, agora,

**[00:17:41 - 00:17:43]** Eu tenho a partir de segurança também,

**[00:17:43 - 00:17:45]** então que a gente está pedindo aqui.

**[00:17:45 - 00:17:48]** Eu tenho o tagu Excel aqui, eu mostrei vocês no final.

**[00:17:48 - 00:17:51]** Então tudo que vai ser...

**[00:17:51 - 00:17:53]** Está sendo aliado, peso, os iticetas,

**[00:17:53 - 00:17:56]** e estiver exposto no final, então né?

**[00:17:56 - 00:17:57]** Acho que a gente tem uma tenda para debutter,

**[00:17:57 - 00:18:00]** tem as rotas também, se todas as rotas.

**[00:18:00 - 00:18:03]** aqui pedia as correntrais, então a gente assim está.

**[00:18:03 - 00:18:08]** Ela pergunta o atático na entrega do vídeo com as pontos que a gente vai valiar,

**[00:18:08 - 00:18:15]** isso está naquele que se luta mesmo, ou seja, o que será analisado na entrega do vídeo,

**[00:18:15 - 00:18:17]** com as pontos precisamos usar a gente car.

**[00:18:17 - 00:18:19]** O vídeo a gente não vai...

**[00:18:19 - 00:18:22]** Eu vou falar aqui para a gente para baixo que estão de entregáveis.

**[00:18:22 - 00:18:25]** Deixa eu dar ele para baixo e eu falo um pouco de entregáveis.

**[00:18:25 - 00:18:26]** Vamos ver o que é?

**[00:18:26 - 00:18:28]** Vamos para o próximo.

**[00:18:28 - 00:18:37]** No vídeo, a nível de projeto mesmo, esses extras podem acabar ajudando demais.

**[00:18:37 - 00:18:43]** Olha, que essa é o que eles se anem a mais pode ajudar nas próximas fase do projeto,

**[00:18:43 - 00:18:49]** tipo, se nós próximas fase do curso, vai reutilizar um coisa do projeto, acho que é isso.

**[00:18:49 - 00:18:52]** Vai reutilizar, basicamente, tudo do projeto.

**[00:18:52 - 00:18:59]** Então, você vai usar o mesmo projeto e você vai começar a reutilizar,

**[00:18:59 - 00:19:04]** por uma vez, levar ele para uma outra que é a captura,

**[00:19:04 - 00:19:09]** já aprende a clinhar que ela nasse uma fase, então vai sair daqui de um MVC,

**[00:19:09 - 00:19:16]** e a MVC dependente que você pode fazer um monolítico para um clinhar,

**[00:19:16 - 00:19:19]** que já começamos a usar a minha televisão.

**[00:19:19 - 00:19:22]** A gente vai evoluir esse projeto ao curso.

**[00:19:22 - 00:19:28]** O que é a outra, como umas dúvidas aqui em relação à especialista de negócio,

**[00:19:28 - 00:19:31]** as regras de negócios, vocês vão ter que inferir,

**[00:19:31 - 00:19:35]** se eles vão ter alguém que vai ajudar, vai ter um especialista,

**[00:19:35 - 00:19:39]** que é a gente que só que vai ser muito na base da inferência, né?

**[00:19:39 - 00:19:41]** Isso, é isso mesmo, pessoal.

**[00:19:41 - 00:19:45]** Ou você escolha comendo o grupo de vocês para fazer esse papel,

**[00:19:45 - 00:19:49]** você vai dizer, ver, é um ali entre si, mas não tem ninguém de esténio,

**[00:19:49 - 00:19:52]** mas não vai fazer esse papel que para vocês, o grupo, mesmo.

**[00:19:52 - 00:19:55]** Ou vocês chamam o amigo do nosso,

**[00:19:55 - 00:19:58]** uma das pessoas que perguntam aqui, que tem uma oficina,

**[00:19:58 - 00:20:01]** pode tovesse-se via de especialista de negócios.

**[00:20:01 - 00:20:06]** Quando vai ter um alfacero, não estou tendo que dá,

**[00:20:06 - 00:20:09]** não faz de fúdio assim, não se tem uma de todos,

**[00:20:09 - 00:20:12]** tem assim de pedida, tem uma galera que é assim,

**[00:20:12 - 00:20:13]** em esta hora, não é?

**[00:20:13 - 00:20:17]** Pide, eu opinionia, não tá, mas não precisa chegar nesse nível,

**[00:20:17 - 00:20:20]** mas não precisa, é, fica um taje também.

**[00:20:20 - 00:20:22]** Tá.

**[00:20:22 - 00:20:25]** Não, não, você já pode inferir também, não,

**[00:20:25 - 00:20:27]** mas só a gente, cada uma está entrevista,

**[00:20:27 - 00:20:30]** com alguém sobre o domino de negócio.

**[00:20:30 - 00:20:33]** Monitualmente então não é para ser feito

**[00:20:33 - 00:20:35]** com alguma PM tipo da Tador, etc.

**[00:20:35 - 00:20:38]** alguém está perguntando sobre a partir de observabilidade.

**[00:20:38 - 00:20:40]** Ainda não está sendo exigido, tá,

**[00:20:40 - 00:20:43]** porque não tem essa disciplina ainda na fase 1.

**[00:20:43 - 00:20:46]** Nas próximas fases, vai entrar essa disciplina

**[00:20:46 - 00:20:48]** e vai ser cobrado.

**[00:20:49 - 00:20:52]** Bom, né?

**[00:20:52 - 00:20:59]** Ah, existe uma uma específica de troca de status.

**[00:21:00 - 00:21:05]** A ordem vai acabar sendo parecida com essa,

**[00:21:05 - 00:21:08]** e isso é muito disso.

**[00:21:08 - 00:21:11]** Tem uma ordem padrão de que este é o rurvico,

**[00:21:11 - 00:21:16]** entre diagnóstico, mando orçamento,

**[00:21:16 - 00:21:18]** para o vou executar na desintrague.

**[00:21:18 - 00:21:21]** Pode fazer tensa ecução,

**[00:21:21 - 00:21:24]** de repente sei lá,

**[00:21:24 - 00:21:27]** permitando um pouco foi testável,

**[00:21:27 - 00:21:29]** que não funcionou, entre a diagnóstico de novo,

**[00:21:29 - 00:21:32]** para a aprovação, pode voltar para os Estados Unidos,

**[00:21:32 - 00:21:33]** e depois,

**[00:21:33 - 00:21:36]** mas a ordem me quer essa, tá?

**[00:21:36 - 00:21:40]** Alguém é que pergunte por rejects,

**[00:21:40 - 00:21:43]** eu não sei acho que foi na pergunta.

**[00:21:43 - 00:21:45]** Tem bastante pergunta aqui,

**[00:21:45 - 00:21:47]** vou melhorar o pouco mais para a frente,

**[00:21:47 - 00:21:49]** depois, porque isso não vai conseguir continuar.

**[00:21:49 - 00:21:51]** Muito vergonha, ainda.

**[00:21:51 - 00:21:53]** O jogo toca a movem antada,

**[00:21:53 - 00:21:56]** sei que você dá por rejects,

**[00:21:56 - 00:21:58]** eu acho que foi para a placa.

**[00:21:58 - 00:22:00]** É, isso não é?

**[00:22:01 - 00:22:03]** O que é com a doida só?

**[00:22:03 - 00:22:07]** Ainda a questão que tem que ter os diagramas do DDD.

**[00:22:07 - 00:22:09]** Quais diagramas, altamente,

**[00:22:09 - 00:22:12]** teriam os diagramas de domínio,

**[00:22:12 - 00:22:15]** os dicionários, de linguagem o bico,

**[00:22:15 - 00:22:18]** e o mapa de contexto, teriam mais alguma coisa,

**[00:22:18 - 00:22:21]** além desses três que eu conseguiri uma piac.

**[00:22:21 - 00:22:22]** Do mesmo enquície,

**[00:22:22 - 00:22:24]** é bem de storming,

**[00:22:24 - 00:22:25]** e o legal da vento storming,

**[00:22:25 - 00:22:28]** é vocês, mapiando conforme as alas lifapassando,

**[00:22:28 - 00:22:30]** e aí, todos os fluxos ali até chegar na modelo

**[00:22:30 - 00:22:32]** e de agregados,

**[00:22:33 - 00:22:36]** o dicionário?

**[00:22:36 - 00:22:38]** E é isso, cara?

**[00:22:38 - 00:22:39]** A gente deu muita想.

**[00:22:39 - 00:22:40]** A gente deu isso, tá?

**[00:22:40 - 00:22:41]** Me leso.

**[00:22:41 - 00:22:42]** Brenham.

**[00:22:42 - 00:22:44]** Eu vou falar lá embaixo sobre as entregas.

**[00:22:44 - 00:22:45]** Acho.

**[00:22:45 - 00:22:48]** Tá? Vamos finalizar só que o fluxo,

**[00:22:48 - 00:22:49]** o total tenta no liar de ouvido,

**[00:22:49 - 00:22:50]** e desque para este lugar,

**[00:22:50 - 00:22:54]** mas alguma doida é relação ao fluxo

**[00:22:54 - 00:22:56]** aqui, para a incitais, e o que eu perai.

**[00:22:56 - 00:22:58]** Aqui, mais uma da vitória, que ela falou,

**[00:22:58 - 00:22:59]** não ficou muito claro,

**[00:22:59 - 00:23:01]** só do tempo, meia de desicustão de serviço.

**[00:23:01 - 00:23:02]** Isso tinha o tempo,

**[00:23:02 - 00:23:03]** quando alas se abriu,

**[00:23:03 - 00:23:04]** quando foi concluída,

**[00:23:04 - 00:23:06]** e o antipónde devre returnar para o S,

**[00:23:06 - 00:23:07]** o porto dos as o S.

**[00:23:07 - 00:23:10]** Quando a de fato o serviço entre uma execução,

**[00:23:10 - 00:23:12]** então, quando vai aprovado,

**[00:23:12 - 00:23:14]** pelo cliente al S,

**[00:23:14 - 00:23:16]** quando a execução,

**[00:23:16 - 00:23:19]** até ela se finalizada, o serviço.

**[00:23:22 - 00:23:23]** É, é...

**[00:23:23 - 00:23:24]** É, a verdade, gente.

**[00:23:24 - 00:23:25]** Aqui, arpedindo,

**[00:23:25 - 00:23:30]** e tal, é só pra fazer aqui uma coisa que eu falei assim.

**[00:23:31 - 00:23:34]** Porque eu lembrei que foi uma doida que gerou na outra turma,

**[00:23:34 - 00:23:36]** e que a gente seguiu de uma outra forma,

**[00:23:36 - 00:23:38]** porque ele não tá pedindo,

**[00:23:38 - 00:23:41]** porque ele pode ter mais um serviço, né.

**[00:23:41 - 00:23:46]** Aqui, ele tá falando numa, no uma,

**[00:23:46 - 00:23:49]** de uma grandeza de O S,

**[00:23:49 - 00:23:51]** tá, se me enque perai, deixou, deixou,

**[00:23:51 - 00:23:54]** deixou, deixou, deixou de novo aqui.

**[00:23:54 - 00:23:56]** Partido que a gente queria ter, para comprar,

**[00:23:56 - 00:23:58]** aqui.

**[00:24:00 - 00:24:02]** Choro como a outra trauma é,

**[00:24:02 - 00:24:05]** ela é, morrer turamente como eles são dos serviços.

**[00:24:05 - 00:24:10]** Vamos manter a própria no serviços, mesmo.

**[00:24:10 - 00:24:12]** Por mais que ele tenha mais de um serviço,

**[00:24:12 - 00:24:15]** a trilada OS,

**[00:24:15 - 00:24:19]** a gente consegue monitorar as opções de serviços.

**[00:24:19 - 00:24:23]** Então, vamos colocar como se aqui, igual eu está falando no documento.

**[00:24:23 - 00:24:27]** E é geral, não é por OS.

**[00:24:27 - 00:24:29]** Então, todos os serviços da minha oficina,

**[00:24:29 - 00:24:32]** todos os serviços sem contar a lisa OS.

**[00:24:32 - 00:24:33]** No geral mesmo.

**[00:24:33 - 00:24:35]** O que eu tenho por meio de eu pensar,

**[00:24:35 - 00:24:36]** o quanto meu aficina está levando

**[00:24:36 - 00:24:40]** para executar a linda e media, o serviço, você está.

**[00:24:40 - 00:24:45]** No geral de todos os clientes.

**[00:24:45 - 00:24:47]** Vitorialmente, eu vou dizer aqui o noteus.

**[00:24:47 - 00:24:50]** Qual o seu limite da sala mesmo?

**[00:24:50 - 00:24:51]** Três entes.

**[00:24:51 - 00:24:53]** Tá, não, eu estou no vete, engino.

**[00:24:53 - 00:24:55]** Tá bem.

**[00:24:55 - 00:24:59]** É, tá tentando deixar um pouquinho mais clara minha dúvida.

**[00:24:59 - 00:25:02]** Então, assim, você tinha citado que,

**[00:25:02 - 00:25:04]** pra esse monitoramento, a gente me eviteria um em-spóente.

**[00:25:04 - 00:25:10]** Então, eu queria entender se é um em-spóente em que um empúte seria o ID de uma OS.

**[00:25:10 - 00:25:13]** E o retorno é o tempo médio daquela ONI, com esse.

**[00:25:13 - 00:25:17]** Ou eu tenho que pegar todas as onesas que eu tenho no meu banco,

**[00:25:17 - 00:25:22]** ou ver ali qualquer o tempo que demora para concluir e calcular a média.

**[00:25:22 - 00:25:23]** Então é nesse...

**[00:25:23 - 00:25:30]** Então, todos os serviços, todos os serviços,

**[00:25:30 - 00:25:33]** todos os serviços que foram executados,

**[00:25:33 - 00:25:37]** que pode ser, por exemplo, troca de óleo e aliamento,

**[00:25:37 - 00:25:38]** bem, dentro de uma OS.

**[00:25:38 - 00:25:43]** Então, cada vez pode ter um ou mais serviços.

**[00:25:43 - 00:25:47]** E aí você quer saber o tempo médio de cada serviço?

**[00:25:47 - 00:25:48]** É.

**[00:25:48 - 00:25:50]** Tá, todos os clientes.

**[00:25:50 - 00:25:52]** Não, não clençado.

**[00:25:52 - 00:25:56]** E isso, na real você não vai nem poder se basear pelo estado da OS.

**[00:25:56 - 00:26:01]** Assim, se você vai ter um estado de execução de serviço,

**[00:26:01 - 00:26:05]** porque um serviço, uma OS, como está aqui,

**[00:26:05 - 00:26:06]** não é que os ones serviços estão,

**[00:26:06 - 00:26:08]** pode ter um ou mais serviços.

**[00:26:08 - 00:26:09]** E para eu finalizar o OS,

**[00:26:09 - 00:26:11]** eu tenho que estar confluído todos os serviços,

**[00:26:11 - 00:26:14]** que fazem parte daquela OS.

**[00:26:14 - 00:26:17]** É, é que pensando em um capenho de tequete médio,

**[00:26:17 - 00:26:18]** do cara, a tempo...

**[00:26:18 - 00:26:19]** Essa é...

**[00:26:19 - 00:26:26]** Quer dizer, curta tal, pensando nesse capenho.

**[00:26:26 - 00:26:27]** Obrigado.

**[00:26:27 - 00:26:31]** Imagina.

**[00:26:31 - 00:26:33]** O agaséuera deixa eu ver que estima,

**[00:26:33 - 00:26:37]** é alguma coisa para gente passar.

**[00:26:37 - 00:26:38]** Sou pra entrar, eu vou falar lá embaixo.

**[00:26:38 - 00:26:39]** Tá, pessoal.

**[00:26:39 - 00:26:41]** Eu tô filhando nós do vés de entrega.

**[00:27:04 - 00:27:07]** para a venta, isso aí, beleza?

**[00:27:07 - 00:27:12]** A sua boa passada aqui, e se a gente vai fazer uma dúvida,

**[00:27:12 - 00:27:16]** eu peguei numa x aqui, eu vou responder no ta,

**[00:27:16 - 00:27:20]** aí quando a gente vai, como eu te passo de final.

**[00:27:20 - 00:27:21]** Tem uma dúvida sobre...

**[00:27:21 - 00:27:25]** Eu vou ver no chat, ele vai sobre a validação,

**[00:27:25 - 00:27:28]** você não é peijota, você preferfe e,

**[00:27:28 - 00:27:30]** basicamente, a gente é disto verificador,

**[00:27:30 - 00:27:34]** ou é o calco mesmo, e vou ver com o suto de uma peio para...

**[00:27:34 - 00:27:37]** Não, você é o calco, você é o calco, só o calco.

**[00:27:37 - 00:27:38]** Ah, beleza.

**[00:27:38 - 00:27:47]** Contressão status, isso aí é um indipóente mesmo,

**[00:27:47 - 00:27:50]** que é a tranção status, pode ser um indipóente.

**[00:27:50 - 00:27:54]** Um relação ao CPF, e a gente olhe vai em conta

**[00:27:54 - 00:27:58]** ao fenômeno é o que já, um epto.

**[00:27:58 - 00:28:00]** É sempre pejorado a você é pejota, né?

**[00:28:00 - 00:28:01]** É isso.

**[00:28:01 - 00:28:03]** Ah, é verdade, é verdade, é verdade.

**[00:28:03 - 00:28:04]** Pode colocar já.

**[00:28:04 - 00:28:06]** Ah, sim, eu...

**[00:28:06 - 00:28:08]** Todo mundo da actória.

**[00:28:08 - 00:28:13]** Tá, né, morrendo, então.

**[00:28:13 - 00:28:15]** Obrigado.

**[00:28:15 - 00:28:17]** Eu sou o Brecassão.

**[00:28:17 - 00:28:18]** Isso.

**[00:28:18 - 00:28:20]** Ah, tem...

**[00:28:20 - 00:28:23]** Ele pode ser um nólito, vai, PC.

**[00:28:23 - 00:28:25]** Eu vou falar, na frente, tá?

**[00:28:25 - 00:28:26]** Ainda vai.

**[00:28:26 - 00:28:27]** Tá, vou.

**[00:28:27 - 00:28:30]** Bom, vamos passar, vou responder a última aqui,

**[00:28:30 - 00:28:31]** que é a última, a JTW,

**[00:28:31 - 00:28:32]** a Penas Paras Rata das Administrativas,

**[00:28:32 - 00:28:34]** toda só administrativa, tá?

**[00:28:34 - 00:28:37]** Então, só essas luta aqui dentro de gestão administrativa,

**[00:28:37 - 00:28:40]** eu preciso de autênticação para acessar.

**[00:28:40 - 00:28:42]** Bom, galera, vamos seguir aqui.

**[00:28:42 - 00:28:47]** time to the final, a gente te enxerte ou essas tás tubulas.

**[00:28:47 - 00:28:50]** É requisitos técnicos, daqui a gente monolítico.

**[00:28:50 - 00:28:53]** Tá, então, por que a gente perde um back end monolítico?

**[00:28:53 - 00:28:56]** Porque a gente vai evoluir um a frente

**[00:28:56 - 00:28:58]** pra me encrocer isso.

**[00:28:58 - 00:29:01]** Então, não vamos plar as etapas,

**[00:29:01 - 00:29:05]** o pouquinho que não olorítico, mais básico.

**[00:29:05 - 00:29:07]** Pra depois a gente ter como evoluir, enfim,

**[00:29:07 - 00:29:10]** aplicado que a gente for aprendendo a loucura, tá?

**[00:29:10 - 00:29:15]** Aqui a gente pode usar uma que ter túrne camadas, tá?

**[00:29:15 - 00:29:17]** Não precisa, eu não sei usar o Instagram, não.

**[00:29:17 - 00:29:20]** O cliente, aqui, nada do tipo, porque também, como eu falei,

**[00:29:20 - 00:29:25]** a ideia evoluir isso, cara, nas próximas fases,

**[00:29:25 - 00:29:30]** a pude, são muito ferem, tem a arte, já quero sair fazendo aqui.

**[00:29:30 - 00:29:36]** Tudo bem, não tem problema, tá?

**[00:29:36 - 00:29:39]** É, escolha do banco de dados ali,

**[00:29:39 - 00:29:41]** mas é necessário de ficar na preferência do banco de risado,

**[00:29:41 - 00:29:44]** então, a documentação de vocês é importante

**[00:29:44 - 00:29:48]** de justificar aqui a escolha do banco, tá?

**[00:29:48 - 00:29:52]** Porque não sei, com o trico, enfim.

**[00:29:52 - 00:29:56]** É, a Pei Reste documentada os viaço-sleger,

**[00:29:56 - 00:29:59]** o similar, então tem gente que vai suerga, tem gente que...

**[00:30:00 - 00:30:05]** de a coletron, pouestima, tem essas formas de vocês apresentarem.

**[00:30:05 - 00:30:15]** Doctrfaiu para a biodad aplicação, toca a composição para o que estrar o ambiente completo,

**[00:30:15 - 00:30:21]** testes automatizados com o cobertura mínima de 80% nos domínios críticos, configuração para

**[00:30:21 - 00:30:26]** execução local simples, está com o regime alí explicativo de como a gente subir

**[00:30:26 - 00:30:32]** seu ambiente aqui, porque a gente tem que subir para testar, validar, e tudo mais.

**[00:30:32 - 00:30:37]** E organização em repositorio privado com acesso ao usuário solte, arquitecture.

**[00:30:37 - 00:30:44]** Então, repositorio guit, rub, que é onde temos nossos vários que da fiapi, esses

**[00:30:44 - 00:30:52]** usuários aqui, não subam o meu trorepo, e em outra ferramenta subando guit, rub, tá?

**[00:30:52 - 00:30:57]** Repositorio privado, sempre, e aí libera esse acesso aqui.

**[00:30:57 - 00:31:03]** Tem gente acabar construindo esse repositorio muito antes da lata de entrega, é isso

**[00:31:03 - 00:31:08]** de se estão acessos, a gente acaba perdendo, perdendo ali a aprovação da cessa,

**[00:31:08 - 00:31:11]** aí a gente entra em contato com vocês para pedir de novo.

**[00:31:11 - 00:31:19]** Então, aqui é muito importante focar do acerfaiu, do acercomposito, para a gente conseguir

**[00:31:19 - 00:31:24]** de fato subiromente forma simples, sempre usar confurar a nossa máquina, então, saldo

**[00:31:24 - 00:31:31]** o que eu comproziar pela isso, bionhamente, para a gente validar, tá bom?

**[00:31:31 - 00:31:36]** O ISEU, os entregáveis de fato, que a gente tem que entregar.

**[00:31:36 - 00:31:40]** Então, um vídeo de até 15 minutos demonstrando todos os pontos, pode ser no grupo individual,

**[00:31:40 - 00:31:46]** então, pode ser ele tudo mundo falando, pouco cada, não falam pouco, olha o tempo, ou uma

**[00:31:46 - 00:31:49]** pessoa só gravar também, não tem problema.

**[00:31:49 - 00:31:56]** O vídeo não vai entrar, a gente não vai avaliar de fato conteúdo, gente, não vai

**[00:31:56 - 00:32:00]** ter uma avaliação específica com uma nota com peso do conteúdo.

**[00:32:00 - 00:32:05]** É importante fazer entregar o vídeo, com a explicação, mas a gente não vai dar uma nota

**[00:32:05 - 00:32:11]** se foi bom, se foi o vídeo, tá, mas é legal fazer, é legal não, é importante fazer

**[00:32:11 - 00:32:15]** que ele contabiliza ali para a nota, o vídeo entrega ali também, não vai fazer um vídeo

**[00:32:15 - 00:32:19]** de um minuto, esse minuto está explicando de fato ele funcionamento, então,

**[00:32:19 - 00:32:24]** é a avalia um míro, explico um pouquinho como que vocês desenvolveram o fluxo, entra um pouquinho

**[00:32:24 - 00:32:29]** ali na do código, detalhe um pouco ali que foi criado dentro do código, se quiser também

**[00:32:29 - 00:32:34]** mostrar ali um suéguer ou poste, não é ali funcionando, fazendo uma estejeamar dos

**[00:32:34 - 00:32:38]** de ATI, para demonstrar um pouco de funcionamento de sistema, é legal, tá?

**[00:32:38 - 00:32:44]** Aqui é mais pra vocês praticarem, ter essa prática de apresentarem, de criar em conteúdo,

**[00:32:44 - 00:32:50]** a gente força bastante, e assim agora, ter essa cultura disso, tá?

**[00:32:50 - 00:32:57]** Do comentação de LED, então o míro é equivalente, eu vi o que falando aqui que nas aulas

**[00:32:57 - 00:32:59]** estão usando uma...

**[00:33:00 - 00:33:01]** outra ferramenta de pra fazer o domingo de storytelling.

**[00:33:01 - 00:33:05]** Pode usar também outra ferramenta pra fazer a boa pra fazer o domingo de storytelling,

**[00:33:05 - 00:33:07]** tem ali os bonequinhos e tudo mais.

**[00:33:07 - 00:33:11]** Mas pra vinguar-me, que é basicamente os postitizinhos,

**[00:33:11 - 00:33:14]** ali os quadradinhos, o miro atendem muito bem.

**[00:33:17 - 00:33:18]** E eu que eu falei, né,

**[00:33:18 - 00:33:25]** tudo dado com a intenção, olha aqui, que vocês aprenderem ali na durante o curso.

**[00:33:25 - 00:33:26]** Então, domingo de storytelling,

**[00:33:26 - 00:33:29]** eu vingue de storme completos ali dos fluxos,

**[00:33:30 - 00:33:32]** de gestão e acompanhamento de OS,

**[00:33:32 - 00:33:33]** e gestão de peças em suos.

**[00:33:33 - 00:33:36]** Então, toda parte ali, de gestão de mistrativa não precisa.

**[00:33:37 - 00:33:40]** Eu de fato vim de sair a minha sopa, a criação é acompanhendo de OS

**[00:33:40 - 00:33:42]** e a gestão de peças em suos.

**[00:33:43 - 00:33:47]** Os diagramas no formo foi apresentado nas alas,

**[00:33:47 - 00:33:50]** e a linguagem língua língua língua língua língua língua aplicada.

**[00:33:50 - 00:33:54]** Então, o adicionário da linguagem também.

**[00:33:55 - 00:33:57]** Tipo, nele, pode ser tudo numiro também, ali, tá.

**[00:33:57 - 00:34:01]** Queria um padradinho aridorado, funcionário,

**[00:34:01 - 00:34:04]** ou pode também entrar em ele na própria documentação

**[00:34:04 - 00:34:06]** o Ídimento do Projeto, enfim.

**[00:34:06 - 00:34:10]** Código Fonte no repostoório privado,

**[00:34:10 - 00:34:13]** incluindo as APIs conforme os requisitos,

**[00:34:13 - 00:34:16]** do Acervário e do Acervo com poucos configurados.

**[00:34:16 - 00:34:19]** Ídimento completa com extracções de uso,

**[00:34:19 - 00:34:23]** tá, de como a gente subia,

**[00:34:23 - 00:34:27]** eu falei, explicando um pouco sobre o projeto de OS e,

**[00:34:27 - 00:34:32]** como que a gente hoda aí, daqui a bem, de local.

**[00:34:32 - 00:34:35]** Segurança, relatório com análise de vulnerabilidades, tá.

**[00:34:35 - 00:34:39]** Articionar no relatório, no relatório, análise de escândia.

**[00:34:39 - 00:34:41]** E eles adunam o código aqui.

**[00:34:41 - 00:34:43]** São dois relatórios.

**[00:34:43 - 00:34:47]** Um é o sonar, tá, que a gente consegue ali,

**[00:34:47 - 00:34:50]** em identificar...

**[00:34:50 - 00:34:51]** é...

**[00:34:51 - 00:34:56]** com a estrutura, poucos qualidade de código de fato,

**[00:34:56 - 00:34:59]** do código ali, em momento de build,

**[00:34:59 - 00:35:01]** ali, de pipeline.

**[00:35:01 - 00:35:03]** E afirmamente aqui,

**[00:35:03 - 00:35:06]** se eu não me engano, eles mostram na aula,

**[00:35:06 - 00:35:11]** o asp, acho que é a Zap, o asp,

**[00:35:11 - 00:35:12]** são no mingueno, tá.

**[00:35:12 - 00:35:15]** Que a artefata, uma afirmamente, que vai gerar um relatório,

**[00:35:15 - 00:35:18]** de vulnerabilidade, em tempo de execução, tá.

**[00:35:18 - 00:35:21]** Então, de execução, faz alguns testes ali, de vulnerabilidade,

**[00:35:21 - 00:35:23]** ele vai gerar um relatório.

**[00:35:23 - 00:35:25]** E aí, também, pode anexacer esse relatório,

**[00:35:25 - 00:35:27]** tirar o printinho, enfim.

**[00:35:28 - 00:35:31]** O sonar, eu sei que no modelo aqui,

**[00:35:31 - 00:35:32]** gratuito, ele não, não, não,

**[00:35:32 - 00:35:35]** a gente não consegue também exportar um relatório.

**[00:35:35 - 00:35:36]** Então, pode tirar um printinho,

**[00:35:36 - 00:35:38]** daquela principal mostrando ali,

**[00:35:38 - 00:35:41]** a cobertura, o que foi encontrado de vulnerabilidade,

**[00:35:41 - 00:35:43]** o codez-me, oito do mais que ele mostra ali,

**[00:35:43 - 00:35:45]** e pode acordar ali também, do meu domingo,

**[00:35:45 - 00:35:47]** de entrega.

**[00:35:47 - 00:35:49]** E eu, e a análise de vulnerabilidade,

**[00:35:49 - 00:35:53]** também pode anexar e voltar.

**[00:35:53 - 00:35:55]** E o que que de fato a gente precisa entregar?

**[00:35:55 - 00:35:57]** O que que vocês vão subir,

**[00:35:57 - 00:35:59]** fazer um pilô de no... na plataforma?

**[00:36:00 - 00:36:03]** um PDF. Nada mais com PDF, gente.

**[00:36:03 - 00:36:10]** A gente que coloca um monte de coisa lá, mas foca num PDF, que cria um arquivo e desse arquivo.

**[00:36:10 - 00:36:15]** Novo do grupo, participantes de os hernames no Discord.

**[00:36:15 - 00:36:22]** Isso é muito importante, o nome de vocês para Rm e os hernames no Discord.

**[00:36:22 - 00:36:28]** Vamos falar em muitas vezes, a gente não aprova o apoio de vocês a tempo.

**[00:36:28 - 00:36:33]** Muitas vezes vocês sacamos subindo, pois errada,

**[00:36:33 - 00:36:37]** vocês não percebem, a gente percebe bem na hora de corrigir.

**[00:36:37 - 00:36:42]** E às vezes falta pouco tempo para a gente entregar a nota para vocês.

**[00:36:42 - 00:36:45]** Isso é uma sedita de mais gente contra vocês, tá?

**[00:36:45 - 00:36:49]** Porque pelo nome é difícil encontrar, né?

**[00:36:49 - 00:36:54]** Tem alguns apelidos aqui, nêmios no Discord, um pouco diferente do nome.

**[00:36:54 - 00:37:00]** O Xiaomi nos animatadores de porcos estão sempre presentes,

**[00:37:00 - 00:37:03]** e aí fica é complicado a gente achar vocês.

**[00:37:03 - 00:37:06]** É...

**[00:37:06 - 00:37:12]** Límica da documentação no PDF, link do repositorio,

**[00:37:12 - 00:37:15]** relatório com análise de vulnerabilidade contra vocês, no sistema como eu falei.

**[00:37:15 - 00:37:18]** Tá? Essa entrega, então link do míro,

**[00:37:18 - 00:37:24]** ou no relacionado com o míro, link do rap do Github,

**[00:37:24 - 00:37:29]** relatório, ali, print do sonar, com o relatório da ferramenta,

**[00:37:29 - 00:37:32]** o astuzap, fica perfeito, tá pessoal.

**[00:37:32 - 00:37:40]** Agora, bora para as dúvidas que devem ser gostas.

**[00:37:40 - 00:37:45]** Eu já me ajuda aí no chat, por favor.

**[00:37:45 - 00:37:46]** E quem quiser...

**[00:37:46 - 00:37:51]** Mas eu vou deixar vocês responder essa.

**[00:37:51 - 00:37:57]** O Júrho perguntou se vai ter algum tipo de validação sobre o uso de R,

**[00:37:57 - 00:38:01]** para o desenvolvimento do projeto,

**[00:38:01 - 00:38:06]** você é a posição o deve em uma domanda de vi.

**[00:38:06 - 00:38:11]** É... não, vai ter, tá? Sim.

**[00:38:11 - 00:38:15]** Claro, e falando sem ser uma mente, vai vocês.

**[00:38:15 - 00:38:20]** Eu apoio o uso, em sentido o uso,

**[00:38:20 - 00:38:23]** acho que grande parte de vocês, acompanhe em cenário,

**[00:38:23 - 00:38:24]** viu como que tá...

**[00:38:24 - 00:38:27]** Não estão vendo como que tá mudando o cenário do desenvolvimento,

**[00:38:27 - 00:38:31]** só que eu realmente hoje quem não está usando a IAR,

**[00:38:31 - 00:38:32]** é para a Flico.

**[00:38:32 - 00:38:35]** Tá? Ficando para trás. Então, eu me senti a uso,

**[00:38:35 - 00:38:40]** mas como que é um curso, você não tem que para aprender.

**[00:38:40 - 00:38:43]** Ozinho é aquele método de questionar ela de entender

**[00:38:43 - 00:38:46]** porque ela está fazendo atividades coisas,

**[00:38:46 - 00:38:48]** não só de fato, por exemplo,

**[00:38:48 - 00:38:49]** porque ela vem entregar hoje,

**[00:38:49 - 00:38:51]** que não clau de vida, mas então,

**[00:38:51 - 00:38:53]** se você jogar a espedda e afelar,

**[00:38:53 - 00:38:55]** ela vai te entregar esse pronto.

**[00:38:55 - 00:38:58]** E muito bem feito,

**[00:38:58 - 00:39:00]** a nível de código, tá? Então...

**[00:39:00 - 00:39:06]** Mas aqui é um tempo de vocês, de aprendizado, é importante a gente conhecer ainda,

**[00:39:06 - 00:39:10]** a que é de software e tudo que ela traz junto com ela.

**[00:39:10 - 00:39:15]** Então, ozinho no modo crítico, tá?

**[00:39:15 - 00:39:18]** Então, desenvolva-me perguntar por que está acontecendo, questionem-se,

**[00:39:18 - 00:39:20]** ela e aprende junto com ela, tá?

**[00:39:20 - 00:39:25]** Então, o da uma exemplo de uso aqui já respondendo algumas das perguntas que foram feitas lá atrás.

**[00:39:25 - 00:39:30]** E a pessoa também preocupado com um cara, como é que você não conhei?

**[00:39:30 - 00:39:34]** Se você não tem o especialista de domingo, como é que eu vou fazer a parte de domingo?

**[00:39:34 - 00:39:40]** Cara, vocês podem pedir para o prochat de PT, se eu, se eu especialista de domingo,

**[00:39:40 - 00:39:45]** se pode falar cara, agora você é um domingo de uma mecânica x.

**[00:39:45 - 00:39:49]** Se você vai se comportar como domingo da mecânica, eu vou te entrevistar

**[00:39:49 - 00:39:54]** para me fazer a minha parte de domingo aqui, minha parte de TDD.

**[00:39:54 - 00:40:00]** E ele vai se comportar como um, claro que não é real, é simulado.

**[00:40:00 - 00:40:07]** Mas ele vai trazer algum contexto que tem na sociedade que você pode usar na sua aplicação.

**[00:40:07 - 00:40:15]** Eu também sou um cara que apoia muito aí, porque hoje estou num projeto grande de um banco,

**[00:40:15 - 00:40:20]** que usa aí para fazer exatamente quem está fazendo aqui.

**[00:40:20 - 00:40:26]** Então, não faz sentido você não saberia usar e a desprejuda vocês.

**[00:40:26 - 00:40:32]** Agora estou a me cuidado para não entrar em toda a inteligência para irá.

**[00:40:32 - 00:40:40]** Ela é o parceiro de vocês que vai ajudar, não resolver que vocês precisam aprender.

**[00:40:40 - 00:40:46]** Mas vai ajudar vocês, que deveriam ir mais rápido, criar em coisas que precisam ser muito inferidas,

**[00:40:46 - 00:40:57]** criar em transestuações fora, mas não pegue em ela para fazer gerar todos os diagramas de vocês.

**[00:40:57 - 00:41:00]** Não pegue em ela, para gerar todo a documentação.

**[00:41:00 - 00:41:09]** Se vocês estão perdendo seu tempo, eu vai acabar ficando meio com o tempo perdido e vocês.

**[00:41:09 - 00:41:12]** Agora, por exemplo, postete os automatizados.

**[00:41:12 - 00:41:15]** Isso aqui é algo que...

**[00:41:15 - 00:41:18]** Eu não preciso gastar o tempo de vocês, é um mente.

**[00:41:18 - 00:41:20]** E vai gerar isso.

**[00:41:20 - 00:41:24]** Claro, com o desenvolvimento bem feito, mas ela vai gerar isso que te falar é perfeito.

**[00:41:24 - 00:41:28]** É um cenário que não vale gastar tempo, mas hoje em dia.

**[00:41:28 - 00:41:35]** A gente tem que ter pessoas no curso, pelo que a primeira apresentação que podem inclusive não ser desenvolvedores,

**[00:41:35 - 00:41:37]** da área de produto.

**[00:41:37 - 00:41:42]** Então, para essas pessoas, por exemplo, a primeira vez vocês vão tocando um doaker file, provavelmente,

**[00:41:42 - 00:41:45]** não toca com o pose, quem é desse público.

**[00:41:45 - 00:41:49]** Claro, não recomendo usar e a a primeira vez, a pesa quem não está numa terra.

**[00:41:49 - 00:41:51]** A gente vai ter lá na frente.

**[00:41:51 - 00:41:58]** Então, pode usar, mas quando entrar em coisas que vocês precisam aprender,

**[00:42:00 - 00:42:05]** deixei a fazer. Fala-se você, conhecimento pra você. Agora é coisa que

**[00:42:05 - 00:42:10]** puta fora do todo o minho, eu to escou e você não vai usar.

**[00:42:10 - 00:42:12]** Por que você permite ter um tempo realmente?

**[00:42:12 - 00:42:20]** Até falando do doc, é uma disciplina que estava na primeira fase. Ela saiu

**[00:42:20 - 00:42:31]** porém, continuou aqui a disciplina no desafio. Porém, não sei qual se não,

**[00:42:31 - 00:42:37]** então, com a audiência, mas vai ter uma live só pra falar de docker.

**[00:42:37 - 00:42:41]** Já tem panega do uma live aqui de docker, onde vai cair as melhores práticas aqui,

**[00:42:41 - 00:42:44]** ele vou professor ver a apresentação de uma boa.

**[00:42:44 - 00:42:47]** É do doluço. É do doluço.

**[00:42:47 - 00:42:52]** Eu não quero ajudar vocês aqui, tá?

**[00:42:52 - 00:42:57]** Bom, essa live vai ser antes depois da entrega, a primeira fase.

**[00:42:57 - 00:42:59]** Antes antes, vai bem antes.

**[00:42:59 - 00:43:01]** É dia 2 de abril conferir.

**[00:43:01 - 00:43:03]** 2 de abril abola, então.

**[00:43:03 - 00:43:04]** No meu Deus.

**[00:43:04 - 00:43:07]** De dias.

**[00:43:07 - 00:43:08]** Lá-te.

**[00:43:08 - 00:43:12]** É só relatório de vulnerabilidad, não fazemos os fixos, não está fazia também.

**[00:43:12 - 00:43:25]** Sim, recomendo fazer o fix, mas se quiser deixar o relatório lá e fazer um breve

**[00:43:25 - 00:43:32]** discritivo ali do... das possíveis soluções ali pra que ele cenários, show de bola também.

**[00:43:32 - 00:43:38]** Tá, mas pela... pelo tipo de entrega que a gente está pedindo,

**[00:43:38 - 00:43:43]** pelo conteúdo aqui, não acaba gerando tanto tipo de vulnerabilidade,

**[00:43:43 - 00:43:47]** tipo, não tinha muita comunicação, tripe a ligação para o meu lado estérelo,

**[00:43:47 - 00:43:48]** enfim.

**[00:43:48 - 00:43:54]** Acredito que seja um pouco os ítimes poucos apontamentos de vulnerabilidade.

**[00:43:54 - 00:43:57]** É, pelo maior outra pergunta aqui, que é do dia,

**[00:43:57 - 00:44:00]** ele perna, eu estou seria precisar da siga,

**[00:44:00 - 00:44:05]** ou dos scripts de banco, para a gente poder testar que o dia foi fazer

**[00:44:05 - 00:44:08]** provavelmente uma sociedade, este bico.

**[00:44:08 - 00:44:10]** Cara, ajuda muito.

**[00:44:11 - 00:44:14]** Quando eu ir assim rodou comprozo, ele já só abitud, tá?

**[00:44:14 - 00:44:19]** Se migrei, chancide, tudo aqui, precisa banco, migrei, chancide, tudo, tá?

**[00:44:19 - 00:44:23]** Facilita e nossa vida, a gente vai estar dando um nota para o mesmo limite.

**[00:44:23 - 00:44:32]** Já vou ter que aprender assim, blaipo, que eu estava vendo, pessoal falando, então...

**[00:44:33 - 00:44:40]** Palape.

**[00:44:45 - 00:44:57]** O dia agramas aqui pode confirmar a entrega de DVD, domença de terni.

**[00:45:00 - 00:45:04]** do Ministro de Télix e de agrima-se e vem de storme. Tudo o que está gastando a

**[00:45:04 - 00:45:09]** 20 storme. Ainda desde o início ali do diagrama reventos, frente no Deus

**[00:45:09 - 00:45:16]** e reventos, vai adicionando os componentes, ali atores, até chegando o modelo de

**[00:45:16 - 00:45:22]** definho contexto delimitado, até chegando o modelo de agregado. Então, um

**[00:45:22 - 00:45:33]** primeiro, vamos evoluir? É, vamos ter o skyo. É, não, é o volume.

**[00:45:33 - 00:45:41]** Acho que ele é dizer para vocês irém evoluindo o desenho no

**[00:45:41 - 00:45:44]** mirror. Avolna a poza, né?

**[00:45:44 - 00:45:50]** Não é. O último aqui para ver o que ele, onde que ele pregou essa?

**[00:45:50 - 00:45:55]** Conei o graçado para o uso dele.

**[00:45:55 - 00:45:57]** Mas aí pode fazer todos os discussos, né?

**[00:45:57 - 00:46:02]** Por favor, né? Eu for o que isso de criação de serviço, acompanhamento da

**[00:46:02 - 00:46:09]** que é o caso da Pina de Macta, a criação se foi aprovado e aí vai seguir no

**[00:46:09 - 00:46:12]** número. Sim.

**[00:46:12 - 00:46:17]** Uma teus cariu, que eu vou acreditar voltando, mas sim, queria que a tudo aquilo que

**[00:46:17 - 00:46:25]** tiver no... como eu recisito ali tá num deixar... nada de fora é importante que o

**[00:46:25 - 00:46:32]** que tá descritando, documenta esteja na entrega de vocês, tá? É a primeira...

**[00:46:32 - 00:46:37]** É assim, vocês não têm um pior, né, que foi eu.

**[00:46:37 - 00:46:43]** Uma das perguntas aí, hein, mas... eu documento ali, já é uma descrição bacana,

**[00:46:43 - 00:46:50]** vamos nos escomensar, vamos nos em ele, ele tem que ser o... meu que eu... a história dos

**[00:46:50 - 00:46:53]** que se pregava, eles definem chofe, o rei.

**[00:46:53 - 00:46:56]** Foi fora.

**[00:46:56 - 00:47:04]** É, professor, sobre a questão e da auto-tonticação. Eu imagino assim que como não tá sendo pedido

**[00:47:04 - 00:47:11]** cada dia os usuários e tudo mais, né? A gente vai usar apenas para proteger aliás rotas,

**[00:47:11 - 00:47:17]** mas a gente vai usar um usuário interno, em mesmo.

**[00:47:17 - 00:47:25]** É, se vocês podem imocar o... a parte do usuário, tá, vocês não necessariamente precisam

**[00:47:25 - 00:47:32]** implementar tudo, tá? Você é acredito que você é imocado aí alguma coisa,

**[00:47:32 - 00:47:38]** outra. É, seria interessante você descrever tá na arquitetura qual... como você tá

**[00:47:38 - 00:47:44]** fazendo... como você planejaria fazer a auto-inticação, como vai ser um micro-servista que

**[00:47:44 - 00:47:48]** se vai desenvolver, vai ser um... um teclo, clavido, a receão... entendeu?

**[00:47:48 - 00:47:54]** A gente pode te conecte portanto e cheio, já aí, mesmo que a gente não coloca na nossa...

**[00:47:54 - 00:47:59]** no nosso desenvolvimento, já tudo, o que a gente marca e como é esse cara tá bom cajoso.

**[00:48:00 - 00:48:03]** Esse carão de um tal é interessante que se fosse uma vez em esteja.

**[00:48:03 - 00:48:05]** Acho que uma vez voltou aí.

**[00:48:05 - 00:48:09]** Até eu sou um pouco.

**[00:48:09 - 00:48:11]** Agora ele vai evoluir.

**[00:48:11 - 00:48:12]** É.

**[00:48:12 - 00:48:14]** Você pegou o meu evoluir como assim, não?

**[00:48:14 - 00:48:15]** Foi um irassado.

**[00:48:15 - 00:48:17]** Ah, só vou evoluir.

**[00:48:17 - 00:48:19]** Cabou a luz, carincaz, tá?

**[00:48:19 - 00:48:21]** Mas já tentando troçar o armazoto.

**[00:48:21 - 00:48:22]** Não.

**[00:48:22 - 00:48:23]** É.

**[00:48:23 - 00:48:26]** E evoluindo o event storming, né?

**[00:48:26 - 00:48:29]** Então eu achei a falar aqui aos fluxos, né?

**[00:48:29 - 00:48:32]** O exude evento e aí a gente vai incorporando ele, né?

**[00:48:32 - 00:48:39]** Colocando as políticas, os atores, definidos atores, que abrem com textos delimitados, até

**[00:48:39 - 00:48:41]** chegar no modelo de agregado.

**[00:48:41 - 00:48:42]** Então, é.

**[00:48:42 - 00:48:45]** Vou um demonstrando num míro, ali, por exemplo.

**[00:48:45 - 00:48:47]** Aí o poloção dele tá.

**[00:48:47 - 00:48:50]** Não, vou evoluindo o próprio o mesmo de agremo.

**[00:48:50 - 00:48:54]** Demonstra-me todos os dias agramas, caso a paz conforme que vocês podem desenvolver

**[00:48:54 - 00:48:55]** do eure.

**[00:48:55 - 00:49:00]** Então isso é, isso é entrega, mais o domenso de Aristótese, mais adicionário da linguagem

**[00:49:00 - 00:49:01]** o Bipo aqui, tá?

**[00:49:01 - 00:49:12]** Então, é basicamente isso que a gente vai avaliar do fluxo de... de dedeta, da documentação.

**[00:49:12 - 00:49:16]** Não precisa de tregar lá todos os requisitos funcionais da funcionar.

**[00:49:16 - 00:49:18]** É.

**[00:49:18 - 00:49:20]** Se quatro model, é.

**[00:49:20 - 00:49:26]** Esse que te coisa, tá?

**[00:49:26 - 00:49:30]** E aí, perguntaram aqui também, do comentação do D.D.P.C.C.

**[00:49:30 - 00:49:32]** da que a do fluxo de criação de O.S. e eu te posto.

**[00:49:32 - 00:49:42]** Criação acompanhamento de O.S. e controle lá gestão de Stork, partido de Stork.

**[00:49:42 - 00:49:44]** Aqui gestão de peças insúmos, né?

**[00:49:44 - 00:49:48]** Que é o Stork.

**[00:49:48 - 00:49:52]** Bom, lá.

**[00:49:52 - 00:49:59]** Eu uma dúvida aqui que eu achei interessante é que é do... sobre o Dockerfile, né?

**[00:49:59 - 00:50:03]** Eu gostava... eu gostava o colher.

**[00:50:03 - 00:50:05]** É o anero, a river, né?

**[00:50:05 - 00:50:09]** Não sei se está certo, se faltou uma lita.

**[00:50:09 - 00:50:12]** Mas...

**[00:50:12 - 00:50:16]** Ele perguntou se... se... por que que espisão do implementar o Dockerfile?

**[00:50:16 - 00:50:19]** Se você não tem que fazer o Pipe Line no GitHub,

**[00:50:19 - 00:50:21]** de WebEx, os por exemplo,

**[00:50:21 - 00:50:25]** a gente que para primeira fase a intenção é que a gente possa executar, né?

**[00:50:25 - 00:50:28]** Ou uma teus... não são a máquina porém,

**[00:50:28 - 00:50:32]** para... para... para as outras fases até a GitHub,

**[00:50:32 - 00:50:34]** tem que ter a bequeção, mas até o Pipe Line,

**[00:50:34 - 00:50:37]** a gente vai ter como parte na entrega, né?

**[00:50:37 - 00:50:39]** É isso aí, não sei.

**[00:50:39 - 00:50:41]** Nessa era a setapa, do Aquifer Faiu.

**[00:50:41 - 00:50:45]** A verdade do Dockerfile, você vai acabar utilizando...

**[00:50:45 - 00:50:48]** em todas as etapas, é... mesmo...

**[00:50:48 - 00:50:49]** um Pipe Line, tá?

**[00:50:49 - 00:50:52]** Vai subir lá no contém, eu vou dar normalável S,

**[00:50:52 - 00:50:54]** vou precisar dele para subir.

**[00:50:54 - 00:50:56]** É, amplicação.

**[00:50:56 - 00:51:00]** Então, o composo não, aí, eu composo e beleza para a gente subir.

**[00:51:00 - 00:51:02]** que não é uma semana que não é tudo o ambiente completo.

**[00:51:05 - 00:51:07]** Então é isso, essa faz gente não tem papelaine,

**[00:51:07 - 00:51:09]** não sei o me gravar a próxima, já entra.

**[00:51:09 - 00:51:11]** Eu e de serem me ganhando com o que tinha que ir no mesmo,

**[00:51:11 - 00:51:14]** eu não falei um deploy lá na AWS,

**[00:51:15 - 00:51:16]** mas não é essa etapa, não.

**[00:51:16 - 00:51:20]** Só o Docker File e o Compose,

**[00:51:20 - 00:51:23]** que é necessário para a gente subir de forma mais simples, ambiente.

**[00:51:23 - 00:51:25]** Então, conseguiria subir sem o Compose,

**[00:51:25 - 00:51:26]** mas a gente decide que ficar subindo,

**[00:51:26 - 00:51:27]** todos os compôrência parados, tá?

**[00:51:27 - 00:51:28]** O POSFASILITO.

**[00:51:32 - 00:51:32]** É legal.

**[00:51:32 - 00:51:34]** Tem uma do Gabriel,

**[00:51:35 - 00:51:39]** que a gente acha melhor?

**[00:51:39 - 00:51:41]** Eu vou deixar essa pra você,

**[00:51:41 - 00:51:44]** porque você já está eu sou novo aqui na fia,

**[00:51:44 - 00:51:44]** né?

**[00:51:44 - 00:51:50]** A gente executa eles precisam executar o TEC-CHAN de Amigil,

**[00:51:50 - 00:51:53]** que eles vão evoluindo semana semana,

**[00:51:53 - 00:51:56]** eles precisam assistir todas as alas,

**[00:51:56 - 00:51:58]** primeiro, porque depois de a partir do TEC-CHAN,

**[00:51:58 - 00:52:03]** a gente vai ver o que seria melhor pra exerir o projeto

**[00:52:03 - 00:52:06]** durante a primeira fase pra eles.

**[00:52:06 - 00:52:09]** Bom, assim,

**[00:52:09 - 00:52:11]** o tema baladiprata que tem,

**[00:52:11 - 00:52:18]** acho que o certo é errado, mas eu iria num sentido assim,

**[00:52:18 - 00:52:20]** vi toda parte de domínio,

**[00:52:20 - 00:52:22]** todos os alos de domínio,

**[00:52:22 - 00:52:24]** como é isso já, pensado do domínio,

**[00:52:24 - 00:52:26]** é estruturar algo de até mais fresco,

**[00:52:26 - 00:52:27]** mas com essa,

**[00:52:27 - 00:52:29]** é mais fácil de gente aplicar,

**[00:52:29 - 00:52:29]** colocar na prática,

**[00:52:29 - 00:52:32]** algo que está em um de a frente,

**[00:52:32 - 00:52:34]** domínio pronto,

**[00:52:34 - 00:52:37]** feito, se te as alas ali,

**[00:52:37 - 00:52:37]** ou recentemente,

**[00:52:37 - 00:52:39]** elas não são mais técnicas,

**[00:52:39 - 00:52:40]** e aí já aplica,

**[00:52:40 - 00:52:42]** com o começo de fato,

**[00:52:42 - 00:52:46]** o desenvolvimento, enfim,

**[00:52:46 - 00:52:47]** e eu falei dessa forma,

**[00:52:47 - 00:52:51]** mas é acredito que tem pessoas que assistem todas as alas,

**[00:52:51 - 00:52:53]** depois que começam a fazer o desafio,

**[00:52:53 - 00:52:56]** tem gente que deve pular várias nesses de alas,

**[00:52:56 - 00:52:58]** e eles me relacionam a ser entrega o desafio,

**[00:52:58 - 00:52:59]** então assim,

**[00:52:59 - 00:53:01]** vai na forma que vocês se preferem,

**[00:53:01 - 00:53:03]** porque acredito que muitos de vocês também,

**[00:53:03 - 00:53:05]** já estão na área muito tempo,

**[00:53:05 - 00:53:06]** e tem conhecimento aqui,

**[00:53:06 - 00:53:08]** e faz parte de nosso dia a dia,

**[00:53:08 - 00:53:10]** que nem já está muito dos anos na área,

**[00:53:10 - 00:53:12]** então ela ali é mais para lembrar algo conceita,

**[00:53:12 - 00:53:13]** alguma coisa assim,

**[00:53:13 - 00:53:15]** então,

**[00:53:15 - 00:53:17]** bom desenvolvendo o projeto ao longo das alas,

**[00:53:17 - 00:53:18]** eu acho melhor,

**[00:53:18 - 00:53:20]** porque se deixar tudo por final,

**[00:53:20 - 00:53:22]** elas vão acabar surgindo do vida no final,

**[00:53:22 - 00:53:28]** é ficar mais um pouco tempo para desenvolver,

**[00:53:28 - 00:53:29]** e como o RGF falou,

**[00:53:29 - 00:53:31]** eu to aqui,

**[00:53:31 - 00:53:33]** ele está aqui na estabeleidade de vocês,

**[00:53:33 - 00:53:36]** que todos os dias aqui de segunda a sexta,

**[00:53:36 - 00:53:37]** para atirar dúvidas,

**[00:53:37 - 00:53:39]** então aproveita em si tem,

**[00:53:39 - 00:53:40]** para ir desenvolver o projeto,

**[00:53:40 - 00:53:41]** tirando dúvidas,

**[00:53:41 - 00:53:44]** a gente é um longo do curso.

**[00:53:44 - 00:53:47]** É que a gente fez poder fazer grupos de estudos,

**[00:53:47 - 00:53:49]** depois relacionados ao projeto,

**[00:53:49 - 00:53:52]** bom,

**[00:53:52 - 00:53:54]** o que quer?

**[00:53:54 - 00:53:56]** É, pode fazer aqueles grupos de estudos,

**[00:53:56 - 00:53:58]** que assala as pessoas,

**[00:53:58 - 00:54:00]** e pode começar com a isso no projeto, não?

**[00:54:00 - 00:54:06]** Pode, então, como falei, a gente tem os grupos de estudos marcados,

**[00:54:06 - 00:54:11]** que são cada 15 dias, cada 15, é live,

**[00:54:11 - 00:54:14]** é uma semana live, uma semana no futuro.

**[00:54:14 - 00:54:17]** Só que como essa tudo tem muita gente,

**[00:54:17 - 00:54:21]** em um dia de grupo de estudos,

**[00:54:21 - 00:54:24]** por ser difícil, a gente, o mosquito de lado do mundo,

**[00:54:24 - 00:54:27]** a gente está aprofundando muito no tema e fica

**[00:54:27 - 00:54:31]** durante o tempo inteiro do grupo.

**[00:54:31 - 00:54:34]** Então, podemos marcar e fazer,

**[00:54:34 - 00:54:37]** ali, cominar outros grupos, outros grupos,

**[00:54:37 - 00:54:40]** para te adúvidas.

**[00:54:40 - 00:54:44]** Mas recomendo fortemente, a utilização do Discord,

**[00:54:44 - 00:54:46]** nesse modelo assim que,

**[00:54:46 - 00:54:50]** a gente consegue trabalhar bem, responder com mais velocidade,

**[00:54:50 - 00:54:52]** as dúvidas, que ficar esperando o encontro,

**[00:54:52 - 00:54:55]** possível, enquanto, para a gente poder saber as dúvidas.

**[00:54:56 - 00:54:59]** Acho que o grupo é legal para a gente te debatei sobre o meu entê.

**[00:54:59 - 00:55:01]** Estas é o conteúdo diferente,

**[00:55:01 - 00:55:03]** às vezes o conteúdo aplicável,

**[00:55:03 - 00:55:07]** de fato, uma empresa mesmo, de dia a dia,

**[00:55:07 - 00:55:10]** e dúvidas simples de projetos, de entrega,

**[00:55:10 - 00:55:13]** joga em lá no Discord, que a gente responde,

**[00:55:13 - 00:55:19]** na mesma forma.

**[00:55:19 - 00:55:21]** Por uma mais dúvida,

**[00:55:21 - 00:55:28]** eu vou ver, tem de uso do Docker,

**[00:55:28 - 00:55:31]** que está de papel line, não, para ser te papel line,

**[00:55:32 - 00:55:34]** a documentação falei,

**[00:55:34 - 00:55:38]** temos que publicar a peia,

**[00:55:38 - 00:55:40]** bom domínio produtivo, não,

**[00:55:40 - 00:55:42]** só doque, local mesmo.

**[00:55:42 - 00:55:45]** Data de entrega está lá no portal,

**[00:55:45 - 00:55:47]** já do ladinho lá,

**[00:55:47 - 00:55:53]** onde vocês cliquem para abrir o desafio,

**[00:55:53 - 00:55:55]** tem lá data de atrega.

**[00:55:55 - 00:56:12]** Grupo de troma no WhatsApp, não tem, mas,

**[00:56:12 - 00:56:17]** se você se animarem, podemos criar.

**[00:56:17 - 00:56:20]** Tem uma pergunta aqui, do G.

**[00:56:20 - 00:56:24]** A dúvida sobre o dedeno código do projeto.

**[00:56:24 - 00:56:25]** Então, o que eu entende,

**[00:56:25 - 00:56:27]** é que o momento mais naquela parte

**[00:56:27 - 00:56:31]** da estrutura de camadas, né?

**[00:56:32 - 00:56:34]** Se a gente perderia no automão,

**[00:56:34 - 00:56:36]** na hora de,

**[00:56:36 - 00:56:41]** de dependendo da estrutura que ele colocá.

**[00:56:41 - 00:56:44]** Não, não, não, não, não,

**[00:56:44 - 00:56:48]** aqui, assim, como a gente não está aplicando,

**[00:56:48 - 00:56:49]** nenhum tipo de,

**[00:56:49 - 00:56:52]** a que te tura um pouco mais favorável,

**[00:56:52 - 00:56:54]** para a gente aplicar de fato,

**[00:56:54 - 00:56:56]** o DDD, como eu não queria,

**[00:56:56 - 00:56:58]** não é a gonnaão da vida,

**[00:56:58 - 00:56:58]** que ficou um pouco mais,

**[00:56:58 - 00:57:00]** explicito, mas claro.

**[00:57:00 - 00:57:02]** a separação, e tudo mais.

**[00:57:05 - 00:57:08]** A gente não vai cobrar nesse nível de detalhe aqui.

**[00:57:08 - 00:57:12]** O que a gente espera, o mínimo, aqui vocês aplicam,

**[00:57:12 - 00:57:15]** usando a linguagem do que foi apresentado

**[00:57:15 - 00:57:19]** de domínito, mais, no DVD,

**[00:57:19 - 00:57:21]** se levantarem no funcionário,

**[00:57:21 - 00:57:23]** aplicar a mesma linguagem no código.

**[00:57:23 - 00:57:26]** Então, esse é o mínimo que a gente espera aqui,

**[00:57:26 - 00:57:32]** nessa fase, agora, o próximo fase é um pouco diferente.

**[00:57:32 - 00:57:35]** A estrutura que é totalmente aberto mesmo,

**[00:57:35 - 00:57:39]** não tem cobrança em lação da estrutura,

**[00:57:39 - 00:57:42]** de visão de projeto, tudo mais.

**[00:57:42 - 00:57:45]** A última dúvida ainda sobre as validações,

**[00:57:45 - 00:57:46]** sobre a questão de CPF, se ele pejou,

**[00:57:46 - 00:57:49]** tem de que a validação, que vocês falaram,

**[00:57:49 - 00:57:51]** tem de a validação com vocês falaram,

**[00:57:51 - 00:57:54]** mas eu vou estaria de confirmar uma dúvida.

**[00:57:54 - 00:57:57]** No grupo, é necessário alguma validação

**[00:57:57 - 00:58:07]** de relacionado a peir e noito aí.

**[00:58:07 - 00:58:10]** A dúvida do rameito.

**[00:58:10 - 00:58:13]** A dúvida do grupo, necessário confirmar alguma validação

**[00:58:13 - 00:58:16]** nação da peir e,

**[00:58:16 - 00:58:21]** é que se abre o microfonei para o Judo, é um PII professor.

**[00:58:21 - 00:58:22]** PII.

**[00:58:22 - 00:58:24]** PII.

**[00:58:24 - 00:58:25]** A próxima pessoa,

**[00:58:25 - 00:58:31]** acho que tem esse tipo de especialização do...

**[00:58:31 - 00:58:33]** é de sempre da internet,

**[00:58:33 - 00:58:37]** de informações de isso.

**[00:58:37 - 00:58:43]** PII, não é a jucada.

**[00:58:43 - 00:58:49]** Ela foi entendido, não precisa fazer a clipe deografia,

**[00:58:49 - 00:58:53]** é só a validação para o ver se dá batendo por um correto,

**[00:58:53 - 00:58:58]** acho que é a validação de validação básica de ser nepeyota, né?

**[00:58:58 - 00:59:00]** De gente, vale a ficar dor, né?

**[00:59:00 - 00:59:04]** Eu acho que a gente, a gente é verdade,

**[00:59:04 - 00:59:07]** e ele está falando em informações pessoais,

**[00:59:07 - 00:59:11]** tem mais uma vez como a gente bem de ver.

**[00:59:11 - 00:59:15]** Está olhando aqui.

**[00:59:15 - 00:59:17]** É o pessoal de um dia ou bem, agora?

**[00:59:17 - 00:59:19]** Sim, agora ficou melhor.

**[00:59:19 - 00:59:20]** O, o exibô.

**[00:59:20 - 00:59:21]** É, o meu...

**[00:59:21 - 00:59:25]** Não, é, é, sobre isso mesmo, sobre...

**[00:59:25 - 00:59:27]** Informação pessoal, né?

**[00:59:27 - 00:59:29]** De gente passando pessoal e...

**[00:59:29 - 00:59:31]** A explodência de não ter nada a vindo, né?

**[00:59:31 - 00:59:33]** Não, não, não, não, não.

**[00:59:33 - 00:59:37]** Vocês ontem um conteúdo de LGBT, não, ultima fase.

**[00:59:37 - 00:59:39]** É, mas são no migo número de não valentes,

**[00:59:39 - 00:59:43]** nem se aplicar aqui no nosso desenvolvimento de todo o projeto.

**[00:59:43 - 00:59:46]** Na última fase, tem, eu lembro que tem, tem, tem.

**[00:59:46 - 00:59:48]** Mas a gente está verificando se daí.

**[00:59:48 - 00:59:53]** É, deixa eu brigar do pessoal.

**[00:59:53 - 00:59:55]** A notificação é exigida apenas para...

**[00:59:55 - 00:59:58]** A topra é voltando de a viaça, é para o Pantista também.

**[00:59:58 - 01:00:00]** A notificação só para a...

**[01:00:00 - 01:00:03]** administradores ou clientes também, final não teria a identicação.

**[01:00:03 - 01:00:07]** É, não, só para a administradora.

**[01:00:07 - 01:00:09]** Quer a gestão administrativa, né?

**[01:00:09 - 01:00:13]** Eu acho que não tem que ir fácil por que a gente aí, né?

**[01:00:13 - 01:00:14]** Não.

**[01:00:14 - 01:00:16]** É, pode ter uma identicação, tá do cliei.

**[01:00:16 - 01:00:22]** Esculpa, porque a identicação do cliei a gente ali é só a transformação de dados

**[01:00:22 - 01:00:24]** de CPF e mandatina na cimento, por exemplo.

**[01:00:24 - 01:00:31]** É, é, é só em alguma outra informação ou toca de ver que provavelmente é

**[01:00:31 - 01:00:32]** que eu tô grafia, né?

**[01:00:32 - 01:00:34]** Já, já, já, já é de IPD, não.

**[01:00:34 - 01:00:37]** É, tem que passar essa do cliei, que não é identificado pela gente, né?

**[01:00:37 - 01:00:38]** É.

**[01:00:38 - 01:00:40]** Mas isso é a identicação, não é a autenticação, é.

**[01:00:40 - 01:00:42]** Isso é a identicação para o pouco, né?

**[01:00:42 - 01:00:43]** Não, não.

**[01:00:43 - 01:00:49]** Uma propriedade do S, não é para a autenticação do cliei, que pode acessar o sistema.

**[01:00:49 - 01:00:55]** Isso é que é um RP, provavelmente, para uma oficina, certo?

**[01:00:55 - 01:00:57]** É, é um sistema para uma oficina.

**[01:00:57 - 01:01:01]** Não, quem tá usando, eu dou no dofocina.

**[01:01:01 - 01:01:05]** Muito ou notas.

**[01:01:05 - 01:01:13]** É, certo, isso é, gente, eu confirmo muita coisa com ele, porque é o primeiro

**[01:01:13 - 01:01:14]** tétate alimbeu.

**[01:01:14 - 01:01:21]** Olha, eu vou ser como, você que fala aqui de mostrar um PDF, cara, não tem padrão

**[01:01:21 - 01:01:27]** assim, é tipo de PDF, você já mostrar o PDF de uma anterior, você não tem um padrão

**[01:01:27 - 01:01:31]** de PDF, cara, realmente, cada uma faz um jeito.

**[01:01:31 - 01:01:37]** Mas desde que tem, a gente faz gritar, faz um desenho maluca aqui, um leite bonita,

**[01:01:37 - 01:01:39]** uma fosmoficina, né?

**[01:01:39 - 01:01:44]** Mas só precisa ter as informações que a gente está pedindo ali embaixo, sabe?

**[01:01:44 - 01:01:45]** Que são?

**[01:01:45 - 01:01:51]** No nome do grupo participante, link, os links, correto, os e o relatório.

**[01:01:51 - 01:01:52]** Sim, tem do isso, cara.

**[01:01:52 - 01:01:58]** Pode escrever de qualquer ele, pode ser escrita básica mesmo, assim, da nem ter

**[01:01:58 - 01:02:00]** cabeçal da pena no tipo.

**[01:02:00 - 01:02:04]** É importante, que legal pode ir do aia, também.

**[01:02:04 - 01:02:07]** Mateus, só para entender.

**[01:02:07 - 01:02:13]** É, a anendir já tá da blia T, e isso aí, a gente precisa fazer ter algum tipo de

**[01:02:13 - 01:02:17]** criptografia, mas específica, então, dessa questão de dar dos olhos.

**[01:02:17 - 01:02:21]** Não, não, não, não.

**[01:02:21 - 01:02:23]** Fechou.

**[01:02:23 - 01:02:28]** Aqui é só isso mesmo, tá?

**[01:02:28 - 01:02:34]** Eu não lembro agora, se ter alguma outra fase com, que é profundo pouco mais, em, expectantemente,

**[01:02:34 - 01:02:35]** segura, não sei, tá?

**[01:02:35 - 01:02:42]** É, depois da boa vez, eu sei que tem uma tarea de segura, sei mesmo.

**[01:02:42 - 01:02:49]** Não, aqui tem, na fase de fato, um desafio, ele só perde o relatório mesmo de valoridade.

**[01:02:49 - 01:02:50]** Sim.

**[01:02:50 - 01:02:54]** Bom, lá.

**[01:02:54 - 01:03:00]** Não, me preocupando porque eu, o ripo.

**[01:03:00 - 01:03:03]** precisar ser privado.

**[01:03:03 - 01:03:07]** Ah, galera assim, porque este é onde

**[01:03:07 - 01:03:09]** e a gente é de galera no copiar e que

**[01:03:09 - 01:03:12]** fica disponível ao seu projeto, onde tudo mundo

**[01:03:12 - 01:03:15]** está fazendo mesmo projeto.

**[01:03:15 - 01:03:18]** Mas polícia, tá, com a gente

**[01:03:18 - 01:03:21]** vai deixar privado, sobre o libero acesso

**[01:03:21 - 01:03:23]** que é para o usuário que a gente já

**[01:03:23 - 01:03:25]** tem acesso aqui de disponível e

**[01:03:25 - 01:03:27]** boa, tá. Mas para

**[01:03:27 - 01:03:28]** por causa disso, mesmo, para no

**[01:03:28 - 01:03:31]** compartilhar o seu projeto com outros grupos.

**[01:03:31 - 01:03:38]** É para se uma lá empresa real também, né?

**[01:03:38 - 01:03:40]** Prima.

**[01:03:40 - 01:03:42]** Repositores são todos privados.

**[01:03:42 - 01:03:44]** Vai.

**[01:03:44 - 01:03:48]** É, o apelógio do vídeo vai ser no

**[01:03:48 - 01:03:49]** YouTube, né?

**[01:03:49 - 01:03:50]** Pessoal da pergunta.

**[01:03:50 - 01:03:52]** Pode ser Google Drive, YouTube.

**[01:03:52 - 01:03:53]** Ok.

**[01:03:53 - 01:03:54]** O que você preferir, né, tá?

**[01:03:54 - 01:03:55]** Vamos dar o link.

**[01:03:55 - 01:03:58]** É uma coisa também que lembra agora

**[01:03:58 - 01:04:00]** você tostva, mas acesso assim,

**[01:04:00 - 01:04:02]** acesso ao vídeo, acesso ao Google Drive

**[01:04:02 - 01:04:05]** e acesso ao míro,

**[01:04:05 - 01:04:07]** também tem tem deixar

**[01:04:07 - 01:04:08]** liberado assim.

**[01:04:08 - 01:04:10]** A gente gasta muito, tem

**[01:04:10 - 01:04:11]** uma turma muito grande,

**[01:04:11 - 01:04:13]** vai ter muito projeto para

**[01:04:13 - 01:04:14]** coerir.

**[01:04:14 - 01:04:15]** A gente gasta muito tempo e

**[01:04:15 - 01:04:17]** da trás das pessoas a

**[01:04:17 - 01:04:19]** pedindo acesso, sabe?

**[01:04:19 - 01:04:21]** Então, as vezes não tem no Guiit,

**[01:04:21 - 01:04:22]** as vezes tem no Guiit, não tem no

**[01:04:22 - 01:04:25]** míro, então, se a tente assim

**[01:04:25 - 01:04:27]** a liberar todos os

**[01:04:27 - 01:04:28]** bíro, eu dá para deixar

**[01:04:28 - 01:04:30]** publico no final do curso.

**[01:04:30 - 01:04:33]** Ou eu passo no email depois,

**[01:04:33 - 01:04:34]** mas eu estou com essa frente

**[01:04:34 - 01:04:36]** ou eu me peio pessoal aqui,

**[01:04:36 - 01:04:38]** que eu uso para

**[01:04:38 - 01:04:39]** acessar o míro aqui também para

**[01:04:39 - 01:04:40]** vocês liberarem, deixar

**[01:04:40 - 01:04:41]** liberado, tá?

**[01:04:41 - 01:04:42]** Mas você tem que ter

**[01:04:42 - 01:04:43]** os acessos que uma coisa que

**[01:04:43 - 01:04:44]** que é a trabalho é bastante

**[01:04:44 - 01:04:45]** mesmo que a gente tem que

**[01:04:45 - 01:04:46]** ficar com o rindo bastante

**[01:04:46 - 01:04:47]** a trás para pedir

**[01:04:47 - 01:04:48]** os acessos.

**[01:04:48 - 01:04:51]** Se eu vou confirmar,

**[01:04:51 - 01:04:52]** também ideal do grupo,

**[01:04:52 - 01:04:53]** é cinco, né?

**[01:04:53 - 01:04:57]** Se eu não sei o que você

**[01:04:57 - 01:04:58]** é cinco pessoas, o máximo.

**[01:04:58 - 01:04:59]** Ah.

**[01:04:59 - 01:05:01]** E isso.

**[01:05:01 - 01:05:05]** Boa.

**[01:05:05 - 01:05:07]** Amulá, terem.

**[01:05:07 - 01:05:12]** É, tem duas pessoas

**[01:05:12 - 01:05:13]** que eu mesmo não dava aqui

**[01:05:13 - 01:05:14]** da RT.

**[01:05:15 - 01:05:16]** E na sua india uma do código

**[01:05:16 - 01:05:17]** ideal é que seja

**[01:05:17 - 01:05:18]** híbrido,

**[01:05:18 - 01:05:19]** contendo os negócios

**[01:05:19 - 01:05:20]** em português e

**[01:05:20 - 01:05:22]** técnicos em inglês,

**[01:05:22 - 01:05:23]** outro do importo

**[01:05:23 - 01:05:24]** inglês.

**[01:05:24 - 01:05:25]** Por exemplo, o que eu

**[01:05:25 - 01:05:26]** a ver aí,

**[01:05:26 - 01:05:27]** o serve,

**[01:05:27 - 01:05:28]** o Criente, o Cerve,

**[01:05:28 - 01:05:29]** o Cerve.

**[01:05:29 - 01:05:31]** Avar lá na discussão nesta, né?

**[01:05:31 - 01:05:33]** No grupo lá no Discord,

**[01:05:33 - 01:05:34]** tem um topo

**[01:05:34 - 01:05:35]** que eu large a conheço.

**[01:05:35 - 01:05:36]** É,

**[01:05:36 - 01:05:37]** pessoal mandou,

**[01:05:37 - 01:05:38]** lá uma,

**[01:05:38 - 01:05:41]** uns artigos interessantes,

**[01:05:42 - 01:05:44]** é onde tem várias abordagens.

**[01:05:44 - 01:05:45]** Tá assim.

**[01:05:45 - 01:05:48]** Tem a abordagem que diz

**[01:05:48 - 01:05:49]** onde o negócio

**[01:05:49 - 01:05:51]** é do Brasil,

**[01:05:51 - 01:05:52]** é,

**[01:05:52 - 01:05:54]** o Polar de negócio,

**[01:05:54 - 01:05:56]** é do Brasil,

**[01:05:56 - 01:05:57]** o Criente,

**[01:05:57 - 01:05:58]** o Brasil, vamos fazer

**[01:05:58 - 01:05:59]** tudo aqui em português.

**[01:06:00 - 01:06:10]** Porém, que em gosta de programada, de colocar o código inglês, que tem muita gente,

**[01:06:10 - 01:06:13]** nesse cenário, o híbrido cai bem.

**[01:06:13 - 01:06:18]** Então, pessoal está usando bastante o híbrido que é termos técnicos, não é como create,

**[01:06:18 - 01:06:25]** serve, se repositor e enfim, temos como uns as linguagens, inglês e aí o termo do domínio,

**[01:06:25 - 01:06:27]** que de fato importuei-se.

**[01:06:27 - 01:06:32]** Eu acho que eu faço estranho, mas...

**[01:06:32 - 01:06:37]** Veridade aqui no mercado, você pode encontrar empresas que não queram nada em inglês.

**[01:06:37 - 01:06:41]** Você pode encontrar empresas que querem inglês, mesmo sendo toda a brasileira.

**[01:06:41 - 01:06:46]** Você pode encontrar empresas que quer que seja em híbrido então.

**[01:06:47 - 01:06:52]** A gente exige alguma dessas, ou não, uma turista, não.

**[01:06:52 - 01:07:00]** Não, tem um pergunta aqui também sobre os cometos, a gente analisou a cometir.

**[01:07:00 - 01:07:10]** Não, padrante cometir não. Eu acho legal, você seguir em comensão, mas a gente não vai avaliar isso nessa fase.

**[01:07:10 - 01:07:17]** O que a gente vai avaliar é o redimit, então, eu documentação o completa do projeto ali, dentro do redimit.

**[01:07:17 - 01:07:22]** Não é onde eu entro a cada uma, quando eu tô dando a comitação do PDF de entrega, tá?

**[01:07:22 - 01:07:29]** O material, eu vou tirar uma dúvida.

**[01:07:29 - 01:07:33]** Para, o da parte do...

**[01:07:33 - 01:07:36]** Envilador Summit, o cliente para aprovação.

**[01:07:36 - 01:07:43]** Esse peda de forma porque o dessa parte não se faz isso no dinho vir de ninguém, ou no lado mesmo.

**[01:07:43 - 01:07:46]** Como ele tem aqui, é mocado mesmo, tá?

**[01:07:46 - 01:07:50]** Você pode fazer em vídeo meio de fato, mas um moque já serve.

**[01:07:50 - 01:08:02]** O moque se mudando um envio e a alteração do estado do S, para aprovado, enfim, o já entra em execução.

**[01:08:02 - 01:08:09]** Tem gente que coloca os Estados de aprovada, porque nem sempre quando ela aprovada, lá, entre a execução.

**[01:08:09 - 01:08:17]** Então, tem gente que coloca os Estados de aguardando o mecânico disponível, mas não tem mecânico disponível para executar.

**[01:08:17 - 01:08:21]** Então, ela ficando em estado de sada em intermediário, depois ela entra em execução.

**[01:08:21 - 01:08:37]** E fica a que a chefeira de você, está o mínimo a esse, mas essa questão do e-mail pode fazer mocado mesmo, tá?

**[01:08:37 - 01:08:38]** Cada...

**[01:08:38 - 01:08:51]** No outro form, já pode ficar a astral grupo, inclusive o grupo tem que ser cadastrado até 15 dias antes da nata de entrega.

**[01:08:51 - 01:08:55]** Se ele bateu os 15 dias a de padadinha entrega, não é possível, mas mexendo o grupo.

**[01:08:55 - 01:08:57]** Então, fica a que a adica.

**[01:08:57 - 01:09:00]** Se na próxima fase...

**[01:09:00 - 01:09:06]** Você estiver ali a vontade de mudar de grupo, enfim, pra um motivo, no seu grupo, no seu adaptor,

**[01:09:06 - 01:09:12]** o grupo, o sistema até 15 dias antes da nature de traga do desafio pra fazer essa mudança,

**[01:09:12 - 01:09:16]** no sistema sinão, vai finalizar a fase com aquele grupo.

**[01:09:16 - 01:09:19]** Então vai poder fazer outra ação no próximo, não vou as mãos faz.

**[01:09:19 - 01:09:26]** Gente, a minha ação, que vai subir uma hora, não é só o marketing aqui, viu?

**[01:09:26 - 01:09:29]** Eu vou até...

**[01:09:29 - 01:09:30]** Até os.

**[01:09:30 - 01:09:37]** As caras de colocar a linha de grupo do desafio, enfim, imagina, as caras de rei-gáquia.

**[01:09:37 - 01:09:39]** Matheus, vocês estão me ouvindo?

**[01:09:39 - 01:09:40]** Sim.

**[01:09:40 - 01:09:47]** Se tinha mencionado anteriormente uma ferramenta na inducionar pra fazer o escando código,

**[01:09:47 - 01:09:52]** é uma chamazap que, por acaso, é o oasp...

**[01:09:52 - 01:10:02]** Eu acho que é o oaspisap, é a esapia de proxino.

**[01:10:02 - 01:10:05]** Ah, acho, beleza, valeu.

**[01:10:05 - 01:10:16]** Essa é isso, é isso daí mesmo, você falou no equilíbrio do raio.

**[01:10:16 - 01:10:18]** Não vai te atacar o opsi.

**[01:10:18 - 01:10:22]** O que é uma planíria que você falou aqui é mostrar o pessoal o que te leva em consideração?

**[01:10:22 - 01:10:24]** Ah, boa, deixa eu pegar daqui.

**[01:10:24 - 01:10:28]** Porque aqui já tem gente perguntando se vai se avaliar da complexidade,

**[01:10:28 - 01:10:33]** da solução nas hoeses, acho que você lembrau para na questão.

**[01:10:33 - 01:10:41]** Quer saber se avaliado?

**[01:10:41 - 01:10:45]** É, ele percoço, se vai se avaliado com a complexidade.

**[01:10:45 - 01:10:53]** Ó, eu vou falar aqui, tá, mas fácil porque tá até com a valeração de dar outra forma, mas assim.

**[01:10:53 - 01:10:58]** A valeração que tem esquenta para a valeração, que contexto de dd20,

**[01:10:58 - 01:11:05]** storming, storytelling, então tem uma parte de a valeração ali, malota que a gente dá aqui.

**[01:11:05 - 01:11:07]** Dizero a 10 para cada um desses itens, tá?

**[01:11:07 - 01:11:11]** Então eu vou falar os itens, é no final a gente faz uma média desse item,

**[01:11:11 - 01:11:12]** isso para a nota final, tá?

**[01:11:12 - 01:11:16]** Então, meio do devint storming, storytelling, a gente tem uma nota de zero dessa área,

**[01:11:16 - 01:11:18]** que eu acessa com a banda.

**[01:11:18 - 01:11:20]** Tá, tá em branco.

**[01:11:20 - 01:11:21]** O quê?

**[01:11:21 - 01:11:22]** Vou tô?

**[01:11:22 - 01:11:27]** Não, não para a aparece na planíria, agora vou tô.

**[01:11:27 - 01:11:32]** Eu não vai apresentar a planíria, acho que tem dados ali na Judo.

**[01:11:32 - 01:11:37]** É, eu vou falando aqui meu, para aí, deixa eu só mudar de terem

**[01:11:37 - 01:11:40]** tão pelo menos para ficar melhor, olha só para mim.

**[01:11:40 - 01:11:52]** Valei.

**[01:11:52 - 01:11:56]** Deixa eu apresentar, deixa eu só...

**[01:11:56 - 01:11:58]** Não, vai, vou falar aqui, agora, mais fácil.

**[01:11:58 - 01:11:59]** Então eu te que...

**[01:12:00 - 01:12:04]** que a gente planeira. Então, como falei, tá serão variados

**[01:12:04 - 01:12:08]** alguns topicos, uma nota de zero a 10 aqui, para cada topo.

**[01:12:08 - 01:12:10]** Então dentro de a Vint store, mi-solide, a gente tem

**[01:12:10 - 01:12:13]** a increação e a coperia a mente de OS, e a gestão de

**[01:12:13 - 01:12:17]** passas em surros. Então, para cada um desses domínios,

**[01:12:17 - 01:12:19]** você não tem a malaute de zero a 10. Então, se você não

**[01:12:19 - 01:12:22]** faz nada da parte de stock, a gestão de passas em surros,

**[01:12:22 - 01:12:25]** você vai ter a nota da criação e acompanhamento em OS.

**[01:12:25 - 01:12:28]** Você vai ser parada da gestão de passas em surros.

**[01:12:28 - 01:12:34]** Isso, o fecho de dd aqui. E aí, isso em globa, tudo

**[01:12:34 - 01:12:35]** que eu falei lá, o Vint store, o salitele, e o

**[01:12:35 - 01:12:38]** o cenário, e a líquia de obico, tá?

**[01:12:38 - 01:12:41]** Quanto, a parte de código ali, tudo fonte, né?

**[01:12:41 - 01:12:46]** Você pode me lesloar as rotas solicitadas, uma nota de zero a 10.

**[01:12:46 - 01:12:48]** Do a querfile, zero a 10.

**[01:12:48 - 01:12:51]** Building round que é o composo e subindo ali tudo bonitinho

**[01:12:51 - 01:12:56]** ambiente, zero a 10. Rídame do projeto, zero a 10.

**[01:12:56 - 01:13:01]** Segurança, a notificação já pro abrotei aplicada, zero a 10.

**[01:13:01 - 01:13:04]** Validação dos dados, sim, civil. Então, a validação ali,

**[01:13:04 - 01:13:07]** você tem de prejão, você pera, fico, a aca,

**[01:13:07 - 01:13:10]** tem nota também, a análise de vulnerabilidade,

**[01:13:10 - 01:13:14]** estão relatórios ali de vulnerabilidade, zero a 10.

**[01:13:14 - 01:13:20]** E testes automatizados, tá? Unitar em integração, zero a 10.

**[01:13:20 - 01:13:24]** Esse são os tópicos que serão avaliados.

**[01:13:24 - 01:13:31]** E aí, a gente faz a média aqui dessa média, tá?

**[01:13:31 - 01:13:35]** Dá nota final, então, tem alguns tópicos, por exemplo,

**[01:13:35 - 01:13:38]** com fonte ali, né, tem a ver com fonte,

**[01:13:38 - 01:13:40]** aqui são as rotas da querfile, build,

**[01:13:40 - 01:13:45]** round e reach make, são quatro items de avaliação,

**[01:13:45 - 01:13:48]** então, tem um peso maior nesse caso,

**[01:13:48 - 01:13:49]** do que o evento histórico, a gente só tem dois items

**[01:13:49 - 01:13:52]** para contabilizar a média, tá?

**[01:13:52 - 01:13:58]** Teste, por exemplo, é um item só, então, tem um peso menor.

**[01:13:58 - 01:14:05]** Beleza, ó, só uma dica, se aqui,

**[01:14:05 - 01:14:07]** a parte de validação de dados sensíveis,

**[01:14:07 - 01:14:08]** uma coisa super simples de implementar, né,

**[01:14:08 - 01:14:11]** você pera a fita de pejota ali,

**[01:14:11 - 01:14:14]** e placa, tem um peso, tem uma uma fita de zero a 10.

**[01:14:14 - 01:14:17]** Então, algo que vale, um peso importante,

**[01:14:17 - 01:14:20]** e algo simples de implementar, então, não deixe de validar,

**[01:14:20 - 01:14:22]** tem bastante projeto de pessoa não valida.

**[01:14:22 - 01:14:25]** E aí, fica assim, não é.

**[01:14:25 - 01:14:30]** Calma, deu um boa nota para o besteira.

**[01:14:30 - 01:14:34]** Isso aqui é a placa, ela, ela se só não tem uma validação, né.

**[01:14:34 - 01:14:36]** É só uma mais, como caso,

**[01:14:36 - 01:14:42]** o cheio de pejota, se ele peja, ele se peja, ele se peja, ele se peja, ele tem uma validação.

**[01:14:42 - 01:14:43]** Isso aí?

**[01:14:43 - 01:14:49]** Vamos dar uma dica no chat para você, está?

**[01:14:49 - 01:14:52]** É bom.

**[01:14:52 - 01:14:54]** Vamos na fãtana, Mola, e fala mais,

**[01:14:54 - 01:14:57]** dobe desse, dobe escrevei aqui no chat,

**[01:14:57 - 01:14:59]** que é vontade, pessoal.

**[01:14:59 - 01:15:01]** É um homem.

**[01:15:02 - 01:15:03]** e estamos ali.

**[01:15:03 - 01:15:13]** Avar nas dúvidas para acabar os novos novos leis da nós.

**[01:15:13 - 01:15:28]** Opa, um bonoite novamente.

**[01:15:28 - 01:15:29]** Vamos.

**[01:15:29 - 01:15:35]** Só vai se ligar de o blico, a como a gente vai ter basicamente só um serviço.

**[01:15:35 - 01:15:41]** O que a gente vai listar vai ser interior simples, né?

**[01:15:41 - 01:15:48]** Hoje eu não vai ter uma vez uma entidade com dois novos no mesmo.

**[01:15:48 - 01:15:51]** O mesmo serviço, por exemplo.

**[01:15:51 - 01:15:59]** O que a gente não é assim, por que é simples de fato, falando de domínio assim,

**[01:15:59 - 01:16:02]** então, o que não é muito complexo, né?

**[01:16:02 - 01:16:07]** Não vai ter essa conflito, né?

**[01:16:07 - 01:16:10]** Ainda não, né?

**[01:16:10 - 01:16:11]** Mas não.

**[01:16:11 - 01:16:16]** E aí, mas de qualquer forma é importante fazer, tá?

**[01:16:16 - 01:16:21]** A gente deixa ele estar mais só pente desse erro.

**[01:16:21 - 01:16:23]** Não está pegado no final.

**[01:16:23 - 01:16:26]** Não, não.

**[01:16:26 - 01:16:31]** É, uma coisa que ficou ali, eu vou voltar lá atrás, normalmente.

**[01:16:31 - 01:16:36]** Você, eu fui um pouco explicado, mas ainda fiquei só com a ideia na cabeça.

**[01:16:36 - 01:16:42]** É sobre o tempo de execução dos serviços, o que está listo apontuado ali.

**[01:16:42 - 01:16:46]** É a quantidade de serviços, o descrito, não é esse.

**[01:16:46 - 01:16:51]** Eu tô imaginando, ah, tem um marketing serviço que tem 4 tipos de serviço.

**[01:16:51 - 01:16:53]** Uma outra, 6 tipos, né?

**[01:16:53 - 01:16:57]** A gente vai contemplizar a sua soma execução delas, né?

**[01:16:57 - 01:17:01]** De todos os serviços, certo?

**[01:17:01 - 01:17:04]** Fui isso que eu falou?

**[01:17:04 - 01:17:06]** Ah, de cada, oh, esse.

**[01:17:06 - 01:17:09]** Não, de cada ser visto.

**[01:17:09 - 01:17:11]** De cada ser visto.

**[01:17:11 - 01:17:17]** Imagina que você tem um OS, aqui não está super explicando no documento, mas aqui,

**[01:17:17 - 01:17:22]** imagina que eu posso incluir um mais serviço dentro do moécio.

**[01:17:22 - 01:17:26]** Então, eu posso deixar o ponto aqui.

**[01:17:26 - 01:17:30]** Tem os 3 OS, diferente, que tem uma troca de olho.

**[01:17:30 - 01:17:35]** Cada OS tem uma quantidade enxipção z, serviço.

**[01:17:35 - 01:17:43]** Mas então vou calcular essa média de execução, por exemplo, da troca de olho, que é em comum.

**[01:17:43 - 01:17:44]** Ah, troca de olho.

**[01:17:44 - 01:17:48]** Pode detalhar, você pode detalhar nesse nível, onde eu passo um serviço,

**[01:17:48 - 01:17:51]** ligue o me d'altem por resposta daquele serviço.

**[01:17:51 - 01:17:53]** Mas aqui, a binginéria é com o mesmo, tá?

**[01:17:53 - 01:17:56]** Ele quer o tempo, a média é com ser um dos serviços.

**[01:17:56 - 01:17:58]** Então, um geral, enfim, depende de setorca de olho, né, aumenta.

**[01:18:00 - 01:18:04]** O que for? Mas, caso, se quer dizer que você é criar um em de ponte passando,

**[01:18:04 - 01:18:10]** esse parâmetro, o tipo de serviço e ele dá o tempo em área de também chamou de bola.

**[01:18:10 - 01:18:14]** Mas é baseado no serviço, não é esse, tá?

**[01:18:14 - 01:18:22]** Nós serviços solicitados. Isso.

**[01:18:22 - 01:18:24]** Isso. Então assim, tem um...

**[01:18:24 - 01:18:29]** um OS contra a cadeia ali em aumento e um OS com...

**[01:18:29 - 01:18:33]** sei lá, a troca dos pneus.

**[01:18:33 - 01:18:40]** Quanto o qual tem o comercio da troca de aia, da linha a mento e da troca dos pneus?

**[01:18:40 - 01:18:44]** Então você tem que ocular o tempo sem impred execução de cada serviço

**[01:18:44 - 01:18:49]** para poder fazer essa média depois, tá?

**[01:18:49 - 01:19:00]** Ah, então, por exemplo, é uma média gastada no OS inteira, que todos os serviços não ali.

**[01:19:00 - 01:19:03]** De todas as OS espocives, né?

**[01:19:03 - 01:19:09]** Mas então, é daquele ponto que ele que começa execução até o ponto de ficar finalizado, né?

**[01:19:09 - 01:19:13]** É, não por quê? Porque aqui...

**[01:19:13 - 01:19:23]** Aqui é a execução da OS, imagine que eu tenho um OS com um troca de aia aia a mento.

**[01:19:23 - 01:19:29]** E a troca de aia ele fez em uma hora e a aia a aia a mento ele demorou em duas OS.

**[01:19:29 - 01:19:35]** Quando ele termina a troca de aia, a aia a aia, se não vai para finalizar ainda.

**[01:19:35 - 01:19:38]** O meu serviço vai.

**[01:19:38 - 01:19:40]** Sim.

**[01:19:40 - 01:19:43]** E aí é um tempo dele, uma hora. Aia a aia a mento ele demorou duas.

**[01:19:43 - 01:19:47]** Então, já tem o tempo médio de uma hora e meia nesse caso, né?

**[01:19:47 - 01:19:51]** Mas, eu tenho um outro serviço no outro OS que demorou três horas.

**[01:19:51 - 01:19:54]** Aí, eu tenho um de uma hora, um de duas, um de três.

**[01:19:54 - 01:20:00]** Então, eu tenho por merdios duas horas do serviço de todos os serviços equipados da noficina.

**[01:20:00 - 01:20:05]** Então, eu vou fazer um gai de ali, pelo portou dos serviços independente de OS

**[01:20:05 - 01:20:07]** e cáPULOW tem o que não é de resposta, né?

**[01:20:07 - 01:20:12]** Sabe que acartem o que acartem o que acartem o que acartem o que acartem isso e caras que

**[01:20:12 - 01:20:17]** acartem isso e caras que iniciam o que acartem isso e depois vai ter o base para fazer calculta.

**[01:20:17 - 01:20:24]** Muy.

**[01:20:24 - 01:20:26]** Não, eu fico clara.

**[01:20:27 - 01:20:31]** Cara.

**[01:20:31 - 01:20:34]** Com lica que o...

**[01:20:34 - 01:20:38]** Não é uma falar que a gente dá de serviço, né, esperem de se me ajudar de eu não

**[01:20:38 - 01:20:42]** vai ter que ter essas informações, particulares dela, né?

**[01:20:42 - 01:20:43]** Pra gravar.

**[01:20:43 - 01:20:48]** porque se está dizendo que você deixa esse tempo, esse time está em

**[01:20:48 - 01:20:56]** um tempo que eu gastei maiore um serviço, vai acabar afetando o outro,

**[01:20:56 - 01:20:59]** ainda numa impressão é rada, ali na muito...

**[01:21:00 - 01:21:20]** exatamente. E que estava pegadinha então? É, sai, por isso ir a orgica. Entendi

**[01:21:20 - 01:21:24]** ele em qualquer isso vai estar tudo mocado, eu vou ter um serviço para

**[01:21:24 - 01:21:32]** caras estatus. Esse está tudo aqui da OEC está, do serviço a gente não tem nada,

**[01:21:32 - 01:21:38]** o serviço está aqui, tá? O serviço basicamente é executado, então,

**[01:21:38 - 01:21:47]** o serviço de acelizeta é executado. É, vai ter um estado dele ali de

**[01:21:47 - 01:21:57]** interna execução e finalizar. E me isso é executado, finalizar. Vamos dar esse

**[01:21:57 - 01:22:04]** preço aqui no documento, tá? É, a única forma de a gente me dê assim, então,

**[01:22:04 - 01:22:15]** ele aí, então aqui nesse caso, tá? Puma. Puma, galera. Tadando no novioras aqui,

**[01:22:15 - 01:22:22]** na vida, isso, eu vou deixar aqui até lá, vc, com o pra ver se eu não tenho mais alguma

**[01:22:22 - 01:22:28]** dúvida. O material, boa noite, pessoal. É só se clare há um pouco mais

**[01:22:28 - 01:22:33]** minha dúvida aqui em relação aos Estados. Você estava comentando mais

**[01:22:33 - 01:22:41]** cedo que cada estado, saliu, teriam em tempo médio, entre, como se colocar ali

**[01:22:41 - 01:22:51]** execução e finalizado ali na hora de ser visto. É, esse time aí, ele vai ser,

**[01:22:51 - 01:22:58]** a gente vai ter um, vai estar funcionando ali, automaticamente, um com de

**[01:22:58 - 01:23:03]** óbviozinho, ou, ou, ou, ou, a gente vai setar esse enrádico do adjale para

**[01:23:03 - 01:23:09]** poder só chamar, lhe um gétzinho e, como se fosse um histórico deles.

**[01:23:10 - 01:23:15]** É, você queria um antiponte, né, de alteração de, de status, então.

**[01:23:15 - 01:23:20]** Aí eu posso jogar ali a finalizei aquela, e a gente vai ficar com o sal e a

**[01:23:20 - 01:23:24]** cara. Calcular o tempo ali de cada um. Exatamente.

**[01:23:24 - 01:23:39]** Vem de, beleza. Exatamente. É, o vitorei, isso mesmo, tá? Então, a

**[01:23:39 - 01:23:43]** isso vai ficar em execução até finalizar tudo que foi contratado e definindo

**[01:23:43 - 01:23:50]** dentro daquela OS, de ser disso. É, a partir, né, assim, que é que o

**[01:23:50 - 01:23:53]** mecânico ali, então, se finalizar todos os serviços que estão indo a OS,

**[01:23:53 - 01:23:56]** é, vai ser, então, como finalizada.

**[01:24:00 - 01:24:06]** Então, realmente, ponte ali como se fosse o mecânico ali dando conclusão, você diz isso.

**[01:24:06 - 01:24:08]** Isso, isso aí.

**[01:24:08 - 01:24:18]** Então, mas ali diz que a U-Staste que seu alterador automaticamente, então, tipo, não...

**[01:24:18 - 01:24:24]** Eu entendo por esse texto que não vai ter um alcheão de fato, é, não vai ser uma...

**[01:24:24 - 01:24:31]** Não vai ser uma ação de fato ali, tipo, o mecânico ali, o funcionário, ele não vai entrar no sistema e atualizar os Estados.

**[01:24:31 - 01:24:34]** Ele vai fazer alguma coisa que vai atualizar automaticamente.

**[01:24:34 - 01:24:40]** Imagina que eu tenho no serviços, então, você pode implementar, quando eu tiver todos os serviços daquela

**[01:24:40 - 01:24:47]** S finalizado, ele altera automaticamente, o Estados Store de serviço, da O S, para finalizar.

**[01:24:47 - 01:24:51]** Uma que eu sou um patirão do...

**[01:24:51 - 01:24:55]** Por mim, eu tenho que gerar o orçamento.

**[01:24:55 - 01:25:01]** E o orçamento tem que ser aprovado antes, então tem que ter um passo do cliente, ele faz com a das,

**[01:25:01 - 01:25:08]** faz o pedido, aí vai gerar o orçamento, certo?

**[01:25:08 - 01:25:11]** E aí esse orçamento tem que ser aprovado pelo cliente.

**[01:25:11 - 01:25:15]** Depois eu posso dizer que o target de serviço, o que é que faça...

**[01:25:15 - 01:25:17]** Isso, isso aí, então.

**[01:25:17 - 01:25:19]** Eu tenho que ser aprovado, né?

**[01:25:19 - 01:25:21]** Isso, o que é?

**[01:25:21 - 01:25:25]** E aí, assim, como que funciona uma mecânica, não tem um...

**[01:25:25 - 01:25:27]** Você não entram no sistema o cliente, não entram no sistema, prova.

**[01:25:27 - 01:25:29]** Então, geralmente, ele gala, falar, falar, tá tudo certo.

**[01:25:29 - 01:25:32]** Eu vou te fazer, vou levar a mecânica, que fala, pode fazer, tá, provar, assim,

**[01:25:32 - 01:25:33]** lá no comento.

**[01:25:33 - 01:25:36]** E aí, o Becanico vai botar em essa discussão enorme.

**[01:25:36 - 01:25:40]** Por aí, bem, beleza.

**[01:25:40 - 01:25:42]** Tá.

**[01:25:42 - 01:25:49]** Desculcular essa questão aí, abitinho só, só para finalizar essa questão da alteração,

**[01:25:49 - 01:25:58]** o meio do que em que pregunto aqui, da alteração automática de Estados.

**[01:25:58 - 01:26:02]** É só para morrer de certeza.

**[01:26:02 - 01:26:07]** O mecânico vai ter uma funcionalidade onde ele vai colocar,

**[01:26:07 - 01:26:10]** serviço dele, ele é confinalizado.

**[01:26:10 - 01:26:15]** E automaticamente, esse estápso no sistema vai mudar,

**[01:26:15 - 01:26:20]** para o atendente lado outro lado e identificar, olha, o serviço já foi concluído,

**[01:26:20 - 01:26:22]** demorou tanto tempo.

**[01:26:22 - 01:26:24]** Isso aí vai refletir ali, não é?

**[01:26:24 - 01:26:25]** Isso mesmo.

**[01:26:25 - 01:26:26]** Isso aí.

**[01:26:26 - 01:26:28]** Agora, só tem três serviços de um mecânico,

**[01:26:28 - 01:26:31]** mas o que eu acho que não servei se foi finalizado,

**[01:26:31 - 01:26:32]** é se vai continuar lá em aberto.

**[01:26:32 - 01:26:34]** Em execução, né?

**[01:26:34 - 01:26:39]** Então, quando o último mecânico colocou o último serviço do S,

**[01:26:39 - 01:26:42]** foi finalizado aí, chega lá para...

**[01:26:42 - 01:26:45]** Filar para atender a gente, enfim, isso aí, isso pensa no nome,

**[01:26:45 - 01:26:46]** que é grande, né?

**[01:26:46 - 01:26:49]** Mas, grande parte do mecânico, só tem o mecânico e,

**[01:26:49 - 01:26:51]** do ajudante.

**[01:26:51 - 01:26:54]** Eu não tenho sistema, tá vendo?

**[01:26:54 - 01:26:57]** Mas aí, isso tá?

**[01:26:57 - 01:27:00]** Então, é mais necessitido, porque não tem como alitar...

**[01:27:00 - 01:27:03]** a outra é uma automática de necessidade de conforme ações. Mas não tem como assim,

**[01:27:03 - 01:27:09]** automático é muito relativa, tem que vai ter uma pessoa ali que vai e putar as coisas

**[01:27:09 - 01:27:13]** no sistema e vai andar com o estado, então é certa.

**[01:27:13 - 01:27:15]** E que você tem um anteriário?

**[01:27:15 - 01:27:18]** Temos que chegar ao meu carro lá e vai recebido.

**[01:27:18 - 01:27:23]** Se a gente vai adocar o carro, eu sei, tem alguém que lá, que vai lá no sistema e puta,

**[01:27:23 - 01:27:26]** que o dia que nós ficou efeito, por exemplo, já eu acho que não é realizado.

**[01:27:26 - 01:27:32]** E aí, mandala, descreve no, por exemplo, que precisa, vai ser um serviço de as peças e aí,

**[01:27:32 - 01:27:34]** por dia, era o orçamento, beleza?

**[01:27:34 - 01:27:39]** E putamos o sistema de arogentamente que transforma automática com base no preço das peças e dos serviços de

**[01:27:39 - 01:27:41]** manda por clientes.

**[01:27:41 - 01:27:43]** Ah, a gente ligou a provou.

**[01:27:43 - 01:27:46]** Pude, você vai ter que ir lá e colocar em a execução.

**[01:27:46 - 01:27:48]** Estou.

**[01:27:48 - 01:27:52]** Está dá para colocar um as paraguindo e não há de sap, também.

**[01:27:53 - 01:27:54]** Eu não estou matizada.

**[01:27:54 - 01:27:58]** Isso aí.

**[01:27:58 - 01:28:07]** Essa descrição é linda que está todos esses balids que estão debaixo da criação da

**[01:28:07 - 01:28:08]** orden de serviço.

**[01:28:08 - 01:28:12]** Eles não estão implica no maior dentro, para mim, eu posso.

**[01:28:12 - 01:28:16]** Eu que vou modelar ali de acordo com meu evento storm, ali.

**[01:28:16 - 01:28:23]** Ou seja, por exemplo, eu não preciso gerar um orçamento logo após da ordem secreada.

**[01:28:25 - 01:28:26]** Não.

**[01:28:26 - 01:28:30]** Não, é, é, você, no caso, acho que nem consegue, né?

**[01:28:30 - 01:28:31]** Não consegue, né?

**[01:28:31 - 01:28:33]** É, foi, eu sei que eu fiquei com o tato de ponto,

**[01:28:33 - 01:28:37]** e eu acho que não sei será para a ordem que isso não obrigações.

**[01:28:37 - 01:28:41]** Não, é, já acaba assim na ordem, porque eu acho que você não consegue fugir dessa ordem.

**[01:28:41 - 01:28:42]** Não, eu sei.

**[01:28:42 - 01:28:44]** Eu digo sobre os itens ali em cima, né?

**[01:28:44 - 01:28:50]** Porque ele já me implica, você vai preste um formulário, o formulário tem que ter isso isso isso.

**[01:28:50 - 01:28:54]** E aí, eu imagino que ele queria dizer tipo, como sua somora de acontecimentos,

**[01:28:54 - 01:28:59]** terminou formulário, preencheu, criou a ordem de hermosamento automáticamente.

**[01:28:59 - 01:29:00]** Só que como é que eu vou gerar um orçamento,

**[01:29:00 - 01:29:03]** sendo que ele tem que passar pelo estados do indiagnóstico ainda.

**[01:29:03 - 01:29:05]** E isso, sei.

**[01:29:05 - 01:29:06]** E isso, é, é, é.

**[01:29:06 - 01:29:07]** Sim.

**[01:29:07 - 01:29:10]** É, no caso, é, como estou tudo antes, né?

**[01:29:10 - 01:29:16]** Tudo antes daí, recebe o orçamento e depois, vai gerar a ordem de serviço.

**[01:29:16 - 01:29:19]** No caso, depois que está aprovado o orçamento,

**[01:29:19 - 01:29:22]** ele vai estar em diagnóstico, agora, provação, tudo.

**[01:29:23 - 01:29:24]** E isso, né?

**[01:29:24 - 01:29:27]** É, no caso assim, por exemplo, você vai incluir e putar por exemplo,

**[01:29:27 - 01:29:31]** esses dados aqui, dedicação, cadaz da placa e o que você quer fazer.

**[01:29:31 - 01:29:33]** Aí, beleza, mandou a S.

**[01:29:33 - 01:29:36]** E essa recebida.

**[01:29:36 - 01:29:38]** E aí, o bem-cânico foi lá, fala, são serviços.

**[01:29:38 - 01:29:41]** Ele botou um mudou e saca para a indiagnóstico.

**[01:29:41 - 01:29:45]** Fredio de anógico, ele sabe o que vai precisar fazer, o que parece que vai precisar,

**[01:29:45 - 01:29:46]** né, colocar.

**[01:29:46 - 01:29:51]** E aí, e com base no serviço que ele já tem nas peças, ele consegue mandar o orçamento.

**[01:29:51 - 01:29:53]** Então, já era o orçamento.

**[01:29:53 - 01:29:55]** E o orçamento é provado, né?

**[01:29:55 - 01:29:57]** Ele entra em execução, e aí, finaliza entrega.

**[01:29:57 - 01:30:00]** Então, segue alguma ordem.

**[01:30:00 - 01:30:06]** aqui, mas acaba aqui, o receimento da OS, entre diagnóstico, e aí o mecânico

**[01:30:06 - 01:30:13]** viau a possibilidade de peças, definiu as peças de aerosamento, um tratamento,

**[01:30:13 - 01:30:21]** o tratamento da mecção, se o cliente aprovou todos o serviço ou enfim, ou um serviço

**[01:30:21 - 01:30:27]** a opção também, que é bem comum também, pode criar o cenário, ali no fluxo de voltar,

**[01:30:27 - 01:30:32]** a mandada no ovo orçamento, opa, no aprovo esse, como que vai ser meu fluxo,

**[01:30:32 - 01:30:34]** quando ele não aprova orçamento.

**[01:30:34 - 01:30:38]** Eu vou enviar a orçamento, eu vou pedir para ele mais informação,

**[01:30:38 - 01:30:41]** porque ele quer, enfim, aí eu vou que vocês vão estar.

**[01:30:41 - 01:30:43]** Sim, não tem certo errado que,

**[01:30:44 - 01:30:48]** portanto, isso já algo a mais está, vocês podem seguir pelo básico.

**[01:30:49 - 01:30:53]** É claro, é importante você escolar e fluxo de excessão e fluxo alternativos,

**[01:30:53 - 01:30:57]** então não só fluxo o fluxo ferizos, mario ferizos.

**[01:30:57 - 01:30:59]** Ah, provou a bolitinho, tá? E quando ele reprova,

**[01:30:59 - 01:31:02]** que você tem uma com eles comporta, quando eu não tenho um pérsate,

**[01:31:02 - 01:31:03]** como eles comporta,

**[01:31:05 - 01:31:08]** isso é legal colocar, não necessariamente vocês precisam implementar tudo, tá?

**[01:31:08 - 01:31:11]** Pementação vai muito, então, tudo que a gente está pedindo aqui,

**[01:31:11 - 01:31:15]** mas no desenho do domínio ali, como vai funcionar o fluxo do negócio,

**[01:31:15 - 01:31:18]** é legal fazer esse botar em esses fluxos de excessão, tá?

**[01:31:20 - 01:31:23]** Ou, uma tesoula, acho que, para ficar,

**[01:31:23 - 01:31:28]** pelo menos pelo menos, entendimento, é a ordem recebida, na verdade,

**[01:31:28 - 01:31:30]** eu entendo que é pela parte do cliente,

**[01:31:30 - 01:31:33]** por eu quero trocar, vou usar o exemplo que está exposto,

**[01:31:33 - 01:31:36]** vou trocar, eu quero trocar o óleo e fazer um aninhamento.

**[01:31:37 - 01:31:39]** É isso que recebo de atendimento do cliente.

**[01:31:39 - 01:31:44]** O diagnóstico é, tipo de óleo, o aninhamento das quatro ródum,

**[01:31:44 - 01:31:48]** a roda e aí eu devolvo com um orçamento,

**[01:31:48 - 01:31:52]** e aí fica no próximo estado de aguardando a provação.

**[01:31:52 - 01:31:55]** A partir daí, eu crios dos fluxos de aprovado,

**[01:31:55 - 01:31:59]** executa, reprovado, finaliza.

**[01:32:00 - 01:32:02]** Isso aí, até porque assim,

**[01:32:02 - 01:32:05]** você pode nem ter um serviço específico, né?

**[01:32:05 - 01:32:08]** Sim, pode ser um serviço geralático, uma notenção.

**[01:32:08 - 01:32:10]** Porque aí eu quero poder ter um alábio, eu calta com problema.

**[01:32:10 - 01:32:12]** Então, recebio é essa só identificar,

**[01:32:12 - 01:32:14]** CPF, placa e calma, né?

**[01:32:14 - 01:32:17]** E aí, ele vai entrar em dragão nócico, aí,

**[01:32:17 - 01:32:18]** sim, eu me cano e covar em indicar,

**[01:32:18 - 01:32:21]** que serviço que precisa fazer, que peça que você incluída,

**[01:32:21 - 01:32:24]** não me sabe, tinha o fíjate, já chega com o serviço ali pronto, né?

**[01:32:24 - 01:32:25]** Entida, boa.

**[01:32:25 - 01:32:27]** Chou de bola, valeu, por favor.

**[01:32:30 - 01:32:34]** Olá.

**[01:32:34 - 01:32:35]** Pula, galera.

**[01:32:35 - 01:32:41]** É, vamos ver se tem uma legula para a gente finalizar.

**[01:32:41 - 01:32:43]** Prefer uma dúvida aqui.

**[01:32:43 - 01:32:47]** Então, o único indipântico que não precisaria necessariamente

**[01:32:47 - 01:32:50]** de alpentecação seria o de criação da Oesca,

**[01:32:50 - 01:32:52]** seria por parte do cliente, então.

**[01:32:52 - 01:32:55]** O resto são todos em dipântico mais digestão.

**[01:32:55 - 01:33:00]** É, aqui, na verdade, o que vai criar, eu próprio me cano.

**[01:33:00 - 01:33:03]** quando é a própria mecânica.

**[01:33:03 - 01:33:06]** Mas ali é um fluxo que da gente não tá pedindo as autenticações.

**[01:33:06 - 01:33:08]** Então, o mecaninho pra entrar no sistema e vai criar o S.

**[01:33:08 - 01:33:13]** Se você vai tirar, olha, vai criar não, vai identificar, vai tirar o Ico, está abelesa.

**[01:33:13 - 01:33:17]** Agora, para que já administrate, vai onde eu faço o crúdio, ali do veículo.

**[01:33:17 - 01:33:21]** Não, não quando eu as socios ele não é, se não é, se não.

**[01:33:21 - 01:33:24]** Então, para que eu casas trânsis, que elas trânsis verão,

**[01:33:24 - 01:33:28]** que ele é um serviço e as peças aí, sim, é preciso dar autenticações.

**[01:33:28 - 01:33:32]** Então, eu vou ter a indicação, que é um fluxo administrativo mesmo do sistema.

**[01:33:32 - 01:33:37]** Agora já tinha um crúdio cadastrado, já tinha o veículo, por exemplo,

**[01:33:37 - 01:33:40]** aí a caixa não de orir em serviço, como é que vai fazer ali, não precisa.

**[01:33:40 - 01:33:46]** Ahem, quem gira, obrigada.

**[01:33:46 - 01:33:52]** Aqui é só para garantir que, de fato, quem tá se showando essas rotas, é um administrador do sistema.

**[01:33:52 - 01:33:58]** Só ele que tem a autorização, a cessar esses indipóintes aqui, então.

**[01:33:58 - 01:34:06]** O que é claro?

**[01:34:06 - 01:34:16]** Bom, vou responder aqui as últimas no chat, olha.

**[01:34:16 - 01:34:21]** Relatório de vulnerabilidade, ou as pesape vai servir como complemento,

**[01:34:21 - 01:34:24]** o Termativo do Sonar, é um complemento, tá?

**[01:34:24 - 01:34:28]** Sonar, a gente vai dar ali muito um cenário de pipeline,

**[01:34:28 - 01:34:31]** e mesmo, né, ali de projeto, build, ali.

**[01:34:31 - 01:34:39]** O as pesape, a gente consegue fazer um teste em tempo de execução, né,

**[01:34:39 - 01:34:43]** do nosso app, tá, um pouco diferente.

**[01:34:43 - 01:34:48]** E aí, os dois complementos aqui nesse caso.

**[01:34:48 - 01:34:51]** Outra pergunta, ou a gente pode subir também como apoio uma infrafrita,

**[01:34:51 - 01:34:56]** a infrafrita, como a renda, ou a sua talvez, não.

**[01:34:56 - 01:34:59]** Nesse momento não, tá?

**[01:34:59 - 01:35:05]** Doi eu quero que eu compose, que inclusive é uma cobrança que do projeto, então,

**[01:35:05 - 01:35:08]** localmente, local nesse momento.

**[01:35:08 - 01:35:18]** Bom, claro, como você é uma vestindo ali, então, também escuro ainda,

**[01:35:18 - 01:35:20]** ou biscuro.

**[01:35:20 - 01:35:26]** Olha galera, é mais simples do que vocês, imagina, então, você apai

**[01:35:26 - 01:35:28]** que em tantos aos detalhes, não.

**[01:35:28 - 01:35:30]** E, portanto, é, seguir em que as melhores práticas,

**[01:35:30 - 01:35:35]** satientarem aos requisitos mínimos e qualquer dúvida que você estiver,

**[01:35:35 - 01:35:41]** mas quer dúvidas, né, que vocês tiveram que ao longo do curso de implementação,

**[01:35:41 - 01:35:45]** como eu, professor Rogério, que estaríamos disponíveis ao Discord,

**[01:35:45 - 01:35:50]** e, enfim, teremos os grupos também de estudo, outros encontros,

**[01:35:50 - 01:35:53]** para a gente poder estar mais dúvidas, tá?

**[01:35:53 - 01:35:54]** E vocês?

**[01:35:54 - 01:35:59]** Espero que tenham ajudado aqui essa lágia.

**[01:36:00 - 01:36:02]** Té, que tem um contributo de alguma forma.

**[01:36:02 - 01:36:05]** Ela tinha gravado também, então, caso vocês...

**[01:36:05 - 01:36:08]** Perdeu, a limetática, a nossa atrasada.

**[01:36:08 - 01:36:15]** A manhã essa no migano já tá disponível aqui no plano discord para vocês.

**[01:36:15 - 01:36:21]** Top galera, que é a falar alguma coisa aí, o professor Rogério.

**[01:36:21 - 01:36:23]** Não, não.

**[01:36:23 - 01:36:23]** Acho que...

**[01:36:23 - 01:36:24]** Não, é claro.

**[01:36:24 - 01:36:27]** Na declaro.

**[01:36:27 - 01:36:28]** Opa.

**[01:36:28 - 01:36:30]** Então, galera, agradeço.

**[01:36:30 - 01:36:33]** Mas uma vez aqui, apresenta de todos, horário,

**[01:36:33 - 01:36:36]** você que tá tarde, você não tá cansado, mas é brigada, é importante,

**[01:36:36 - 01:36:39]** mas não se participar em todos os encontros.

**[01:36:39 - 01:36:44]** Eu acho que, ao diferencial dessa após aqui das demais,

**[01:36:44 - 01:36:47]** no modelo de postec,

**[01:36:47 - 01:36:50]** que aqui a gente tem esse espaço de fato de troca de compartilhamento,

**[01:36:50 - 01:36:53]** se encontros ao vivo aqui, que a gente pode colocar.

**[01:36:53 - 01:36:58]** Vamos, outras, vou juntos, por isso a gente tem um cenário.

**[01:36:58 - 01:37:00]** Eu tomei te assim que não tem essa interação,

**[01:37:00 - 01:37:04]** então ajuda bastante a aproveitem o máximo aqui esses momentos.

**[01:37:04 - 01:37:08]** As likes também são, são bem legais, os grupos de estudos.

**[01:37:08 - 01:37:10]** Eu costumo...

**[01:37:10 - 01:37:13]** Quando a gente tentou, é muito grande, assim, que fica muito difícil,

**[01:37:13 - 01:37:18]** que a gente conseguir tirar dúvida, essa dúvida de cada um,

**[01:37:18 - 01:37:21]** imagina, entre 150 pessoas no grupo de tudo.

**[01:37:21 - 01:37:25]** Imagina, 30, 40 pessoas levantam a mão e uma hormina,

**[01:37:25 - 01:37:30]** então, eu gosto bastante de debate,

**[01:37:30 - 01:37:35]** assunto de fato de adirar, de trabalho, como a gente vê isso sendo aplicado.

**[01:37:35 - 01:37:39]** O trabalho, estou pensando até trazer algo relacionado,

**[01:37:39 - 01:37:42]** vou conversar com um gênero, e depois,

**[01:37:42 - 01:37:44]** para trazer um pouco do tema de ata,

**[01:37:44 - 01:37:47]** também aplicado ao que a gente está aprendendo aqui,

**[01:37:47 - 01:37:49]** então, tudo que a gente está vendo aqui, como que a ajuda,

**[01:37:49 - 01:37:50]** como que é aplicada, porque,

**[01:37:50 - 01:37:53]** considera, a gente está vendo muito forte, se tem uma hoje,

**[01:37:53 - 01:37:54]** não dá pra gente fugir disso,

**[01:37:54 - 01:37:57]** acho que isso vai agregar bastante pra vocês, tá?

**[01:37:57 - 01:38:00]** Então, se vocês terem sugestão também de tema,

**[01:38:00 - 01:38:02]** algo que vocês queiram trazer,

**[01:38:02 - 01:38:04]** para o professor tem um tema legal,

**[01:38:04 - 01:38:05]** via o legal que na minha empresa,

**[01:38:05 - 01:38:09]** que era levar para o trator, que faz total sentido que a gente está aprendendo,

**[01:38:09 - 01:38:12]** enxerme lá no Discord no privado, a gente escute,

**[01:38:12 - 01:38:14]** espaço superaberta, que tá.

**[01:38:14 - 01:38:21]** O combinar lá e ver para apresentar o caso, lá no Brasil, é desco.

**[01:38:21 - 01:38:22]** Boa.

**[01:38:22 - 01:38:25]** Então, eu vou mandar um pereino saindo ainda,

**[01:38:25 - 01:38:30]** como aí, como aí, eu vou mandar um formulário,

**[01:38:30 - 01:38:33]** que é muito importante,

**[01:38:33 - 01:38:37]** vocês responderam, ajuda muito a gente,

**[01:38:37 - 01:38:39]** deixa eu só pegar aqui,

**[01:38:39 - 01:38:43]** bem rapidinho, eu vou mandar no chat,

**[01:38:43 - 01:38:47]** como aí, como aí,

**[01:38:47 - 01:38:49]** não é uma pessoa tá saindo,

**[01:38:49 - 01:38:52]** pessoa tá afirindo o formulário,

**[01:38:52 - 01:38:54]** como daqui,

**[01:39:00 - 01:39:03]** Vou mandar aqui para vocês responder aí aqui sobre essa live tá?

**[01:39:03 - 01:39:07]** A gente aqui em esteve então, se eles são nem um curso lá de vocês,

**[01:39:07 - 01:39:15]** software architecture, a turma aqui em Zsote, vou colocar aqui escrito,

**[01:39:15 - 01:39:23]** por uma quinhinha de Sote e aí no nome da live, vocês são meu vindo?

**[01:39:23 - 01:39:27]** Então, a prevenção de C.

**[01:39:28 - 01:39:29]** O vinha é prejou.

**[01:39:29 - 01:39:31]** O aqui?

**[01:39:31 - 01:39:34]** Então, eu me chovi na C.

**[01:39:34 - 01:39:36]** Vou aqui, não é uma traválente.

**[01:39:36 - 01:39:44]** Agradeço a sua espada responder que ajuda bastante a gente que tá melhorável e sem...

**[01:39:44 - 01:39:54]** Toa, a gente, obrigado, viu pela presença,

**[01:39:54 - 01:39:58]** ótima, não te pela vocês, bonso canso e até a próxima, até a próxima encontro aí.

**[01:39:58 - 01:39:59]** Valeu, boa noite.

**[01:39:59 - 01:40:00]** Valeu.

## Texto corrido

Bom, galera, eu vou começar aqui, é primeiramente bom. Não te atose, eu me enfim um desses que... Aí eu professor Rogério, que hoje eu vou vocês. E hoje, como falamos na última semana, eu vou dizer onde irá apresentarmos aqui um pouco do desafio. Então, o que te anjequie, que vocês serão aqui entregado a final aqui da primeira fase. Então, o que é um espaço onde eu vou passar o PDF com vocês, vem nos desafios, todos os requisitos, tudo que a gente precisa entregar, da forma que a gente precisa entregar. E aí, eu espasse o bem aberto que, para... vocês tira em dúvidas, está tudo relacionado hoje, especificamente aqui a entrega tal dos desafios. Duvidas de conteúdo da matéria, mais dessa semana continuem ali, mandando um discorde, e logo, teremos um próximo encontro aqui, nas próximas semanas, com os encontros de grupo de estudantes, onde a gente pode discutir aqui, tem uma das alvas, está trazer algum tipo algum outro tipo de conteúdo, tudo mais. Mas hoje, o objetivo aqui é passar com vocês, de conta ponta, assim, traga aqui do que a gente está esperando, melhor forma de fazer, enfim, tudo que vocês sabem, sabe aí, tá bom? O vídeo pro professor Rogério, que fica de olho, na galera que tá entrando ainda, porque vai chegar a gente, e na terra, umas ou bitas de chegar a gente, o favor de mentir pessoal, quem tiver dúvida levanta mão aqui, eu não posso saber como falei, para falar, também no chat está disponível aqui para dúvida, eu tô de olho aqui do lado, hoje é do também, tá, no show, e eu não essa tá, bora lá, estão vendo minha tela? Sim, tem que ver. Então, vamos ver, bola, vamos lá, acho que acredito que, que a gente pode você já tira curiosidade, já tira baixados desafios, já tiveram, já saiu desafio lá também, se isso já sei lá para tá falando, provavelmente, é. Já assim, ver o professor. Bom, boa, deixa eu. Mas vamos passar aqui de ponta ponta, acho que nem tudo mundo, eu enfim, esse passo para a gente, quando a tiradas do beleza aqui necessária está, vou maralar então. É, como eu falei na aula de abertura, né, até que já não tinha que eu projear que dá faz, que em Glova que todos os conhecimentos serão obitídos aqui, todos as disciplinas das fazes, que no caso, na primeira faz. Então, uma motividade que, a princípios e da deslo de Glova do grupo, não é poder indo ou não se deslo de Glova do grupo, você não é individual também. O grupo até cinco pessoas, como falei também na última live, é importante, se a gente atentar, se é o prazo de entrega, é, pra baixo. entregue, posa a data combinada, da data determinada no próprio sistema da FIP, terá automaticamente um disconto de 30% de valor da nota, trazando, você pode trazer a 10 segundos, terá um desconto da nota, via sistema, então, um setente se atente ao prazo, beleza, como já comentem também, falando que esse trabalho vale 90 pontos, juntando de cooperate ao apresentar vocês vão fazer na frente, isso vai dar 100 pontos no final, tá? Bora por dizer afio, só pra gente alinhar que não sei se tudo é uma aqui, participar da live de abertura, eu tenho a oportunidade de ver alguém ficou com alguma dúvida em relação à nota, vamos ver que ele aqui não, tá, tranquilo, não sei, nem colocando no chat também, pode colocar aquele que eu tenho praia que eu não tenho, vamos ver desde, agora lá então, agora te, porra, que ele entendeu, a senhora se são das quatro fase sempre, quatro tem que ele te alente de 90 pontos, isso mesmo, isso aí, e mais uma coisa, vai ser, ouvi lá que existe uma somatória, 90, mais 10, e esses 10 pontos das solididades presencial, você vai ser somatima ou uma a cada final de fase, somatima, no final de curso, tá, certo, então tem de certo, beleza, olha só isso, uma aqui vai usar te usar pra compor, a minha nota de cada fase, beleza, beleza, vamos lá, desafio, qual que eu desafio aqui, uma voce na mecânica de mediportes, especializar em uma notência onde veículos, tem enfrentado desafios para expandir seus serviços com qualidade e eficiência, atualmente o processo de atendimento, diagnóstico, as execuições de serviços, entrega dos seus efeitos de forma desorganizada, utilizando atações manuales planilhas, o que gera alguns problemas, como, erro na priorização dos atendimentos, falhos no controle de peças em sul, dificuldade em acompanhar os status dos serviços, perda de histórico, de clientes e veículos, ineficiência no fluxo de orçamentos e autorizações, gente disso aficina decidiu investir em um sistema integrado de atendimento e a execução dos serviços, de serviços, que permitirá aos clientes acompanhar em tempo real, o andamento dos serviços, autorizar repar os atacionais de aplicativo e garantir uma gestão internaficiente e segura. Qual que a proposta? Então, nossa proposta quer desenvolver a primeira versão na MPP do back-end do sistema do ofcina. com foco na gestão de audience de serviço, cliente e peças, aplicando o Domain Drive in design, ddd e garantir nas boas práticas aqui de qualidade e desenvolvimento no geral do software, tá, e segurança. Beleza, quais são as funcionalidades obrigatórias aqui? Então, tudo que eu passar aqui de funcionalidade é o que vai entrar de fato na valhação da nota, vai compor a alhação à nota. A professora posso fazer mais do que estar aqui pode, legal, a gente avaria, eu avaria, eu passo no feedback que sempre eu usei diferenciais do projeto, mas, para contar que desalo a nota, para ser justo com todos, vai entrar somente do que está aqui, tal que a gente está pídido com obrigatório. Então, a fluxo de principais, criação da OS, da ordem de serviço, então, o que vai ter na criação da ordem de serviço? A edificação do cliente, por ser pédio, ser pédio, cada sort de veículo, claca, marca, modeliano, inclusive, solicitados, da um troca de olha, né, a minha almento, o frede por de reparo ali, porque é tipo de serviço. Possibilidade de incluir peças em sumos necessários, orçamento da cirurgia do automaticamente com base no serviço e peças necessários para fazer aqui da atendimento, o invíulador somente ao cliente para a provação. Temos outra fluxo que há acompanhamento da OS, então, estamos queriam um fluxo ali, através de uma peripla, a gente pode fazer o acompanhamento dos Estados da OS e dá uma mudança de estatus também, né? Então, esses são os Estados aqui, sempre tem a dúvida para o peço, possa adicionar estatos amais, possa criar estatos diferentes, pode, desde que você entra aí os Estados que estamos pedindo aqui, tá? E isso você quer criar mais fluxos que é uma oficina, um pouco mais robustas com mais estatos, né? A pode também, tá? Tranquilo, mas atentes, sempre, é o que está sendo solicitado em desafio. É, alteração automática dos Estados conforme a sua ex-systema, tá, então, quando eu, já vou carrer lá para o mecânea, que o entriendo diagnóstico, quando o mando o orçamento ali, que foi gerado automaticamente para a provação, o DNA, a OS tem que mudar para estar a gravar na provação. E, enfim, essa mudança de estatus automática baseada, com o Fionando, né? A LLS, e permite ter consulta por parte do cliente viafeir para acompanhar o seu preço. Então, como eu falei, não é uma peia que vai explorar de o Estado de OS, por o acompanhamento do cliente, tá? E agora, toda a parte de gestão administrativa também, que é um fluxo que a gente tá pedindo. Crou de disclinhe, escruz de diferentes, escruz de serviços, crou de piezas em sumos, com controle de... do stock, estágem, detalhamento de ordens de serviço, monitoramento do tempo médio, dias de circunção dos serviços. Então, quando a gente fala que crude de peças insubos com controle de stock, significa que as peças utilizadas aqui para o atendimento da OS, deverão ser baixadas ali de stock. Então, a gente perde aqui no desafio que seja feita, assim, a integração entre o fluxo da OS com o sistema de esteão de stock, que está de controle de stock. O monitoramento de tempo médio de execução dos serviços, então, também aqui, não está explícito, não desafia, mas é uma peita, é uma rota ali, onde pode que vai tornar monitoramento de monitorinal tempo médio de execução dos serviços. Então, a nível geral, todo o inferno. Isso seria somente do estado de execução ou da entrada da OS até entrega. Não, da execução dos serviços, isso é uma execução, né? É, isso é isso. Tem que demorar o que demorou para ser realizado de fato ou serviço aqui, né? Que você é excitado. Ou o monitoramento, isso é uma coisa mais uma pergunta. Mas não é o tempo da requisição, né? Visam de, sei lá, mil e seguros que por feita requisição, não chega nesse merito, né? É, você vai ter que colocar ali, né? Um time tempo de quando ela entrou de fato nessa necessidade, e quando ela mudou de estratos, né? Tá, você vê, né? Então, a gente pode ser até mil e seguros, mas não que isso seja necessário, né? Para a gente monitorar, porque é um tempo médio, tá? Berenzo, vai dar. É, vou aproveitar que tão pobre aqui, é, não, mas deixa eu terminar só que o fluxo, segurança e qualidade, que já entra meio que, pouco de requisito técnico também, mas implementação de autenticação de autadabilter para a Peiza administrativas, tá? Então, todo esse acesso aqui de crúdito, do mais, tem que ter a tenta de cação, validação dos dados sensíveis, então CPF, CNPJ, o atrapalho de veículo, e testes unitários e de integração para os principais fluxos, tá? Pensei pais fluxos, são esses fluxos aqui, principais mesmas, então, criação de alguém de serviço, acompanhamento, e essa parte de gestão de mensativa, todos isso daqui, são os fluxos que a gente posile a principais, tá? Beleza, aqui, validação dos dados sensíveis também, CPF, CNPJ, uma dica até para vocês aqui, faça a validação real, tá? Não só de tamanho de campo, tem aí... eu teca disponíveis de monte para toda linguagem, que a gente consegue validar a paca, me acusou, placa normal, no formato antigo, você percebe que é joa, então uma dica que para vocês, tá? E aí eu vou abrir agora antes de que a gente cai para o exito o técnico para receber em alguma dúvida em relação aos fluxos aqui. Só se eu me reenistar aqui. Geralmente a gente tem que extrair um pouco, que geralmente eu tenho uma vez que tem ofcina e ele entrega para o cliente ao a S com a necessidade de compra da peça x, por exemplo, com x-coz, de eletrólica, elétrica do carro, ele não vai ter em stock, então a peça de 3 mil reais. Então ele vai neutralizada, passou de somente para o cliente e autoriza e ele vai lá e compra. Pra esse caso a gente tem que fazer uma protatima mais perto do real, por exemplo, filtro de olhos, essas coisas, hoje a gente abstrair tem uma mecânica mais simples de manutenção de coisas que normalmente moficina teriam no stock e tem que esperar, assim. É assim, o que esperar, o mateix que você falou, tá? Então a abstrair esse tipo de coisa para a gente não deixar muito robusto o sistema porém tem vários grupos aqui de outras turmas, tá aqui, entregam algo parecido com que você falou, tá? Então não tem a ter aí, se pode ter algo para a gente compra, é isso? E o cliente joga como um conhecedor que teria só aí. Não pode ter o sistema extremisté, não deixamos uma terila de compa, tá? E o que? Uma outra pergunta que não é ali é seria a válida, a gente restringir e inventar um pouco, por exemplo, a minha oficina é a preparação de Honda Civic. Aí eu consigo fazer um negócio mais maneiro, só que só com as peças daquele carros, peças, ou com coisa assim. Cara, por exemplo, a gente assim é bem aberto, uma terza ou desafio, assim, desde que uma forma entregue esses equipos mínimos, tá? Sim. Agora, ideias, cara, pode ter de monte aí. A gente não vai avaliar esse tipo de distlação, tá? A gente vai avaliar, de fato se eu sei que os mínimos aqui dos fluxos estão falando desenvolver, eles enfim foram amapiados ali, não todo fluxo ali, no Jagamas, você não mudar, domenistas terem, é vim store, muito do marista. Bem, gente, mas a gente pode ser um pouco creativo assim, no caso de preta-lo. E eu olho, tá? Antes assim, vocês vão ver no final de na coisa, só dar uma dada de vocês, que eu não fico de ver que bem legal assim, bem dividido ao mesmo pra cara através, tá? A manhã. Bem, inclusive, a gente vai participar no momento, também, o procanei, né, é sobre o projeto que está sendo feito. E vai estar a mente. Isso não é só no final, né, que a gente entra. Isso aí. É, tem algumas perguntas... que eu, Mateus, para responder, é para a primeira pergunta do Lucas, como deve ser essa questão de envio da S para a proporcismo seria vieim, meio? É assim, vieim, é mais como a gente não tá pedindo, a gente não tá especificando, né, como se é, então pode ser de forma mocado a mesma, tá? Espar ali, um, sei lá, eu não serviria um método mesmo de envio de orçamento e tem uma resposta de orçamento enviada, guardando a provação em mudos Estados da OS e boa, esse passo. Agora, se quiser criar, aí eu te desalgo uma ferramenta que é isso, para fazer envio de meio de fato, beleza, hoje boa, tá? Mas pode mocar mesmo, se cara, outra Austática da OS e seguim frente, como se tivesse enviado. Pois até declarar que vai que a ideia do envio de alguma ferramenta na documentação, mas no código, se não precisa fazer, é um mídi, essa é isso. É, tem mais algumas perguntas aqui, na Tolivera, ela fez quatro perguntas no contexto sóta, a documentação de poder ser entregue, marquidá-lo, com de agramas, uma emmede, dentro do reposo-tório, ou é obrigatório, ou seja, não é uma ferramenta visor com o o primeiro. Comendo fortemente, ferramenta visor com o muniro, tá? Para donizar 99% dos grupos, sentré-go, um comirro, vira marquidá-lo, um pouco mais chato, até para a gente, avaliar, já teve a gente já mandou aqui da última turma, marquidá-lo, um zalmelo tudo, por escrito mesmo, aí falar para o preústalo, uma ferramenta aqui, para subir uma quidá-la, um traígê-la aos diagramas, faz de amir o que atende bem, tá? Facelita aqui a coissão, a visualização do projeto também, com todo o padrão, e isso aqui é grande parte, é grande maioria, mesmo, um traígano, um miro, um filme maravilhoso, tá? Não, não. Qual o peso relativo entre DVD, código e testes? Depois eu posso até mostrar para vocês aqui, no final abre que eu vou que sê, o depente do da distribução de peso, tá? Então, é o mesmo peso, pra cada... vamos dizer assim, cada disciplina, tá? Então, a gente tem teste, tem o evento storme, é do evento start-telling, tem o peso pra lhe doaker, não vou lembrar exatamente tudo que tem ali, agora, Eu tenho a partir de segurança também, então que a gente está pedindo aqui. Eu tenho o tagu Excel aqui, eu mostrei vocês no final. Então tudo que vai ser... Está sendo aliado, peso, os iticetas, e estiver exposto no final, então né? Acho que a gente tem uma tenda para debutter, tem as rotas também, se todas as rotas. aqui pedia as correntrais, então a gente assim está. Ela pergunta o atático na entrega do vídeo com as pontos que a gente vai valiar, isso está naquele que se luta mesmo, ou seja, o que será analisado na entrega do vídeo, com as pontos precisamos usar a gente car. O vídeo a gente não vai... Eu vou falar aqui para a gente para baixo que estão de entregáveis. Deixa eu dar ele para baixo e eu falo um pouco de entregáveis. Vamos ver o que é? Vamos para o próximo. No vídeo, a nível de projeto mesmo, esses extras podem acabar ajudando demais. Olha, que essa é o que eles se anem a mais pode ajudar nas próximas fase do projeto, tipo, se nós próximas fase do curso, vai reutilizar um coisa do projeto, acho que é isso. Vai reutilizar, basicamente, tudo do projeto. Então, você vai usar o mesmo projeto e você vai começar a reutilizar, por uma vez, levar ele para uma outra que é a captura, já aprende a clinhar que ela nasse uma fase, então vai sair daqui de um MVC, e a MVC dependente que você pode fazer um monolítico para um clinhar, que já começamos a usar a minha televisão. A gente vai evoluir esse projeto ao curso. O que é a outra, como umas dúvidas aqui em relação à especialista de negócio, as regras de negócios, vocês vão ter que inferir, se eles vão ter alguém que vai ajudar, vai ter um especialista, que é a gente que só que vai ser muito na base da inferência, né? Isso, é isso mesmo, pessoal. Ou você escolha comendo o grupo de vocês para fazer esse papel, você vai dizer, ver, é um ali entre si, mas não tem ninguém de esténio, mas não vai fazer esse papel que para vocês, o grupo, mesmo. Ou vocês chamam o amigo do nosso, uma das pessoas que perguntam aqui, que tem uma oficina, pode tovesse-se via de especialista de negócios. Quando vai ter um alfacero, não estou tendo que dá, não faz de fúdio assim, não se tem uma de todos, tem assim de pedida, tem uma galera que é assim, em esta hora, não é? Pide, eu opinionia, não tá, mas não precisa chegar nesse nível, mas não precisa, é, fica um taje também. Tá. Não, não, você já pode inferir também, não, mas só a gente, cada uma está entrevista, com alguém sobre o domino de negócio. Monitualmente então não é para ser feito com alguma PM tipo da Tador, etc. alguém está perguntando sobre a partir de observabilidade. Ainda não está sendo exigido, tá, porque não tem essa disciplina ainda na fase 1. Nas próximas fases, vai entrar essa disciplina e vai ser cobrado. Bom, né? Ah, existe uma uma específica de troca de status. A ordem vai acabar sendo parecida com essa, e isso é muito disso. Tem uma ordem padrão de que este é o rurvico, entre diagnóstico, mando orçamento, para o vou executar na desintrague. Pode fazer tensa ecução, de repente sei lá, permitando um pouco foi testável, que não funcionou, entre a diagnóstico de novo, para a aprovação, pode voltar para os Estados Unidos, e depois, mas a ordem me quer essa, tá? Alguém é que pergunte por rejects, eu não sei acho que foi na pergunta. Tem bastante pergunta aqui, vou melhorar o pouco mais para a frente, depois, porque isso não vai conseguir continuar. Muito vergonha, ainda. O jogo toca a movem antada, sei que você dá por rejects, eu acho que foi para a placa. É, isso não é? O que é com a doida só? Ainda a questão que tem que ter os diagramas do DDD. Quais diagramas, altamente, teriam os diagramas de domínio, os dicionários, de linguagem o bico, e o mapa de contexto, teriam mais alguma coisa, além desses três que eu conseguiri uma piac. Do mesmo enquície, é bem de storming, e o legal da vento storming, é vocês, mapiando conforme as alas lifapassando, e aí, todos os fluxos ali até chegar na modelo e de agregados, o dicionário? E é isso, cara? A gente deu muita想. A gente deu isso, tá? Me leso. Brenham. Eu vou falar lá embaixo sobre as entregas. Acho. Tá? Vamos finalizar só que o fluxo, o total tenta no liar de ouvido, e desque para este lugar, mas alguma doida é relação ao fluxo aqui, para a incitais, e o que eu perai. Aqui, mais uma da vitória, que ela falou, não ficou muito claro, só do tempo, meia de desicustão de serviço. Isso tinha o tempo, quando alas se abriu, quando foi concluída, e o antipónde devre returnar para o S, o porto dos as o S. Quando a de fato o serviço entre uma execução, então, quando vai aprovado, pelo cliente al S, quando a execução, até ela se finalizada, o serviço. É, é... É, a verdade, gente. Aqui, arpedindo, e tal, é só pra fazer aqui uma coisa que eu falei assim. Porque eu lembrei que foi uma doida que gerou na outra turma, e que a gente seguiu de uma outra forma, porque ele não tá pedindo, porque ele pode ter mais um serviço, né. Aqui, ele tá falando numa, no uma, de uma grandeza de O S, tá, se me enque perai, deixou, deixou, deixou, deixou, deixou de novo aqui. Partido que a gente queria ter, para comprar, aqui. Choro como a outra trauma é, ela é, morrer turamente como eles são dos serviços. Vamos manter a própria no serviços, mesmo. Por mais que ele tenha mais de um serviço, a trilada OS, a gente consegue monitorar as opções de serviços. Então, vamos colocar como se aqui, igual eu está falando no documento. E é geral, não é por OS. Então, todos os serviços da minha oficina, todos os serviços sem contar a lisa OS. No geral mesmo. O que eu tenho por meio de eu pensar, o quanto meu aficina está levando para executar a linda e media, o serviço, você está. No geral de todos os clientes. Vitorialmente, eu vou dizer aqui o noteus. Qual o seu limite da sala mesmo? Três entes. Tá, não, eu estou no vete, engino. Tá bem. É, tá tentando deixar um pouquinho mais clara minha dúvida. Então, assim, você tinha citado que, pra esse monitoramento, a gente me eviteria um em-spóente. Então, eu queria entender se é um em-spóente em que um empúte seria o ID de uma OS. E o retorno é o tempo médio daquela ONI, com esse. Ou eu tenho que pegar todas as onesas que eu tenho no meu banco, ou ver ali qualquer o tempo que demora para concluir e calcular a média. Então é nesse... Então, todos os serviços, todos os serviços, todos os serviços que foram executados, que pode ser, por exemplo, troca de óleo e aliamento, bem, dentro de uma OS. Então, cada vez pode ter um ou mais serviços. E aí você quer saber o tempo médio de cada serviço? É. Tá, todos os clientes. Não, não clençado. E isso, na real você não vai nem poder se basear pelo estado da OS. Assim, se você vai ter um estado de execução de serviço, porque um serviço, uma OS, como está aqui, não é que os ones serviços estão, pode ter um ou mais serviços. E para eu finalizar o OS, eu tenho que estar confluído todos os serviços, que fazem parte daquela OS. É, é que pensando em um capenho de tequete médio, do cara, a tempo... Essa é... Quer dizer, curta tal, pensando nesse capenho. Obrigado. Imagina. O agaséuera deixa eu ver que estima, é alguma coisa para gente passar. Sou pra entrar, eu vou falar lá embaixo. Tá, pessoal. Eu tô filhando nós do vés de entrega. para a venta, isso aí, beleza? A sua boa passada aqui, e se a gente vai fazer uma dúvida, eu peguei numa x aqui, eu vou responder no ta, aí quando a gente vai, como eu te passo de final. Tem uma dúvida sobre... Eu vou ver no chat, ele vai sobre a validação, você não é peijota, você preferfe e, basicamente, a gente é disto verificador, ou é o calco mesmo, e vou ver com o suto de uma peio para... Não, você é o calco, você é o calco, só o calco. Ah, beleza. Contressão status, isso aí é um indipóente mesmo, que é a tranção status, pode ser um indipóente. Um relação ao CPF, e a gente olhe vai em conta ao fenômeno é o que já, um epto. É sempre pejorado a você é pejota, né? É isso. Ah, é verdade, é verdade, é verdade. Pode colocar já. Ah, sim, eu... Todo mundo da actória. Tá, né, morrendo, então. Obrigado. Eu sou o Brecassão. Isso. Ah, tem... Ele pode ser um nólito, vai, PC. Eu vou falar, na frente, tá? Ainda vai. Tá, vou. Bom, vamos passar, vou responder a última aqui, que é a última, a JTW, a Penas Paras Rata das Administrativas, toda só administrativa, tá? Então, só essas luta aqui dentro de gestão administrativa, eu preciso de autênticação para acessar. Bom, galera, vamos seguir aqui. time to the final, a gente te enxerte ou essas tás tubulas. É requisitos técnicos, daqui a gente monolítico. Tá, então, por que a gente perde um back end monolítico? Porque a gente vai evoluir um a frente pra me encrocer isso. Então, não vamos plar as etapas, o pouquinho que não olorítico, mais básico. Pra depois a gente ter como evoluir, enfim, aplicado que a gente for aprendendo a loucura, tá? Aqui a gente pode usar uma que ter túrne camadas, tá? Não precisa, eu não sei usar o Instagram, não. O cliente, aqui, nada do tipo, porque também, como eu falei, a ideia evoluir isso, cara, nas próximas fases, a pude, são muito ferem, tem a arte, já quero sair fazendo aqui. Tudo bem, não tem problema, tá? É, escolha do banco de dados ali, mas é necessário de ficar na preferência do banco de risado, então, a documentação de vocês é importante de justificar aqui a escolha do banco, tá? Porque não sei, com o trico, enfim. É, a Pei Reste documentada os viaço-sleger, o similar, então tem gente que vai suerga, tem gente que... de a coletron, pouestima, tem essas formas de vocês apresentarem. Doctrfaiu para a biodad aplicação, toca a composição para o que estrar o ambiente completo, testes automatizados com o cobertura mínima de 80% nos domínios críticos, configuração para execução local simples, está com o regime alí explicativo de como a gente subir seu ambiente aqui, porque a gente tem que subir para testar, validar, e tudo mais. E organização em repositorio privado com acesso ao usuário solte, arquitecture. Então, repositorio guit, rub, que é onde temos nossos vários que da fiapi, esses usuários aqui, não subam o meu trorepo, e em outra ferramenta subando guit, rub, tá? Repositorio privado, sempre, e aí libera esse acesso aqui. Tem gente acabar construindo esse repositorio muito antes da lata de entrega, é isso de se estão acessos, a gente acaba perdendo, perdendo ali a aprovação da cessa, aí a gente entra em contato com vocês para pedir de novo. Então, aqui é muito importante focar do acerfaiu, do acercomposito, para a gente conseguir de fato subiromente forma simples, sempre usar confurar a nossa máquina, então, saldo o que eu comproziar pela isso, bionhamente, para a gente validar, tá bom? O ISEU, os entregáveis de fato, que a gente tem que entregar. Então, um vídeo de até 15 minutos demonstrando todos os pontos, pode ser no grupo individual, então, pode ser ele tudo mundo falando, pouco cada, não falam pouco, olha o tempo, ou uma pessoa só gravar também, não tem problema. O vídeo não vai entrar, a gente não vai avaliar de fato conteúdo, gente, não vai ter uma avaliação específica com uma nota com peso do conteúdo. É importante fazer entregar o vídeo, com a explicação, mas a gente não vai dar uma nota se foi bom, se foi o vídeo, tá, mas é legal fazer, é legal não, é importante fazer que ele contabiliza ali para a nota, o vídeo entrega ali também, não vai fazer um vídeo de um minuto, esse minuto está explicando de fato ele funcionamento, então, é a avalia um míro, explico um pouquinho como que vocês desenvolveram o fluxo, entra um pouquinho ali na do código, detalhe um pouco ali que foi criado dentro do código, se quiser também mostrar ali um suéguer ou poste, não é ali funcionando, fazendo uma estejeamar dos de ATI, para demonstrar um pouco de funcionamento de sistema, é legal, tá? Aqui é mais pra vocês praticarem, ter essa prática de apresentarem, de criar em conteúdo, a gente força bastante, e assim agora, ter essa cultura disso, tá? Do comentação de LED, então o míro é equivalente, eu vi o que falando aqui que nas aulas estão usando uma... outra ferramenta de pra fazer o domingo de storytelling. Pode usar também outra ferramenta pra fazer a boa pra fazer o domingo de storytelling, tem ali os bonequinhos e tudo mais. Mas pra vinguar-me, que é basicamente os postitizinhos, ali os quadradinhos, o miro atendem muito bem. E eu que eu falei, né, tudo dado com a intenção, olha aqui, que vocês aprenderem ali na durante o curso. Então, domingo de storytelling, eu vingue de storme completos ali dos fluxos, de gestão e acompanhamento de OS, e gestão de peças em suos. Então, toda parte ali, de gestão de mistrativa não precisa. Eu de fato vim de sair a minha sopa, a criação é acompanhendo de OS e a gestão de peças em suos. Os diagramas no formo foi apresentado nas alas, e a linguagem língua língua língua língua língua língua aplicada. Então, o adicionário da linguagem também. Tipo, nele, pode ser tudo numiro também, ali, tá. Queria um padradinho aridorado, funcionário, ou pode também entrar em ele na própria documentação o Ídimento do Projeto, enfim. Código Fonte no repostoório privado, incluindo as APIs conforme os requisitos, do Acervário e do Acervo com poucos configurados. Ídimento completa com extracções de uso, tá, de como a gente subia, eu falei, explicando um pouco sobre o projeto de OS e, como que a gente hoda aí, daqui a bem, de local. Segurança, relatório com análise de vulnerabilidades, tá. Articionar no relatório, no relatório, análise de escândia. E eles adunam o código aqui. São dois relatórios. Um é o sonar, tá, que a gente consegue ali, em identificar... é... com a estrutura, poucos qualidade de código de fato, do código ali, em momento de build, ali, de pipeline. E afirmamente aqui, se eu não me engano, eles mostram na aula, o asp, acho que é a Zap, o asp, são no mingueno, tá. Que a artefata, uma afirmamente, que vai gerar um relatório, de vulnerabilidade, em tempo de execução, tá. Então, de execução, faz alguns testes ali, de vulnerabilidade, ele vai gerar um relatório. E aí, também, pode anexacer esse relatório, tirar o printinho, enfim. O sonar, eu sei que no modelo aqui, gratuito, ele não, não, não, a gente não consegue também exportar um relatório. Então, pode tirar um printinho, daquela principal mostrando ali, a cobertura, o que foi encontrado de vulnerabilidade, o codez-me, oito do mais que ele mostra ali, e pode acordar ali também, do meu domingo, de entrega. E eu, e a análise de vulnerabilidade, também pode anexar e voltar. E o que que de fato a gente precisa entregar? O que que vocês vão subir, fazer um pilô de no... na plataforma? um PDF. Nada mais com PDF, gente. A gente que coloca um monte de coisa lá, mas foca num PDF, que cria um arquivo e desse arquivo. Novo do grupo, participantes de os hernames no Discord. Isso é muito importante, o nome de vocês para Rm e os hernames no Discord. Vamos falar em muitas vezes, a gente não aprova o apoio de vocês a tempo. Muitas vezes vocês sacamos subindo, pois errada, vocês não percebem, a gente percebe bem na hora de corrigir. E às vezes falta pouco tempo para a gente entregar a nota para vocês. Isso é uma sedita de mais gente contra vocês, tá? Porque pelo nome é difícil encontrar, né? Tem alguns apelidos aqui, nêmios no Discord, um pouco diferente do nome. O Xiaomi nos animatadores de porcos estão sempre presentes, e aí fica é complicado a gente achar vocês. É... Límica da documentação no PDF, link do repositorio, relatório com análise de vulnerabilidade contra vocês, no sistema como eu falei. Tá? Essa entrega, então link do míro, ou no relacionado com o míro, link do rap do Github, relatório, ali, print do sonar, com o relatório da ferramenta, o astuzap, fica perfeito, tá pessoal. Agora, bora para as dúvidas que devem ser gostas. Eu já me ajuda aí no chat, por favor. E quem quiser... Mas eu vou deixar vocês responder essa. O Júrho perguntou se vai ter algum tipo de validação sobre o uso de R, para o desenvolvimento do projeto, você é a posição o deve em uma domanda de vi. É... não, vai ter, tá? Sim. Claro, e falando sem ser uma mente, vai vocês. Eu apoio o uso, em sentido o uso, acho que grande parte de vocês, acompanhe em cenário, viu como que tá... Não estão vendo como que tá mudando o cenário do desenvolvimento, só que eu realmente hoje quem não está usando a IAR, é para a Flico. Tá? Ficando para trás. Então, eu me senti a uso, mas como que é um curso, você não tem que para aprender. Ozinho é aquele método de questionar ela de entender porque ela está fazendo atividades coisas, não só de fato, por exemplo, porque ela vem entregar hoje, que não clau de vida, mas então, se você jogar a espedda e afelar, ela vai te entregar esse pronto. E muito bem feito, a nível de código, tá? Então... Mas aqui é um tempo de vocês, de aprendizado, é importante a gente conhecer ainda, a que é de software e tudo que ela traz junto com ela. Então, ozinho no modo crítico, tá? Então, desenvolva-me perguntar por que está acontecendo, questionem-se, ela e aprende junto com ela, tá? Então, o da uma exemplo de uso aqui já respondendo algumas das perguntas que foram feitas lá atrás. E a pessoa também preocupado com um cara, como é que você não conhei? Se você não tem o especialista de domingo, como é que eu vou fazer a parte de domingo? Cara, vocês podem pedir para o prochat de PT, se eu, se eu especialista de domingo, se pode falar cara, agora você é um domingo de uma mecânica x. Se você vai se comportar como domingo da mecânica, eu vou te entrevistar para me fazer a minha parte de domingo aqui, minha parte de TDD. E ele vai se comportar como um, claro que não é real, é simulado. Mas ele vai trazer algum contexto que tem na sociedade que você pode usar na sua aplicação. Eu também sou um cara que apoia muito aí, porque hoje estou num projeto grande de um banco, que usa aí para fazer exatamente quem está fazendo aqui. Então, não faz sentido você não saberia usar e a desprejuda vocês. Agora estou a me cuidado para não entrar em toda a inteligência para irá. Ela é o parceiro de vocês que vai ajudar, não resolver que vocês precisam aprender. Mas vai ajudar vocês, que deveriam ir mais rápido, criar em coisas que precisam ser muito inferidas, criar em transestuações fora, mas não pegue em ela para fazer gerar todos os diagramas de vocês. Não pegue em ela, para gerar todo a documentação. Se vocês estão perdendo seu tempo, eu vai acabar ficando meio com o tempo perdido e vocês. Agora, por exemplo, postete os automatizados. Isso aqui é algo que... Eu não preciso gastar o tempo de vocês, é um mente. E vai gerar isso. Claro, com o desenvolvimento bem feito, mas ela vai gerar isso que te falar é perfeito. É um cenário que não vale gastar tempo, mas hoje em dia. A gente tem que ter pessoas no curso, pelo que a primeira apresentação que podem inclusive não ser desenvolvedores, da área de produto. Então, para essas pessoas, por exemplo, a primeira vez vocês vão tocando um doaker file, provavelmente, não toca com o pose, quem é desse público. Claro, não recomendo usar e a a primeira vez, a pesa quem não está numa terra. A gente vai ter lá na frente. Então, pode usar, mas quando entrar em coisas que vocês precisam aprender, deixei a fazer. Fala-se você, conhecimento pra você. Agora é coisa que puta fora do todo o minho, eu to escou e você não vai usar. Por que você permite ter um tempo realmente? Até falando do doc, é uma disciplina que estava na primeira fase. Ela saiu porém, continuou aqui a disciplina no desafio. Porém, não sei qual se não, então, com a audiência, mas vai ter uma live só pra falar de docker. Já tem panega do uma live aqui de docker, onde vai cair as melhores práticas aqui, ele vou professor ver a apresentação de uma boa. É do doluço. É do doluço. Eu não quero ajudar vocês aqui, tá? Bom, essa live vai ser antes depois da entrega, a primeira fase. Antes antes, vai bem antes. É dia 2 de abril conferir. 2 de abril abola, então. No meu Deus. De dias. Lá-te. É só relatório de vulnerabilidad, não fazemos os fixos, não está fazia também. Sim, recomendo fazer o fix, mas se quiser deixar o relatório lá e fazer um breve discritivo ali do... das possíveis soluções ali pra que ele cenários, show de bola também. Tá, mas pela... pelo tipo de entrega que a gente está pedindo, pelo conteúdo aqui, não acaba gerando tanto tipo de vulnerabilidade, tipo, não tinha muita comunicação, tripe a ligação para o meu lado estérelo, enfim. Acredito que seja um pouco os ítimes poucos apontamentos de vulnerabilidade. É, pelo maior outra pergunta aqui, que é do dia, ele perna, eu estou seria precisar da siga, ou dos scripts de banco, para a gente poder testar que o dia foi fazer provavelmente uma sociedade, este bico. Cara, ajuda muito. Quando eu ir assim rodou comprozo, ele já só abitud, tá? Se migrei, chancide, tudo aqui, precisa banco, migrei, chancide, tudo, tá? Facilita e nossa vida, a gente vai estar dando um nota para o mesmo limite. Já vou ter que aprender assim, blaipo, que eu estava vendo, pessoal falando, então... Palape. O dia agramas aqui pode confirmar a entrega de DVD, domença de terni. do Ministro de Télix e de agrima-se e vem de storme. Tudo o que está gastando a 20 storme. Ainda desde o início ali do diagrama reventos, frente no Deus e reventos, vai adicionando os componentes, ali atores, até chegando o modelo de definho contexto delimitado, até chegando o modelo de agregado. Então, um primeiro, vamos evoluir? É, vamos ter o skyo. É, não, é o volume. Acho que ele é dizer para vocês irém evoluindo o desenho no mirror. Avolna a poza, né? Não é. O último aqui para ver o que ele, onde que ele pregou essa? Conei o graçado para o uso dele. Mas aí pode fazer todos os discussos, né? Por favor, né? Eu for o que isso de criação de serviço, acompanhamento da que é o caso da Pina de Macta, a criação se foi aprovado e aí vai seguir no número. Sim. Uma teus cariu, que eu vou acreditar voltando, mas sim, queria que a tudo aquilo que tiver no... como eu recisito ali tá num deixar... nada de fora é importante que o que tá descritando, documenta esteja na entrega de vocês, tá? É a primeira... É assim, vocês não têm um pior, né, que foi eu. Uma das perguntas aí, hein, mas... eu documento ali, já é uma descrição bacana, vamos nos escomensar, vamos nos em ele, ele tem que ser o... meu que eu... a história dos que se pregava, eles definem chofe, o rei. Foi fora. É, professor, sobre a questão e da auto-tonticação. Eu imagino assim que como não tá sendo pedido cada dia os usuários e tudo mais, né? A gente vai usar apenas para proteger aliás rotas, mas a gente vai usar um usuário interno, em mesmo. É, se vocês podem imocar o... a parte do usuário, tá, vocês não necessariamente precisam implementar tudo, tá? Você é acredito que você é imocado aí alguma coisa, outra. É, seria interessante você descrever tá na arquitetura qual... como você tá fazendo... como você planejaria fazer a auto-inticação, como vai ser um micro-servista que se vai desenvolver, vai ser um... um teclo, clavido, a receão... entendeu? A gente pode te conecte portanto e cheio, já aí, mesmo que a gente não coloca na nossa... no nosso desenvolvimento, já tudo, o que a gente marca e como é esse cara tá bom cajoso. Esse carão de um tal é interessante que se fosse uma vez em esteja. Acho que uma vez voltou aí. Até eu sou um pouco. Agora ele vai evoluir. É. Você pegou o meu evoluir como assim, não? Foi um irassado. Ah, só vou evoluir. Cabou a luz, carincaz, tá? Mas já tentando troçar o armazoto. Não. É. E evoluindo o event storming, né? Então eu achei a falar aqui aos fluxos, né? O exude evento e aí a gente vai incorporando ele, né? Colocando as políticas, os atores, definidos atores, que abrem com textos delimitados, até chegar no modelo de agregado. Então, é. Vou um demonstrando num míro, ali, por exemplo. Aí o poloção dele tá. Não, vou evoluindo o próprio o mesmo de agremo. Demonstra-me todos os dias agramas, caso a paz conforme que vocês podem desenvolver do eure. Então isso é, isso é entrega, mais o domenso de Aristótese, mais adicionário da linguagem o Bipo aqui, tá? Então, é basicamente isso que a gente vai avaliar do fluxo de... de dedeta, da documentação. Não precisa de tregar lá todos os requisitos funcionais da funcionar. É. Se quatro model, é. Esse que te coisa, tá? E aí, perguntaram aqui também, do comentação do D.D.P.C.C. da que a do fluxo de criação de O.S. e eu te posto. Criação acompanhamento de O.S. e controle lá gestão de Stork, partido de Stork. Aqui gestão de peças insúmos, né? Que é o Stork. Bom, lá. Eu uma dúvida aqui que eu achei interessante é que é do... sobre o Dockerfile, né? Eu gostava... eu gostava o colher. É o anero, a river, né? Não sei se está certo, se faltou uma lita. Mas... Ele perguntou se... se... por que que espisão do implementar o Dockerfile? Se você não tem que fazer o Pipe Line no GitHub, de WebEx, os por exemplo, a gente que para primeira fase a intenção é que a gente possa executar, né? Ou uma teus... não são a máquina porém, para... para... para as outras fases até a GitHub, tem que ter a bequeção, mas até o Pipe Line, a gente vai ter como parte na entrega, né? É isso aí, não sei. Nessa era a setapa, do Aquifer Faiu. A verdade do Dockerfile, você vai acabar utilizando... em todas as etapas, é... mesmo... um Pipe Line, tá? Vai subir lá no contém, eu vou dar normalável S, vou precisar dele para subir. É, amplicação. Então, o composo não, aí, eu composo e beleza para a gente subir. que não é uma semana que não é tudo o ambiente completo. Então é isso, essa faz gente não tem papelaine, não sei o me gravar a próxima, já entra. Eu e de serem me ganhando com o que tinha que ir no mesmo, eu não falei um deploy lá na AWS, mas não é essa etapa, não. Só o Docker File e o Compose, que é necessário para a gente subir de forma mais simples, ambiente. Então, conseguiria subir sem o Compose, mas a gente decide que ficar subindo, todos os compôrência parados, tá? O POSFASILITO. É legal. Tem uma do Gabriel, que a gente acha melhor? Eu vou deixar essa pra você, porque você já está eu sou novo aqui na fia, né? A gente executa eles precisam executar o TEC-CHAN de Amigil, que eles vão evoluindo semana semana, eles precisam assistir todas as alas, primeiro, porque depois de a partir do TEC-CHAN, a gente vai ver o que seria melhor pra exerir o projeto durante a primeira fase pra eles. Bom, assim, o tema baladiprata que tem, acho que o certo é errado, mas eu iria num sentido assim, vi toda parte de domínio, todos os alos de domínio, como é isso já, pensado do domínio, é estruturar algo de até mais fresco, mas com essa, é mais fácil de gente aplicar, colocar na prática, algo que está em um de a frente, domínio pronto, feito, se te as alas ali, ou recentemente, elas não são mais técnicas, e aí já aplica, com o começo de fato, o desenvolvimento, enfim, e eu falei dessa forma, mas é acredito que tem pessoas que assistem todas as alas, depois que começam a fazer o desafio, tem gente que deve pular várias nesses de alas, e eles me relacionam a ser entrega o desafio, então assim, vai na forma que vocês se preferem, porque acredito que muitos de vocês também, já estão na área muito tempo, e tem conhecimento aqui, e faz parte de nosso dia a dia, que nem já está muito dos anos na área, então ela ali é mais para lembrar algo conceita, alguma coisa assim, então, bom desenvolvendo o projeto ao longo das alas, eu acho melhor, porque se deixar tudo por final, elas vão acabar surgindo do vida no final, é ficar mais um pouco tempo para desenvolver, e como o RGF falou, eu to aqui, ele está aqui na estabeleidade de vocês, que todos os dias aqui de segunda a sexta, para atirar dúvidas, então aproveita em si tem, para ir desenvolver o projeto, tirando dúvidas, a gente é um longo do curso. É que a gente fez poder fazer grupos de estudos, depois relacionados ao projeto, bom, o que quer? É, pode fazer aqueles grupos de estudos, que assala as pessoas, e pode começar com a isso no projeto, não? Pode, então, como falei, a gente tem os grupos de estudos marcados, que são cada 15 dias, cada 15, é live, é uma semana live, uma semana no futuro. Só que como essa tudo tem muita gente, em um dia de grupo de estudos, por ser difícil, a gente, o mosquito de lado do mundo, a gente está aprofundando muito no tema e fica durante o tempo inteiro do grupo. Então, podemos marcar e fazer, ali, cominar outros grupos, outros grupos, para te adúvidas. Mas recomendo fortemente, a utilização do Discord, nesse modelo assim que, a gente consegue trabalhar bem, responder com mais velocidade, as dúvidas, que ficar esperando o encontro, possível, enquanto, para a gente poder saber as dúvidas. Acho que o grupo é legal para a gente te debatei sobre o meu entê. Estas é o conteúdo diferente, às vezes o conteúdo aplicável, de fato, uma empresa mesmo, de dia a dia, e dúvidas simples de projetos, de entrega, joga em lá no Discord, que a gente responde, na mesma forma. Por uma mais dúvida, eu vou ver, tem de uso do Docker, que está de papel line, não, para ser te papel line, a documentação falei, temos que publicar a peia, bom domínio produtivo, não, só doque, local mesmo. Data de entrega está lá no portal, já do ladinho lá, onde vocês cliquem para abrir o desafio, tem lá data de atrega. Grupo de troma no WhatsApp, não tem, mas, se você se animarem, podemos criar. Tem uma pergunta aqui, do G. A dúvida sobre o dedeno código do projeto. Então, o que eu entende, é que o momento mais naquela parte da estrutura de camadas, né? Se a gente perderia no automão, na hora de, de dependendo da estrutura que ele colocá. Não, não, não, não, não, aqui, assim, como a gente não está aplicando, nenhum tipo de, a que te tura um pouco mais favorável, para a gente aplicar de fato, o DDD, como eu não queria, não é a gonnaão da vida, que ficou um pouco mais, explicito, mas claro. a separação, e tudo mais. A gente não vai cobrar nesse nível de detalhe aqui. O que a gente espera, o mínimo, aqui vocês aplicam, usando a linguagem do que foi apresentado de domínito, mais, no DVD, se levantarem no funcionário, aplicar a mesma linguagem no código. Então, esse é o mínimo que a gente espera aqui, nessa fase, agora, o próximo fase é um pouco diferente. A estrutura que é totalmente aberto mesmo, não tem cobrança em lação da estrutura, de visão de projeto, tudo mais. A última dúvida ainda sobre as validações, sobre a questão de CPF, se ele pejou, tem de que a validação, que vocês falaram, tem de a validação com vocês falaram, mas eu vou estaria de confirmar uma dúvida. No grupo, é necessário alguma validação de relacionado a peir e noito aí. A dúvida do rameito. A dúvida do grupo, necessário confirmar alguma validação nação da peir e, é que se abre o microfonei para o Judo, é um PII professor. PII. PII. A próxima pessoa, acho que tem esse tipo de especialização do... é de sempre da internet, de informações de isso. PII, não é a jucada. Ela foi entendido, não precisa fazer a clipe deografia, é só a validação para o ver se dá batendo por um correto, acho que é a validação de validação básica de ser nepeyota, né? De gente, vale a ficar dor, né? Eu acho que a gente, a gente é verdade, e ele está falando em informações pessoais, tem mais uma vez como a gente bem de ver. Está olhando aqui. É o pessoal de um dia ou bem, agora? Sim, agora ficou melhor. O, o exibô. É, o meu... Não, é, é, sobre isso mesmo, sobre... Informação pessoal, né? De gente passando pessoal e... A explodência de não ter nada a vindo, né? Não, não, não, não, não. Vocês ontem um conteúdo de LGBT, não, ultima fase. É, mas são no migo número de não valentes, nem se aplicar aqui no nosso desenvolvimento de todo o projeto. Na última fase, tem, eu lembro que tem, tem, tem. Mas a gente está verificando se daí. É, deixa eu brigar do pessoal. A notificação é exigida apenas para... A topra é voltando de a viaça, é para o Pantista também. A notificação só para a... administradores ou clientes também, final não teria a identicação. É, não, só para a administradora. Quer a gestão administrativa, né? Eu acho que não tem que ir fácil por que a gente aí, né? Não. É, pode ter uma identicação, tá do cliei. Esculpa, porque a identicação do cliei a gente ali é só a transformação de dados de CPF e mandatina na cimento, por exemplo. É, é, é só em alguma outra informação ou toca de ver que provavelmente é que eu tô grafia, né? Já, já, já, já é de IPD, não. É, tem que passar essa do cliei, que não é identificado pela gente, né? É. Mas isso é a identicação, não é a autenticação, é. Isso é a identicação para o pouco, né? Não, não. Uma propriedade do S, não é para a autenticação do cliei, que pode acessar o sistema. Isso é que é um RP, provavelmente, para uma oficina, certo? É, é um sistema para uma oficina. Não, quem tá usando, eu dou no dofocina. Muito ou notas. É, certo, isso é, gente, eu confirmo muita coisa com ele, porque é o primeiro tétate alimbeu. Olha, eu vou ser como, você que fala aqui de mostrar um PDF, cara, não tem padrão assim, é tipo de PDF, você já mostrar o PDF de uma anterior, você não tem um padrão de PDF, cara, realmente, cada uma faz um jeito. Mas desde que tem, a gente faz gritar, faz um desenho maluca aqui, um leite bonita, uma fosmoficina, né? Mas só precisa ter as informações que a gente está pedindo ali embaixo, sabe? Que são? No nome do grupo participante, link, os links, correto, os e o relatório. Sim, tem do isso, cara. Pode escrever de qualquer ele, pode ser escrita básica mesmo, assim, da nem ter cabeçal da pena no tipo. É importante, que legal pode ir do aia, também. Mateus, só para entender. É, a anendir já tá da blia T, e isso aí, a gente precisa fazer ter algum tipo de criptografia, mas específica, então, dessa questão de dar dos olhos. Não, não, não, não. Fechou. Aqui é só isso mesmo, tá? Eu não lembro agora, se ter alguma outra fase com, que é profundo pouco mais, em, expectantemente, segura, não sei, tá? É, depois da boa vez, eu sei que tem uma tarea de segura, sei mesmo. Não, aqui tem, na fase de fato, um desafio, ele só perde o relatório mesmo de valoridade. Sim. Bom, lá. Não, me preocupando porque eu, o ripo. precisar ser privado. Ah, galera assim, porque este é onde e a gente é de galera no copiar e que fica disponível ao seu projeto, onde tudo mundo está fazendo mesmo projeto. Mas polícia, tá, com a gente vai deixar privado, sobre o libero acesso que é para o usuário que a gente já tem acesso aqui de disponível e boa, tá. Mas para por causa disso, mesmo, para no compartilhar o seu projeto com outros grupos. É para se uma lá empresa real também, né? Prima. Repositores são todos privados. Vai. É, o apelógio do vídeo vai ser no YouTube, né? Pessoal da pergunta. Pode ser Google Drive, YouTube. Ok. O que você preferir, né, tá? Vamos dar o link. É uma coisa também que lembra agora você tostva, mas acesso assim, acesso ao vídeo, acesso ao Google Drive e acesso ao míro, também tem tem deixar liberado assim. A gente gasta muito, tem uma turma muito grande, vai ter muito projeto para coerir. A gente gasta muito tempo e da trás das pessoas a pedindo acesso, sabe? Então, as vezes não tem no Guiit, as vezes tem no Guiit, não tem no míro, então, se a tente assim a liberar todos os bíro, eu dá para deixar publico no final do curso. Ou eu passo no email depois, mas eu estou com essa frente ou eu me peio pessoal aqui, que eu uso para acessar o míro aqui também para vocês liberarem, deixar liberado, tá? Mas você tem que ter os acessos que uma coisa que que é a trabalho é bastante mesmo que a gente tem que ficar com o rindo bastante a trás para pedir os acessos. Se eu vou confirmar, também ideal do grupo, é cinco, né? Se eu não sei o que você é cinco pessoas, o máximo. Ah. E isso. Boa. Amulá, terem. É, tem duas pessoas que eu mesmo não dava aqui da RT. E na sua india uma do código ideal é que seja híbrido, contendo os negócios em português e técnicos em inglês, outro do importo inglês. Por exemplo, o que eu a ver aí, o serve, o Criente, o Cerve, o Cerve. Avar lá na discussão nesta, né? No grupo lá no Discord, tem um topo que eu large a conheço. É, pessoal mandou, lá uma, uns artigos interessantes, é onde tem várias abordagens. Tá assim. Tem a abordagem que diz onde o negócio é do Brasil, é, o Polar de negócio, é do Brasil, o Criente, o Brasil, vamos fazer tudo aqui em português. Porém, que em gosta de programada, de colocar o código inglês, que tem muita gente, nesse cenário, o híbrido cai bem. Então, pessoal está usando bastante o híbrido que é termos técnicos, não é como create, serve, se repositor e enfim, temos como uns as linguagens, inglês e aí o termo do domínio, que de fato importuei-se. Eu acho que eu faço estranho, mas... Veridade aqui no mercado, você pode encontrar empresas que não queram nada em inglês. Você pode encontrar empresas que querem inglês, mesmo sendo toda a brasileira. Você pode encontrar empresas que quer que seja em híbrido então. A gente exige alguma dessas, ou não, uma turista, não. Não, tem um pergunta aqui também sobre os cometos, a gente analisou a cometir. Não, padrante cometir não. Eu acho legal, você seguir em comensão, mas a gente não vai avaliar isso nessa fase. O que a gente vai avaliar é o redimit, então, eu documentação o completa do projeto ali, dentro do redimit. Não é onde eu entro a cada uma, quando eu tô dando a comitação do PDF de entrega, tá? O material, eu vou tirar uma dúvida. Para, o da parte do... Envilador Summit, o cliente para aprovação. Esse peda de forma porque o dessa parte não se faz isso no dinho vir de ninguém, ou no lado mesmo. Como ele tem aqui, é mocado mesmo, tá? Você pode fazer em vídeo meio de fato, mas um moque já serve. O moque se mudando um envio e a alteração do estado do S, para aprovado, enfim, o já entra em execução. Tem gente que coloca os Estados de aprovada, porque nem sempre quando ela aprovada, lá, entre a execução. Então, tem gente que coloca os Estados de aguardando o mecânico disponível, mas não tem mecânico disponível para executar. Então, ela ficando em estado de sada em intermediário, depois ela entra em execução. E fica a que a chefeira de você, está o mínimo a esse, mas essa questão do e-mail pode fazer mocado mesmo, tá? Cada... No outro form, já pode ficar a astral grupo, inclusive o grupo tem que ser cadastrado até 15 dias antes da nata de entrega. Se ele bateu os 15 dias a de padadinha entrega, não é possível, mas mexendo o grupo. Então, fica a que a adica. Se na próxima fase... Você estiver ali a vontade de mudar de grupo, enfim, pra um motivo, no seu grupo, no seu adaptor, o grupo, o sistema até 15 dias antes da nature de traga do desafio pra fazer essa mudança, no sistema sinão, vai finalizar a fase com aquele grupo. Então vai poder fazer outra ação no próximo, não vou as mãos faz. Gente, a minha ação, que vai subir uma hora, não é só o marketing aqui, viu? Eu vou até... Até os. As caras de colocar a linha de grupo do desafio, enfim, imagina, as caras de rei-gáquia. Matheus, vocês estão me ouvindo? Sim. Se tinha mencionado anteriormente uma ferramenta na inducionar pra fazer o escando código, é uma chamazap que, por acaso, é o oasp... Eu acho que é o oaspisap, é a esapia de proxino. Ah, acho, beleza, valeu. Essa é isso, é isso daí mesmo, você falou no equilíbrio do raio. Não vai te atacar o opsi. O que é uma planíria que você falou aqui é mostrar o pessoal o que te leva em consideração? Ah, boa, deixa eu pegar daqui. Porque aqui já tem gente perguntando se vai se avaliar da complexidade, da solução nas hoeses, acho que você lembrau para na questão. Quer saber se avaliado? É, ele percoço, se vai se avaliado com a complexidade. Ó, eu vou falar aqui, tá, mas fácil porque tá até com a valeração de dar outra forma, mas assim. A valeração que tem esquenta para a valeração, que contexto de dd20, storming, storytelling, então tem uma parte de a valeração ali, malota que a gente dá aqui. Dizero a 10 para cada um desses itens, tá? Então eu vou falar os itens, é no final a gente faz uma média desse item, isso para a nota final, tá? Então, meio do devint storming, storytelling, a gente tem uma nota de zero dessa área, que eu acessa com a banda. Tá, tá em branco. O quê? Vou tô? Não, não para a aparece na planíria, agora vou tô. Eu não vai apresentar a planíria, acho que tem dados ali na Judo. É, eu vou falando aqui meu, para aí, deixa eu só mudar de terem tão pelo menos para ficar melhor, olha só para mim. Valei. Deixa eu apresentar, deixa eu só... Não, vai, vou falar aqui, agora, mais fácil. Então eu te que... que a gente planeira. Então, como falei, tá serão variados alguns topicos, uma nota de zero a 10 aqui, para cada topo. Então dentro de a Vint store, mi-solide, a gente tem a increação e a coperia a mente de OS, e a gestão de passas em surros. Então, para cada um desses domínios, você não tem a malaute de zero a 10. Então, se você não faz nada da parte de stock, a gestão de passas em surros, você vai ter a nota da criação e acompanhamento em OS. Você vai ser parada da gestão de passas em surros. Isso, o fecho de dd aqui. E aí, isso em globa, tudo que eu falei lá, o Vint store, o salitele, e o o cenário, e a líquia de obico, tá? Quanto, a parte de código ali, tudo fonte, né? Você pode me lesloar as rotas solicitadas, uma nota de zero a 10. Do a querfile, zero a 10. Building round que é o composo e subindo ali tudo bonitinho ambiente, zero a 10. Rídame do projeto, zero a 10. Segurança, a notificação já pro abrotei aplicada, zero a 10. Validação dos dados, sim, civil. Então, a validação ali, você tem de prejão, você pera, fico, a aca, tem nota também, a análise de vulnerabilidade, estão relatórios ali de vulnerabilidade, zero a 10. E testes automatizados, tá? Unitar em integração, zero a 10. Esse são os tópicos que serão avaliados. E aí, a gente faz a média aqui dessa média, tá? Dá nota final, então, tem alguns tópicos, por exemplo, com fonte ali, né, tem a ver com fonte, aqui são as rotas da querfile, build, round e reach make, são quatro items de avaliação, então, tem um peso maior nesse caso, do que o evento histórico, a gente só tem dois items para contabilizar a média, tá? Teste, por exemplo, é um item só, então, tem um peso menor. Beleza, ó, só uma dica, se aqui, a parte de validação de dados sensíveis, uma coisa super simples de implementar, né, você pera a fita de pejota ali, e placa, tem um peso, tem uma uma fita de zero a 10. Então, algo que vale, um peso importante, e algo simples de implementar, então, não deixe de validar, tem bastante projeto de pessoa não valida. E aí, fica assim, não é. Calma, deu um boa nota para o besteira. Isso aqui é a placa, ela, ela se só não tem uma validação, né. É só uma mais, como caso, o cheio de pejota, se ele peja, ele se peja, ele se peja, ele se peja, ele tem uma validação. Isso aí? Vamos dar uma dica no chat para você, está? É bom. Vamos na fãtana, Mola, e fala mais, dobe desse, dobe escrevei aqui no chat, que é vontade, pessoal. É um homem. e estamos ali. Avar nas dúvidas para acabar os novos novos leis da nós. Opa, um bonoite novamente. Vamos. Só vai se ligar de o blico, a como a gente vai ter basicamente só um serviço. O que a gente vai listar vai ser interior simples, né? Hoje eu não vai ter uma vez uma entidade com dois novos no mesmo. O mesmo serviço, por exemplo. O que a gente não é assim, por que é simples de fato, falando de domínio assim, então, o que não é muito complexo, né? Não vai ter essa conflito, né? Ainda não, né? Mas não. E aí, mas de qualquer forma é importante fazer, tá? A gente deixa ele estar mais só pente desse erro. Não está pegado no final. Não, não. É, uma coisa que ficou ali, eu vou voltar lá atrás, normalmente. Você, eu fui um pouco explicado, mas ainda fiquei só com a ideia na cabeça. É sobre o tempo de execução dos serviços, o que está listo apontuado ali. É a quantidade de serviços, o descrito, não é esse. Eu tô imaginando, ah, tem um marketing serviço que tem 4 tipos de serviço. Uma outra, 6 tipos, né? A gente vai contemplizar a sua soma execução delas, né? De todos os serviços, certo? Fui isso que eu falou? Ah, de cada, oh, esse. Não, de cada ser visto. De cada ser visto. Imagina que você tem um OS, aqui não está super explicando no documento, mas aqui, imagina que eu posso incluir um mais serviço dentro do moécio. Então, eu posso deixar o ponto aqui. Tem os 3 OS, diferente, que tem uma troca de olho. Cada OS tem uma quantidade enxipção z, serviço. Mas então vou calcular essa média de execução, por exemplo, da troca de olho, que é em comum. Ah, troca de olho. Pode detalhar, você pode detalhar nesse nível, onde eu passo um serviço, ligue o me d'altem por resposta daquele serviço. Mas aqui, a binginéria é com o mesmo, tá? Ele quer o tempo, a média é com ser um dos serviços. Então, um geral, enfim, depende de setorca de olho, né, aumenta. O que for? Mas, caso, se quer dizer que você é criar um em de ponte passando, esse parâmetro, o tipo de serviço e ele dá o tempo em área de também chamou de bola. Mas é baseado no serviço, não é esse, tá? Nós serviços solicitados. Isso. Isso. Então assim, tem um... um OS contra a cadeia ali em aumento e um OS com... sei lá, a troca dos pneus. Quanto o qual tem o comercio da troca de aia, da linha a mento e da troca dos pneus? Então você tem que ocular o tempo sem impred execução de cada serviço para poder fazer essa média depois, tá? Ah, então, por exemplo, é uma média gastada no OS inteira, que todos os serviços não ali. De todas as OS espocives, né? Mas então, é daquele ponto que ele que começa execução até o ponto de ficar finalizado, né? É, não por quê? Porque aqui... Aqui é a execução da OS, imagine que eu tenho um OS com um troca de aia aia a mento. E a troca de aia ele fez em uma hora e a aia a aia a mento ele demorou em duas OS. Quando ele termina a troca de aia, a aia a aia, se não vai para finalizar ainda. O meu serviço vai. Sim. E aí é um tempo dele, uma hora. Aia a aia a mento ele demorou duas. Então, já tem o tempo médio de uma hora e meia nesse caso, né? Mas, eu tenho um outro serviço no outro OS que demorou três horas. Aí, eu tenho um de uma hora, um de duas, um de três. Então, eu tenho por merdios duas horas do serviço de todos os serviços equipados da noficina. Então, eu vou fazer um gai de ali, pelo portou dos serviços independente de OS e cáPULOW tem o que não é de resposta, né? Sabe que acartem o que acartem o que acartem o que acartem o que acartem isso e caras que acartem isso e caras que iniciam o que acartem isso e depois vai ter o base para fazer calculta. Muy. Não, eu fico clara. Cara. Com lica que o... Não é uma falar que a gente dá de serviço, né, esperem de se me ajudar de eu não vai ter que ter essas informações, particulares dela, né? Pra gravar. porque se está dizendo que você deixa esse tempo, esse time está em um tempo que eu gastei maiore um serviço, vai acabar afetando o outro, ainda numa impressão é rada, ali na muito... exatamente. E que estava pegadinha então? É, sai, por isso ir a orgica. Entendi ele em qualquer isso vai estar tudo mocado, eu vou ter um serviço para caras estatus. Esse está tudo aqui da OEC está, do serviço a gente não tem nada, o serviço está aqui, tá? O serviço basicamente é executado, então, o serviço de acelizeta é executado. É, vai ter um estado dele ali de interna execução e finalizar. E me isso é executado, finalizar. Vamos dar esse preço aqui no documento, tá? É, a única forma de a gente me dê assim, então, ele aí, então aqui nesse caso, tá? Puma. Puma, galera. Tadando no novioras aqui, na vida, isso, eu vou deixar aqui até lá, vc, com o pra ver se eu não tenho mais alguma dúvida. O material, boa noite, pessoal. É só se clare há um pouco mais minha dúvida aqui em relação aos Estados. Você estava comentando mais cedo que cada estado, saliu, teriam em tempo médio, entre, como se colocar ali execução e finalizado ali na hora de ser visto. É, esse time aí, ele vai ser, a gente vai ter um, vai estar funcionando ali, automaticamente, um com de óbviozinho, ou, ou, ou, ou, a gente vai setar esse enrádico do adjale para poder só chamar, lhe um gétzinho e, como se fosse um histórico deles. É, você queria um antiponte, né, de alteração de, de status, então. Aí eu posso jogar ali a finalizei aquela, e a gente vai ficar com o sal e a cara. Calcular o tempo ali de cada um. Exatamente. Vem de, beleza. Exatamente. É, o vitorei, isso mesmo, tá? Então, a isso vai ficar em execução até finalizar tudo que foi contratado e definindo dentro daquela OS, de ser disso. É, a partir, né, assim, que é que o mecânico ali, então, se finalizar todos os serviços que estão indo a OS, é, vai ser, então, como finalizada. Então, realmente, ponte ali como se fosse o mecânico ali dando conclusão, você diz isso. Isso, isso aí. Então, mas ali diz que a U-Staste que seu alterador automaticamente, então, tipo, não... Eu entendo por esse texto que não vai ter um alcheão de fato, é, não vai ser uma... Não vai ser uma ação de fato ali, tipo, o mecânico ali, o funcionário, ele não vai entrar no sistema e atualizar os Estados. Ele vai fazer alguma coisa que vai atualizar automaticamente. Imagina que eu tenho no serviços, então, você pode implementar, quando eu tiver todos os serviços daquela S finalizado, ele altera automaticamente, o Estados Store de serviço, da O S, para finalizar. Uma que eu sou um patirão do... Por mim, eu tenho que gerar o orçamento. E o orçamento tem que ser aprovado antes, então tem que ter um passo do cliente, ele faz com a das, faz o pedido, aí vai gerar o orçamento, certo? E aí esse orçamento tem que ser aprovado pelo cliente. Depois eu posso dizer que o target de serviço, o que é que faça... Isso, isso aí, então. Eu tenho que ser aprovado, né? Isso, o que é? E aí, assim, como que funciona uma mecânica, não tem um... Você não entram no sistema o cliente, não entram no sistema, prova. Então, geralmente, ele gala, falar, falar, tá tudo certo. Eu vou te fazer, vou levar a mecânica, que fala, pode fazer, tá, provar, assim, lá no comento. E aí, o Becanico vai botar em essa discussão enorme. Por aí, bem, beleza. Tá. Desculcular essa questão aí, abitinho só, só para finalizar essa questão da alteração, o meio do que em que pregunto aqui, da alteração automática de Estados. É só para morrer de certeza. O mecânico vai ter uma funcionalidade onde ele vai colocar, serviço dele, ele é confinalizado. E automaticamente, esse estápso no sistema vai mudar, para o atendente lado outro lado e identificar, olha, o serviço já foi concluído, demorou tanto tempo. Isso aí vai refletir ali, não é? Isso mesmo. Isso aí. Agora, só tem três serviços de um mecânico, mas o que eu acho que não servei se foi finalizado, é se vai continuar lá em aberto. Em execução, né? Então, quando o último mecânico colocou o último serviço do S, foi finalizado aí, chega lá para... Filar para atender a gente, enfim, isso aí, isso pensa no nome, que é grande, né? Mas, grande parte do mecânico, só tem o mecânico e, do ajudante. Eu não tenho sistema, tá vendo? Mas aí, isso tá? Então, é mais necessitido, porque não tem como alitar... a outra é uma automática de necessidade de conforme ações. Mas não tem como assim, automático é muito relativa, tem que vai ter uma pessoa ali que vai e putar as coisas no sistema e vai andar com o estado, então é certa. E que você tem um anteriário? Temos que chegar ao meu carro lá e vai recebido. Se a gente vai adocar o carro, eu sei, tem alguém que lá, que vai lá no sistema e puta, que o dia que nós ficou efeito, por exemplo, já eu acho que não é realizado. E aí, mandala, descreve no, por exemplo, que precisa, vai ser um serviço de as peças e aí, por dia, era o orçamento, beleza? E putamos o sistema de arogentamente que transforma automática com base no preço das peças e dos serviços de manda por clientes. Ah, a gente ligou a provou. Pude, você vai ter que ir lá e colocar em a execução. Estou. Está dá para colocar um as paraguindo e não há de sap, também. Eu não estou matizada. Isso aí. Essa descrição é linda que está todos esses balids que estão debaixo da criação da orden de serviço. Eles não estão implica no maior dentro, para mim, eu posso. Eu que vou modelar ali de acordo com meu evento storm, ali. Ou seja, por exemplo, eu não preciso gerar um orçamento logo após da ordem secreada. Não. Não, é, é, você, no caso, acho que nem consegue, né? Não consegue, né? É, foi, eu sei que eu fiquei com o tato de ponto, e eu acho que não sei será para a ordem que isso não obrigações. Não, é, já acaba assim na ordem, porque eu acho que você não consegue fugir dessa ordem. Não, eu sei. Eu digo sobre os itens ali em cima, né? Porque ele já me implica, você vai preste um formulário, o formulário tem que ter isso isso isso. E aí, eu imagino que ele queria dizer tipo, como sua somora de acontecimentos, terminou formulário, preencheu, criou a ordem de hermosamento automáticamente. Só que como é que eu vou gerar um orçamento, sendo que ele tem que passar pelo estados do indiagnóstico ainda. E isso, sei. E isso, é, é, é. Sim. É, no caso, é, como estou tudo antes, né? Tudo antes daí, recebe o orçamento e depois, vai gerar a ordem de serviço. No caso, depois que está aprovado o orçamento, ele vai estar em diagnóstico, agora, provação, tudo. E isso, né? É, no caso assim, por exemplo, você vai incluir e putar por exemplo, esses dados aqui, dedicação, cadaz da placa e o que você quer fazer. Aí, beleza, mandou a S. E essa recebida. E aí, o bem-cânico foi lá, fala, são serviços. Ele botou um mudou e saca para a indiagnóstico. Fredio de anógico, ele sabe o que vai precisar fazer, o que parece que vai precisar, né, colocar. E aí, e com base no serviço que ele já tem nas peças, ele consegue mandar o orçamento. Então, já era o orçamento. E o orçamento é provado, né? Ele entra em execução, e aí, finaliza entrega. Então, segue alguma ordem. aqui, mas acaba aqui, o receimento da OS, entre diagnóstico, e aí o mecânico viau a possibilidade de peças, definiu as peças de aerosamento, um tratamento, o tratamento da mecção, se o cliente aprovou todos o serviço ou enfim, ou um serviço a opção também, que é bem comum também, pode criar o cenário, ali no fluxo de voltar, a mandada no ovo orçamento, opa, no aprovo esse, como que vai ser meu fluxo, quando ele não aprova orçamento. Eu vou enviar a orçamento, eu vou pedir para ele mais informação, porque ele quer, enfim, aí eu vou que vocês vão estar. Sim, não tem certo errado que, portanto, isso já algo a mais está, vocês podem seguir pelo básico. É claro, é importante você escolar e fluxo de excessão e fluxo alternativos, então não só fluxo o fluxo ferizos, mario ferizos. Ah, provou a bolitinho, tá? E quando ele reprova, que você tem uma com eles comporta, quando eu não tenho um pérsate, como eles comporta, isso é legal colocar, não necessariamente vocês precisam implementar tudo, tá? Pementação vai muito, então, tudo que a gente está pedindo aqui, mas no desenho do domínio ali, como vai funcionar o fluxo do negócio, é legal fazer esse botar em esses fluxos de excessão, tá? Ou, uma tesoula, acho que, para ficar, pelo menos pelo menos, entendimento, é a ordem recebida, na verdade, eu entendo que é pela parte do cliente, por eu quero trocar, vou usar o exemplo que está exposto, vou trocar, eu quero trocar o óleo e fazer um aninhamento. É isso que recebo de atendimento do cliente. O diagnóstico é, tipo de óleo, o aninhamento das quatro ródum, a roda e aí eu devolvo com um orçamento, e aí fica no próximo estado de aguardando a provação. A partir daí, eu crios dos fluxos de aprovado, executa, reprovado, finaliza. Isso aí, até porque assim, você pode nem ter um serviço específico, né? Sim, pode ser um serviço geralático, uma notenção. Porque aí eu quero poder ter um alábio, eu calta com problema. Então, recebio é essa só identificar, CPF, placa e calma, né? E aí, ele vai entrar em dragão nócico, aí, sim, eu me cano e covar em indicar, que serviço que precisa fazer, que peça que você incluída, não me sabe, tinha o fíjate, já chega com o serviço ali pronto, né? Entida, boa. Chou de bola, valeu, por favor. Olá. Pula, galera. É, vamos ver se tem uma legula para a gente finalizar. Prefer uma dúvida aqui. Então, o único indipântico que não precisaria necessariamente de alpentecação seria o de criação da Oesca, seria por parte do cliente, então. O resto são todos em dipântico mais digestão. É, aqui, na verdade, o que vai criar, eu próprio me cano. quando é a própria mecânica. Mas ali é um fluxo que da gente não tá pedindo as autenticações. Então, o mecaninho pra entrar no sistema e vai criar o S. Se você vai tirar, olha, vai criar não, vai identificar, vai tirar o Ico, está abelesa. Agora, para que já administrate, vai onde eu faço o crúdio, ali do veículo. Não, não quando eu as socios ele não é, se não é, se não. Então, para que eu casas trânsis, que elas trânsis verão, que ele é um serviço e as peças aí, sim, é preciso dar autenticações. Então, eu vou ter a indicação, que é um fluxo administrativo mesmo do sistema. Agora já tinha um crúdio cadastrado, já tinha o veículo, por exemplo, aí a caixa não de orir em serviço, como é que vai fazer ali, não precisa. Ahem, quem gira, obrigada. Aqui é só para garantir que, de fato, quem tá se showando essas rotas, é um administrador do sistema. Só ele que tem a autorização, a cessar esses indipóintes aqui, então. O que é claro? Bom, vou responder aqui as últimas no chat, olha. Relatório de vulnerabilidade, ou as pesape vai servir como complemento, o Termativo do Sonar, é um complemento, tá? Sonar, a gente vai dar ali muito um cenário de pipeline, e mesmo, né, ali de projeto, build, ali. O as pesape, a gente consegue fazer um teste em tempo de execução, né, do nosso app, tá, um pouco diferente. E aí, os dois complementos aqui nesse caso. Outra pergunta, ou a gente pode subir também como apoio uma infrafrita, a infrafrita, como a renda, ou a sua talvez, não. Nesse momento não, tá? Doi eu quero que eu compose, que inclusive é uma cobrança que do projeto, então, localmente, local nesse momento. Bom, claro, como você é uma vestindo ali, então, também escuro ainda, ou biscuro. Olha galera, é mais simples do que vocês, imagina, então, você apai que em tantos aos detalhes, não. E, portanto, é, seguir em que as melhores práticas, satientarem aos requisitos mínimos e qualquer dúvida que você estiver, mas quer dúvidas, né, que vocês tiveram que ao longo do curso de implementação, como eu, professor Rogério, que estaríamos disponíveis ao Discord, e, enfim, teremos os grupos também de estudo, outros encontros, para a gente poder estar mais dúvidas, tá? E vocês? Espero que tenham ajudado aqui essa lágia. Té, que tem um contributo de alguma forma. Ela tinha gravado também, então, caso vocês... Perdeu, a limetática, a nossa atrasada. A manhã essa no migano já tá disponível aqui no plano discord para vocês. Top galera, que é a falar alguma coisa aí, o professor Rogério. Não, não. Acho que... Não, é claro. Na declaro. Opa. Então, galera, agradeço. Mas uma vez aqui, apresenta de todos, horário, você que tá tarde, você não tá cansado, mas é brigada, é importante, mas não se participar em todos os encontros. Eu acho que, ao diferencial dessa após aqui das demais, no modelo de postec, que aqui a gente tem esse espaço de fato de troca de compartilhamento, se encontros ao vivo aqui, que a gente pode colocar. Vamos, outras, vou juntos, por isso a gente tem um cenário. Eu tomei te assim que não tem essa interação, então ajuda bastante a aproveitem o máximo aqui esses momentos. As likes também são, são bem legais, os grupos de estudos. Eu costumo... Quando a gente tentou, é muito grande, assim, que fica muito difícil, que a gente conseguir tirar dúvida, essa dúvida de cada um, imagina, entre 150 pessoas no grupo de tudo. Imagina, 30, 40 pessoas levantam a mão e uma hormina, então, eu gosto bastante de debate, assunto de fato de adirar, de trabalho, como a gente vê isso sendo aplicado. O trabalho, estou pensando até trazer algo relacionado, vou conversar com um gênero, e depois, para trazer um pouco do tema de ata, também aplicado ao que a gente está aprendendo aqui, então, tudo que a gente está vendo aqui, como que a ajuda, como que é aplicada, porque, considera, a gente está vendo muito forte, se tem uma hoje, não dá pra gente fugir disso, acho que isso vai agregar bastante pra vocês, tá? Então, se vocês terem sugestão também de tema, algo que vocês queiram trazer, para o professor tem um tema legal, via o legal que na minha empresa, que era levar para o trator, que faz total sentido que a gente está aprendendo, enxerme lá no Discord no privado, a gente escute, espaço superaberta, que tá. O combinar lá e ver para apresentar o caso, lá no Brasil, é desco. Boa. Então, eu vou mandar um pereino saindo ainda, como aí, como aí, eu vou mandar um formulário, que é muito importante, vocês responderam, ajuda muito a gente, deixa eu só pegar aqui, bem rapidinho, eu vou mandar no chat, como aí, como aí, não é uma pessoa tá saindo, pessoa tá afirindo o formulário, como daqui, Vou mandar aqui para vocês responder aí aqui sobre essa live tá? A gente aqui em esteve então, se eles são nem um curso lá de vocês, software architecture, a turma aqui em Zsote, vou colocar aqui escrito, por uma quinhinha de Sote e aí no nome da live, vocês são meu vindo? Então, a prevenção de C. O vinha é prejou. O aqui? Então, eu me chovi na C. Vou aqui, não é uma traválente. Agradeço a sua espada responder que ajuda bastante a gente que tá melhorável e sem... Toa, a gente, obrigado, viu pela presença, ótima, não te pela vocês, bonso canso e até a próxima, até a próxima encontro aí. Valeu, boa noite. Valeu.
