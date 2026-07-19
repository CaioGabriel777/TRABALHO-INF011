package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * COMPOSITE - Client.
 *
 * Opera exclusivamente pela interface ProdutoComercial, sem distinguir folha
 * de composto: um filme avulso e um super pacote aninhado sao somados da
 * mesma maneira ao total de preco e duracao.
 */
public class CarrinhoDeCompras {

	private List<ProdutoComercial> itens = new ArrayList<ProdutoComercial>();

	public CarrinhoDeCompras adicionar(ProdutoComercial produto) {
		this.itens.add(produto);
		return this;
	}

	public Double getPrecoTotal() {
		return this.itens.stream().mapToDouble(ProdutoComercial::getPreco).sum();
	}

	public Integer getDuracaoTotal() {
		return this.itens.stream().mapToInt(ProdutoComercial::getDuracao).sum();
	}

	public String resumo() {
		StringBuilder sb = new StringBuilder("========== CARRINHO ==========");
		for (ProdutoComercial produto : this.itens)
			sb.append(System.lineSeparator()).append(produto.descrever());
		sb.append(System.lineSeparator())
				.append(String.format(Locale.US, "TOTAL: R$ %.2f | %d s de conteudo",
						this.getPrecoTotal(), this.getDuracaoTotal()));
		return sb.toString();
	}
}
