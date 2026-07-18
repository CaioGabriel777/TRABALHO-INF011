package playlist;

import comercial.Episodio;
import comercial.Filme;

import java.util.Map;

/**
 * VISITOR — ConcreteVisitor.
 * Operação analítica "Largura de Banda Total": cada tipo contribui de um
 * jeito (uploads têm tamanho real conhecido; itens do catálogo são estimados
 * por resolução x duração) — exatamente o caso de uso do Visitor.
 */
public class CalculadoraLarguraBanda implements PlaylistVisitor {

    private static final Map<String, Double> MB_POR_MINUTO = Map.of(
            "4K", 110.0,
            "FULLHD", 45.0,
            "HD", 30.0,
            "SD", 15.0
    );

    private double totalMB = 0.0;

    @Override
    public void visitar(Filme filme) {
        totalMB += filme.getDuracaoMin() * taxa(filme.getResolucao());
    }

    @Override
    public void visitar(Episodio episodio) {
        totalMB += episodio.getDuracaoMin() * taxa(episodio.getResolucao());
    }

    @Override
    public void visitar(MP3 mp3) {
        totalMB += mp3.getTamanhoMB();
    }

    @Override
    public void visitar(VideoClipe clipe) {
        totalMB += clipe.getTamanhoMB();
    }

    private double taxa(String resolucao) {
        return MB_POR_MINUTO.getOrDefault(resolucao.toUpperCase(), 30.0);
    }

    public double getTotalMB() { return totalMB; }
}
