package br.edu.ifba.inf011.model.comercial;

import java.util.Locale;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;

/**
 * COMPOSITE - Leaf.
 *
 * Conteudo indivisivel usado como "bonus" nos pacotes promocionais
 * (ex.: o documentario que acompanha a Trilogia Matrix). Novos tipos de
 * produto entram sem alterar o carrinho (Aberto/Fechado).
 */
public class Documentario implements ProdutoComercial {

	private String titulo;
	private Double preco;
	private Timeline timeline;

	public Documentario(String titulo, Double preco, Timeline timeline) {
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

	@Override
	public Integer getDuracao() {
		return this.timeline.getDurationInSeconds();
	}

	@Override
	public String descrever(Integer nivel) {
		return "  ".repeat(nivel) + String.format(Locale.US,
				"- Documentario (bonus): %s (R$ %.2f | %d s)",
				this.titulo, this.getPreco(), this.getDuracao());
	}
}
