package br.edu.ifba.inf011.model.comercial;

import java.util.Locale;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.model.playlist.PlaylistItem;
import br.edu.ifba.inf011.model.playlist.PlaylistVisitor;

/**
 * COMPOSITE - Leaf   |   VISITOR - ConcreteElement.
 *
 * Item indivisivel do catalogo. Assim como Filme, delega a duracao a sua
 * Timeline interna e responde ele mesmo as perguntas comerciais.
 */
public class Episodio implements ProdutoComercial, PlaylistItem {

	private String titulo;
	private Double preco;
	private Integer numero;
	private Timeline timeline;

	public Episodio(String titulo, Double preco, Integer numero, Timeline timeline) {
		this.titulo = titulo;
		this.preco = preco;
		this.numero = numero;
		this.timeline = timeline;
	}

	@Override
	public String getTitulo() {
		return this.titulo;
	}

	@Override
	public Double getPreco() {
		return this.preco;
	}

	@Override
	public Integer getDuracao() {
		return this.timeline.getDurationInSeconds();
	}

	public Integer getNumero() {
		return this.numero;
	}

	@Override
	public String descrever(Integer nivel) {
		return "  ".repeat(nivel) + String.format(Locale.US,
				"- Episodio %d: %s (R$ %.2f | %d s)",
				this.numero, this.titulo, this.getPreco(), this.getDuracao());
	}

	/** VISITOR - double dispatch. */
	@Override
	public void aceitar(PlaylistVisitor visitante) {
		visitante.visitar(this);
	}
}
