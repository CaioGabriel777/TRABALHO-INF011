package br.edu.ifba.inf011.model.comercial;

import java.util.Locale;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.model.playlist.PlaylistItem;
import br.edu.ifba.inf011.model.playlist.PlaylistVisitor;

/**
 * COMPOSITE - Leaf   |   VISITOR - ConcreteElement.
 *
 * A "casca comercial" do enunciado: encapsula atributos de negocio (titulo,
 * preco) e CONTEM uma Timeline (principio da Composicao). Perguntas do
 * dominio tecnico (duracao) sao DELEGADAS a Timeline interna; perguntas do
 * dominio comercial (preco) a propria classe responde.
 */
public class Filme implements ProdutoComercial, PlaylistItem {

	private String titulo;
	private Double preco;
	private Timeline timeline;

	public Filme(String titulo, Double preco, Timeline timeline) {
		this.titulo = titulo;
		this.preco = preco;
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

	/** Delegacao: fronteira Dominio Comercial -> Dominio Tecnico. */
	@Override
	public Integer getDuracao() {
		return this.timeline.getDurationInSeconds();
	}

	@Override
	public String descrever(Integer nivel) {
		return "  ".repeat(nivel) + String.format(Locale.US,
				"- Filme: %s (R$ %.2f | %d s)",
				this.titulo, this.getPreco(), this.getDuracao());
	}

	/** VISITOR - double dispatch. */
	@Override
	public void aceitar(PlaylistVisitor visitante) {
		visitante.visitar(this);
	}
}
