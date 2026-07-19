package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;

/**
 * BUILDER - Builder / ConcreteBuilder.
 *
 * Resolve o problema dos "construtores gigantescos e ilegiveis" do enunciado:
 * em vez de aninhar varios new Pacote(new Filme(...), new Serie(...)), a API
 * fluente acumula as partes passo a passo e so materializa o Pacote (Product)
 * em construir(). Os metodos de conveniencia (comFilme, comDocumentarioBonus)
 * ainda escondem de quem monta promocoes a criacao da Timeline (dominio
 * tecnico), reforcando a fronteira entre os dominios.
 */
public class PacoteBuilder {

	private String titulo;
	private Double desconto = 0.0;
	private List<ProdutoComercial> itens = new ArrayList<ProdutoComercial>();

	public PacoteBuilder(String titulo) {
		this.titulo = titulo;
	}

	public PacoteBuilder comDesconto(Double desconto) {
		this.desconto = desconto;
		return this;
	}

	public PacoteBuilder comFilme(String titulo, Double preco, String videoStream) {
		this.itens.add(new Filme(titulo, preco, this.timeline(videoStream)));
		return this;
	}

	public PacoteBuilder comDocumentarioBonus(String titulo, String videoStream) {
		this.itens.add(new Documentario(titulo, 0.0, this.timeline(videoStream)));
		return this;
	}

	public PacoteBuilder comSerie(Serie serie) {
		this.itens.add(serie);
		return this;
	}

	public PacoteBuilder comPacote(Pacote pacote) {
		this.itens.add(pacote);
		return this;
	}

	public PacoteBuilder comProduto(ProdutoComercial produto) {
		this.itens.add(produto);
		return this;
	}

	public Pacote construir() {
		Pacote pacote = new Pacote(this.titulo, this.desconto);
		this.itens.forEach(pacote::adicionar);
		return pacote;
	}

	/** Reuso do TimelineBuilder do dominio tecnico (Avaliacao I). */
	private Timeline timeline(String videoStream) {
		return new CinemaTimelineBuilder().reset().addClassAdapterVideo(videoStream).build();
	}
}
