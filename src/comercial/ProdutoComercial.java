package comercial;

/**
 * COMPOSITE — Component.
 * Contrato único da vitrine comercial: o carrinho (Client) calcula preço e
 * duração de um filme avulso ou de um super pacote aninhado exatamente da
 * mesma forma, através desta interface.
 */
public interface ProdutoComercial {

    String getTitulo();

    double getPreco();

    int getDuracaoMin();

    /** Representação textual em árvore (nivel = profundidade de indentação). */
    String descrever(int nivel);

    default String descrever() {
        return descrever(0);
    }
}
