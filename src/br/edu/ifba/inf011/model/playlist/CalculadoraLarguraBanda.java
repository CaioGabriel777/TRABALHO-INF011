package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;

/**
 * VISITOR - ConcreteVisitor.
 *
 * Operacao analitica "Largura de Banda Total". Cada tipo contribui de um jeito:
 * os uploads do cliente (MP3, Video) tem tamanho real conhecido, enquanto os
 * itens do catalogo (Filme, Episodio) sao estimados por duracao x BAND_PER_SECOND.
 * Essa logica especifica por tipo, isolada da estrutura, e exatamente o ganho
 * do Visitor.
 */
public class CalculadoraLarguraBanda implements PlaylistVisitor {

	private Double totalMB = 0.0;

	@Override
	public void visitar(Filme filme) {
		this.totalMB += filme.getDuracao() * PlaylistItem.BAND_PER_SECOND;
	}

	@Override
	public void visitar(Episodio episodio) {
		this.totalMB += episodio.getDuracao() * PlaylistItem.BAND_PER_SECOND;
	}

	@Override
	public void visitar(MP3 mp3) {
		this.totalMB += mp3.getTamanhoMegaBytes();
	}

	@Override
	public void visitar(Video video) {
		this.totalMB += video.getTamanhoMegaBytes();
	}

	public Double getTotalMB() {
		return this.totalMB;
	}
}
