package comercial;

import java.util.Locale;

import playlist.ItemPlaylist;
import playlist.PlaylistVisitor;
import tecnico.Timeline;

public class Episodio implements ProdutoComercial, ItemPlaylist {

    private final String titulo;
    private final double preco;
    private final String resolucao;
    private final Timeline timeline;

    public Episodio(String titulo, double preco, String resolucao, Timeline timeline) {
        this.titulo = titulo;
        this.preco = preco;
        this.resolucao = resolucao;
        this.timeline = timeline;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public int getDuracaoMin() {
        return timeline.getDuracaoMin();
    }

    public String getResolucao() {
        return resolucao;
    }

    @Override
    public String descrever(int nivel) {
        return "  ".repeat(nivel) + String.format(Locale.US,
                "- Episódio: %s [%s] (R$ %.2f | %d min)",
                titulo, resolucao, preco, getDuracaoMin());
    }

    @Override
    public void aceitar(PlaylistVisitor visitante) {
        visitante.visitar(this);
    }
}
