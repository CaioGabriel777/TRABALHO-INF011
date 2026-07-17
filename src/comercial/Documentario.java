package comercial;

import java.util.Locale;

import tecnico.Timeline;

/** COMPOSITE — Leaf. Conteúdo bônus dos pacotes promocionais. */
public class Documentario implements ProdutoComercial {

    private final String titulo;
    private final double preco;
    private final Timeline timeline;

    public Documentario(String titulo, double preco, Timeline timeline) {
        this.titulo = titulo;
        this.preco = preco;
        this.timeline = timeline;
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public double getPreco() { return preco; }

    @Override
    public int getDuracaoMin() { return timeline.getDuracaoMin(); }

    @Override
    public String descrever(int nivel) {
        return "  ".repeat(nivel) + String.format(Locale.US,
                "- Documentário (bônus): %s (R$ %.2f | %d min)",
                titulo, preco, getDuracaoMin());
    }
}
