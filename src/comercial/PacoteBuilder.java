package comercial;

import java.util.ArrayList;
import java.util.List;

import tecnico.Timeline;

/**
 * BUILDER (interface fluente) — Builder/ConcreteBuilder.
 * Separa a montagem passo a passo dos pacotes da sua representação final,
 * eliminando os construtores gigantes e aninhados:
 *
 *   ANTES:  new Pacote("SciFi", new Pacote("Matrix", new Filme(...), ...), ...)
 *   DEPOIS: new PacoteBuilder("SciFi").comDesconto(0.30).comPacote(matrix)....construir()
 *
 * De quebra, os métodos de conveniência escondem de quem monta promoções a
 * criação da Timeline (domínio técnico).
 */
public class PacoteBuilder {

    private final String titulo;
    private double desconto = 0.0;
    private final List<ProdutoComercial> itens = new ArrayList<>();

    public PacoteBuilder(String titulo) {
        this.titulo = titulo;
    }

    public PacoteBuilder comDesconto(double desconto) {
        this.desconto = desconto;
        return this;
    }

    public PacoteBuilder comFilme(String titulo, double preco, String resolucao, int duracaoMin) {
        itens.add(new Filme(titulo, preco, resolucao, Timeline.padrao(duracaoMin)));
        return this;
    }

    public PacoteBuilder comDocumentarioBonus(String titulo, int duracaoMin) {
        itens.add(new Documentario(titulo, 0.0, Timeline.padrao(duracaoMin)));
        return this;
    }

    public PacoteBuilder comSerie(Serie serie) {
        itens.add(serie);
        return this;
    }

    /** Permite aninhar pacotes já montados (Super Pacotes). */
    public PacoteBuilder comPacote(Pacote pacote) {
        itens.add(pacote);
        return this;
    }

    /** Ponto de extensão genérico para qualquer novo ProdutoComercial. */
    public PacoteBuilder comProduto(ProdutoComercial produto) {
        itens.add(produto);
        return this;
    }

    /** Product: só aqui o Pacote é efetivamente criado. */
    public Pacote construir() {
        Pacote pacote = new Pacote(titulo, desconto);
        itens.forEach(pacote::adicionar);
        return pacote;
    }
}
