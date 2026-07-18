package comercial;

import java.util.Locale;

import playlist.ItemPlaylist;
import playlist.PlaylistVisitor;
import tecnico.Timeline;

/**
 * COMPOSITE — Leaf  |  VISITOR — ConcreteElement.
 *
 * A "casca comercial" do enunciado: encapsula atributos de negócio (título,
 * preço, resolução...) e CONTÉM uma Timeline (princípio da Composição).
 * Perguntas do domínio técnico (duração) são DELEGADAS à Timeline interna;
 * perguntas do domínio comercial (preço) a própria classe responde.
 */
public class Filme implements ProdutoComercial, ItemPlaylist {

    private final String titulo;
    private final double preco;
    private final String resolucao;
    private final Timeline timeline;

    public Filme(String titulo, double preco, String resolucao, Timeline timeline) {
        this.titulo = titulo;
        this.preco = preco;
        this.resolucao = resolucao;
        this.timeline = timeline;
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public double getPreco() { return preco; }

    /** Delegação: fronteira Domínio Comercial -> Domínio Técnico. */
    @Override
    public int getDuracaoMin() { return timeline.getDuracaoMin(); }

    public String getResolucao() { return resolucao; }

    @Override
    public String descrever(int nivel) {
        return "  ".repeat(nivel) + String.format(Locale.US,
                "- Filme: %s [%s] (R$ %.2f | %d min)",
                titulo, resolucao, preco, getDuracaoMin());
    }

    /** VISITOR — double dispatch. */
    @Override
    public void aceitar(PlaylistVisitor visitante) {
        visitante.visitar(this);
    }
}
