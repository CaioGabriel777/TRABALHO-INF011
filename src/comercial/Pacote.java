package comercial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * COMPOSITE — Composite.
 * Pacote promocional que agrupa QUALQUER ProdutoComercial — inclusive outros
 * pacotes ("Super Pacotes"). Preço = soma recursiva dos filhos com o desconto
 * do pacote aplicado; duração = soma recursiva das durações.
 */
public class Pacote implements ProdutoComercial {

    private final String titulo;
    private final double desconto; // 0.30 = 30%
    private final List<ProdutoComercial> itens = new ArrayList<>();

    public Pacote(String titulo, double desconto) {
        this.titulo = titulo;
        this.desconto = desconto;
    }

    public Pacote adicionar(ProdutoComercial produto) {
        itens.add(produto);
        return this;
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public double getPreco() {
        double bruto = itens.stream().mapToDouble(ProdutoComercial::getPreco).sum();
        return bruto * (1.0 - desconto);
    }

    @Override
    public int getDuracaoMin() {
        return itens.stream().mapToInt(ProdutoComercial::getDuracaoMin).sum();
    }

    @Override
    public String descrever(int nivel) {
        StringBuilder sb = new StringBuilder("  ".repeat(nivel))
                .append(String.format(Locale.US,
                        "+ Pacote: %s (%.0f%% off | R$ %.2f | %d min)",
                        titulo, desconto * 100, getPreco(), getDuracaoMin()));
        for (ProdutoComercial p : itens) {
            sb.append(System.lineSeparator()).append(p.descrever(nivel + 1));
        }
        return sb.toString();
    }
}
