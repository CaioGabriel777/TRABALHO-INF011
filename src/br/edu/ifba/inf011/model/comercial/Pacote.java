package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * COMPOSITE - Composite   |   BUILDER - Product.
 *
 * Agrupa quaisquer ProdutoComercial (folhas OU outros pacotes) com um desconto
 * proprio. Como os filhos sao do tipo ProdutoComercial, um Pacote pode conter
 * outro Pacote com profundidade arbitraria ("Super Pacote"). Preco e duracao
 * sao derivados recursivamente da arvore de filhos, e o desconto e aplicado
 * sobre a soma dos filhos.
 */
public class Pacote implements ProdutoComercial {

	private String titulo;
	private Double desconto; // 0.30 = 30% off
	private List<ProdutoComercial> itens;

	public Pacote(String titulo, Double desconto) {
		this.titulo = titulo;
		this.desconto = desconto;
		this.itens = new ArrayList<ProdutoComercial>();
	}

	public Pacote adicionar(ProdutoComercial produto) {
		this.itens.add(produto);
		return this;
	}

	@Override
	public String getTitulo() {
		return this.titulo;
	}

	@Override
	public Double getPreco() {
		Double bruto = this.itens.stream().mapToDouble(ProdutoComercial::getPreco).sum();
		return bruto * (1.0 - this.desconto);
	}

	@Override
	public Integer getDuracao() {
		return this.itens.stream().mapToInt(ProdutoComercial::getDuracao).sum();
	}

	@Override
	public String descrever(Integer nivel) {
		StringBuilder sb = new StringBuilder("  ".repeat(nivel))
				.append(String.format(Locale.US,
						"+ Pacote: %s (%.0f%% off | R$ %.2f | %d s)",
						this.titulo, this.desconto * 100, this.getPreco(), this.getDuracao()));
		for (ProdutoComercial produto : this.itens)
			sb.append(System.lineSeparator()).append(produto.descrever(nivel + 1));
		return sb.toString();
	}
}
