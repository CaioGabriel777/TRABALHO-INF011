package comercial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tecnico.Timeline;

/**
 * COMPOSITE — Composite (de Episodio).
 * Preço e duração são derivados recursivamente dos episódios.
 */
public class Serie implements ProdutoComercial {

    private final String titulo;
    private final List<Episodio> episodios = new ArrayList<>();

    public Serie(String titulo) {
        this.titulo = titulo;
    }

    public Serie adicionar(Episodio episodio) {
        episodios.add(episodio);
        return this;
    }

    /** Conveniência fluente: evita o "new Episodio(..., new Timeline(...))" verboso. */
    public Serie comEpisodio(String titulo, double preco, String resolucao, int duracaoMin) {
        return adicionar(new Episodio(titulo, preco, resolucao, Timeline.padrao(duracaoMin)));
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public double getPreco() {
        return episodios.stream().mapToDouble(Episodio::getPreco).sum();
    }

    @Override
    public int getDuracaoMin() {
        return episodios.stream().mapToInt(Episodio::getDuracaoMin).sum();
    }

    @Override
    public String descrever(int nivel) {
        StringBuilder sb = new StringBuilder("  ".repeat(nivel))
                .append(String.format(Locale.US, "+ Série: %s (R$ %.2f | %d min)",
                        titulo, getPreco(), getDuracaoMin()));
        for (Episodio e : episodios) {
            sb.append(System.lineSeparator()).append(e.descrever(nivel + 1));
        }
        return sb.toString();
    }
}
