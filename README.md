# INF011 — Padrões de Projeto · Avaliação III

**O Ecossistema de Streaming: do Motor Técnico à Plataforma Comercial**

Equipe:
- [Caio Amorim](https://github.com/CaioGabriel777)
- [Luan Guimarães](https://github.com/LuanVitorRibeiroGuimaraes)
- [João Gabriel](https://github.com/Joao-Gabriel-SB)

---

## Visão geral

O projeto parte do **esqueleto oficial da disciplina** (o *Domínio Técnico* das fases
anteriores — `Timeline`, `Track`, `Builder`, `Adapter` e `Decorator`, sob o pacote
`br.edu.ifba.inf011`) e implementa sobre ele as **Questões I e II** da Avaliação III,
respeitando a fronteira de domínios definida no enunciado através do **princípio da
Composição**:

- **Domínio Técnico** (`avaliacao1`, `avaliacao2`, `model.track`, `model.canva`,
  `model.encoder`, `model.renderer`, `thirdPart`): `RenderableContent`, `Timeline` e as
  `Track`s continuam como o "motor técnico" da mídia e **ignoram** conceitos de negócio
  (preço, catálogo, promoção). Esse código vem do esqueleto e é **reutilizado sem
  alterações**.
- **Domínio Comercial** (`model.comercial`): `Filme`, `Episodio`, `Serie`, `Documentario`
  e `Pacote` formam a vitrine. A ligação entre os domínios é feita por **Composição**: um
  `Filme` **contém** uma `Timeline` e **delega** a ela as perguntas técnicas
  (`getDuracao()` → `timeline.getDurationInSeconds()`), enquanto responde ele mesmo as
  perguntas comerciais (`getPreco()`).
- **Playlists e análises** (`model.playlist`): estrutura heterogênea que mistura itens do
  catálogo (`Filme`, `Episodio`) com uploads do usuário (`MP3`, `Video`), sobre a qual a
  equipe de Ciência de Dados executa operações analíticas.

O ponto de entrada da avaliação é **`br.edu.ifba.inf011.model.ClienteAval3`**, que
demonstra as duas questões (`runQuestaoI` e `runQuestaoII`). Os clientes `ClientAval1` e
`ClientAval2`, herdados do esqueleto, demonstram o motor técnico das fases anteriores e
seguem funcionando.

> ℹ️ A duração dos itens de catálogo é lida do `HDDBinaryReader` (motor técnico do
> esqueleto), que gera metadados de duração de forma pseudoaleatória. Por isso os valores
> de duração/banda **variam a cada execução** — isso é comportamento herdado do domínio
> técnico, não da solução das Questões I e II.

## Como executar

Requer **JDK 11 ou superior** (testado com JDK 17 e 25). Abra o terminal **na raiz do
projeto** (a pasta `TRABALHO-INF011/`, onde está este README) e copie o bloco do seu
sistema operacional. O 1º comando compila; o 2º executa.

### 🐧 Linux / 🍎 macOS 

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out br.edu.ifba.inf011.model.ClienteAval3
```

### 🪟 Windows — Prompt de Comando (cmd)

```bat
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d out @sources.txt
java -cp out br.edu.ifba.inf011.model.ClienteAval3
```

### 🪟 Windows — PowerShell

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp out br.edu.ifba.inf011.model.ClienteAval3
```

### 💡 Alternativa: pela IDE (IntelliJ / Eclipse / VS Code)

Marque `src/` como pasta de código-fonte (*Sources Root*) e execute a classe
`br.edu.ifba.inf011.model.ClienteAval3` (método `main`) — funciona em qualquer SO.

## Estrutura de pastas

```
src/br/edu/ifba/inf011/
├── avaliacao1/               → Motor técnico (esqueleto): Prototype + Builder da Timeline
│   ├── prototype/            →   Forkable
│   └── timeline/builder/     →   Timeline, TimelineBuilder, CinemaTimelineBuilder
├── avaliacao2/               → Motor técnico (esqueleto): Adapter + Decorator
│   ├── adapter/              →   Class/Object AdapterTrack
│   └── decorator/            →   RenderableContent, *Decorator
├── model/
│   ├── canva|encoder|renderer|track/   → Infraestrutura técnica (esqueleto)
│   ├── comercial/            → QUESTÃO I: Composite + Builder
│   ├── playlist/             → QUESTÃO II: Visitor
│   ├── ClientAval1.java      → Demo do motor técnico (fase I)
│   ├── ClientAval2.java      → Demo do motor técnico (fase II)
│   └── ClienteAval3.java     → *** Demo das Questões I e II (ponto de entrada) ***
└── thirdPart/                → HDDBinaryReader (Adaptee de baixo nível, esqueleto)
```

---

# Questão I — Composite + Builder

**Problema.** Vender pacotes promocionais com desconto que podem conter filmes,
documentários, séries e **outros pacotes** ("Super Pacotes", com aninhamento arbitrário);
o carrinho precisa calcular preço total e duração tratando filme avulso e pacote complexo
**exatamente da mesma maneira**; e a primeira versão da instanciação gerou construtores
gigantescos, repetitivos e ilegíveis.

## Padrão de projeto: Composite

**Justificativa.** Os produtos formam uma hierarquia parte-todo de profundidade arbitrária
(pacote dentro de pacote). O Composite dá ao cliente uma interface única
(`ProdutoComercial`): pedir `getPreco()` ou `getDuracao()` de um filme avulso ou de um
super pacote é a mesma chamada — a recursão pela árvore é transparente, e o desconto de
cada pacote é aplicado sobre a soma recursiva dos seus filhos. Novos tipos de produto
(Clipe, Temporada...) entram sem alterar uma linha do carrinho (Aberto/Fechado).

| Participante (GoF) | Classe no projeto | Papel |
|---|---|---|
| **Component** | `ProdutoComercial` | Interface única da vitrine: `getTitulo()`, `getPreco()`, `getDuracao()`, `descrever()` |
| **Leaf** | `Filme`, `Documentario`, `Episodio` | Itens indivisíveis; `Filme` e `Episodio` delegam a duração à `Timeline` interna (Composição) |
| **Composite** | `Pacote` (agrupa quaisquer produtos + desconto), `Serie` (agrupa episódios) | Mantêm filhos e derivam preço/duração recursivamente |
| **Client** | `CarrinhoDeCompras` (e `ClienteAval3`) | Opera apenas via `ProdutoComercial`, sem distinguir folha de composto |

## Padrão de projeto: Builder

**Justificativa.** A montagem dos super pacotes exigia expressões `new` aninhadas, longas e
propensas a erro (construtor telescópico). O Builder separa a construção passo a passo do
objeto complexo da sua representação final: a interface fluente do `PacoteBuilder` monta a
árvore de forma legível e validável, e os métodos de conveniência (`comFilme(...)`,
`comDocumentarioBonus(...)`) ainda escondem de quem monta promoções a criação da `Timeline`
(domínio técnico), reusando o `CinemaTimelineBuilder` do esqueleto. O Director
(`CatalogoPromocoes`) centraliza as "receitas" oficiais das promoções, tornando-as
reutilizáveis e testáveis.

| Participante (GoF) | Classe no projeto | Papel |
|---|---|---|
| **Builder / ConcreteBuilder** | `PacoteBuilder` | API fluente que acumula as partes e só materializa o produto em `construir()` |
| **Director** | `CatalogoPromocoes` | Conhece as receitas (`trilogiaMatrix()`, `colecaoSciFi()`...) e dirige o builder |
| **Product** | `Pacote` | O objeto complexo resultante (que é o Composite da estrutura) |

**Antes → depois:**

```java
// ANTES (ilegível, propenso a erro):
new Pacote("SciFi", new Pacote("Matrix", new Filme(...), new Filme(...)),
        new Serie("BlackMirror", new Episodio(...), new Episodio(...)), new Filme(...));

// DEPOIS (fluente e legível):
new PacoteBuilder("Coleção Sci-Fi")
        .comDesconto(0.30)
        .comPacote(trilogiaMatrix())
        .comPacote(trilogiaStarWars())
        .comSerie(blackMirrorT1())
        .comFilme("Blade Runner", 14.90, "BladeRunner_4K.mov")
        .construir();
```

## Como os dois padrões se combinam

O **Builder** é quem **monta** a estrutura **Composite**: cada chamada `com...()` adiciona
um nó à árvore (Leaf ou outro Composite) e `construir()` entrega o `Pacote` pronto para o
carrinho consumir de forma uniforme e transparente.

---

# Questão II — Visitor

**Problema.** As playlists misturam tipos do catálogo (`Filme`, `Episodio`) com uploads do
usuário (`MP3`, `Video`). Cada nova operação analítica solicitada pela equipe de Ciência de
Dados (Largura de Banda Total, Relatório de Nomes e, agora, Exportador XML) vinha exigindo
modificar as quatro classes de mídia, injetando bugs como efeito colateral e violando os
princípios Aberto/Fechado e da Responsabilidade Única.

## Padrão de projeto: Visitor

**Justificativa.** O Visitor inverte a dependência: as classes de mídia ganham um único
ponto de extensão **estável** (`aceitar(PlaylistVisitor)`) e cada operação analítica vira
uma classe visitante separada. O *double dispatch* (`visitante.visitar(this)`) seleciona a
sobrecarga correta pelo tipo concreto, permitindo lógica específica por tipo — ex.: a banda
de um upload vem do tamanho real do arquivo, a de um item do catálogo é estimada por
`duração × BAND_PER_SECOND`. Resultado: **nova análise = nova classe**, sem tocar em `MP3`,
`Video`, `Filme` ou `Episodio` (Aberto/Fechado), e cada análise fica isolada em uma classe
coesa (Responsabilidade Única).

**Trade-off consciente:** incluir um novo *tipo* de elemento exige adicionar um método na
interface `PlaylistVisitor`. Isso é aceitável neste cenário porque o conjunto de tipos de
mídia é estável, enquanto o que muda com frequência são as operações — exatamente a
situação descrita no enunciado.

| Participante (GoF) | Classe no projeto | Papel |
|---|---|---|
| **Visitor** | `PlaylistVisitor` | Declara uma sobrecarga `visitar(...)` para cada elemento concreto |
| **ConcreteVisitor** | `CalculadoraLarguraBanda`, `RelatorioNomes`, `ExportadorXML` | Uma classe por operação analítica solicitada pela equipe de dados |
| **Element** | `PlaylistItem` | Declara `aceitar(PlaylistVisitor)` |
| **ConcreteElement** | `Filme`, `Episodio` (catálogo), `MP3`, `Video` (uploads) | Implementam `aceitar` com `visitante.visitar(this)` (double dispatch) |
| **Object Structure** | `Playlist` | Agrega os itens heterogêneos e os percorre aplicando o visitante |
