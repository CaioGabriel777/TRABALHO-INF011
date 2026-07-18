package comercial;

import java.util.ArrayList;
import java.util.List;

import tecnico.Timeline;

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

    public PacoteBuilder comPacote(Pacote pacote) {
        itens.add(pacote);
        return this;
    }

    public PacoteBuilder comProduto(ProdutoComercial produto) {
        itens.add(produto);
        return this;
    }

    public Pacote construir() {
        Pacote pacote = new Pacote(titulo, desconto);
        itens.forEach(pacote::adicionar);
        return pacote;
    }
}
