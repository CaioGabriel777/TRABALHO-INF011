package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.CinemaTimelineBuilder;

/**
 * COMPOSITE - Composite.
 *
 * Agrupa Episodios e deriva preco e duracao recursivamente a partir deles.
 * Para o carrinho, uma Serie e apenas mais um ProdutoComercial.
 */
public class Serie implements ProdutoComercial {

	private String titulo;
	private Integer temporada;
	private List<Episodio> episodios;

	public Serie(String titulo, Integer temporada) {
		this.titulo = titulo;
		this.temporada = temporada;
		this.episodios = new ArrayList<Episodio>();
	}

	public Serie adicionar(Episodio episodio) {
		this.episodios.add(episodio);
		return this;
	}

	/** Conveniencia fluente: esconde a criacao da Timeline (dominio tecnico). */
	public Serie comEpisodio(String titulo, Double preco, String videoStream) {
		return this.adicionar(new Episodio(titulo, preco, this.episodios.size() + 1,
				new CinemaTimelineBuilder().reset().addClassAdapterVideo(videoStream).build()));
	}

	@Override
	public String getTitulo() {
		return this.titulo;
	}

	public Integer getTemporada() {
		return this.temporada;
	}

	@Override
	public Double getPreco() {
		return this.episodios.stream().mapToDouble(Episodio::getPreco).sum();
	}

	@Override
	public Integer getDuracao() {
		return this.episodios.stream().mapToInt(Episodio::getDuracao).sum();
	}

	@Override
	public String descrever(Integer nivel) {
		StringBuilder sb = new StringBuilder("  ".repeat(nivel))
				.append(String.format(Locale.US, "+ Serie: %s (T%d | R$ %.2f | %d s)",
						this.titulo, this.temporada, this.getPreco(), this.getDuracao()));
		for (Episodio episodio : this.episodios)
			sb.append(System.lineSeparator()).append(episodio.descrever(nivel + 1));
		return sb.toString();
	}
}
