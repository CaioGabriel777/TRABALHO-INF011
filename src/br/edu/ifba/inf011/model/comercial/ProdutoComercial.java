package br.edu.ifba.inf011.model.comercial;

/**
 * COMPOSITE - Component.
 *
 * Interface unica da vitrine comercial. O carrinho e qualquer outro cliente
 * enxergam filmes avulsos e super pacotes aninhados exatamente da mesma forma:
 * pedir o preco ou a duracao de uma folha (Filme) ou de um composto (Pacote)
 * e a mesma chamada, e a recursao pela arvore fica transparente.
 */
public interface ProdutoComercial {

	public String getTitulo();

	public Double getPreco();

	public Integer getDuracao();

	/** Descricao recursiva (indentada por nivel) para exibicao em arvore. */
	public String descrever(Integer nivel);

	public default String descrever() {
		return this.descrever(0);
	}
}
