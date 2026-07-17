package comercial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * COMPOSITE — Client.
 * Só conhece a interface ProdutoComercial: calcula totais sem saber (nem
 * precisar saber) se o item é um filme avulso ou um super pacote aninhado.
 */
public class CarrinhoDeCompras {

    private final List<ProdutoComercial> itens = new ArrayList<>();

    public CarrinhoDeCompras adicionar(ProdutoComercial produto) {
        itens.add(produto);
        return this;
    }

    public double getPrecoTotal() {
        return itens.stream().mapToDouble(ProdutoComercial::getPreco).sum();
    }

    public int getDuracaoTotalMin() {
        return itens.stream().mapToInt(ProdutoComercial::getDuracaoMin).sum();
    }

    public String resumo() {
        StringBuilder sb = new StringBuilder("========== CARRINHO ==========");
        for (ProdutoComercial p : itens) {
            sb.append(System.lineSeparator()).append(p.descrever());
        }
        sb.append(System.lineSeparator())
          .append(String.format(Locale.US, "TOTAL: R$ %.2f | %d min de conteúdo",
                  getPrecoTotal(), getDuracaoTotalMin()));
        return sb.toString();
    }
}
