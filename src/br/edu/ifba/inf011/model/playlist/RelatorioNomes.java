package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;

/**
 * VISITOR - ConcreteVisitor.
 *
 * Operacao "Relatorio com o Nome dos Elementos da Playlist". Nova analise =
 * nova classe, sem tocar em nenhum ConcreteElement.
 */
public class RelatorioNomes implements PlaylistVisitor {

	private StringBuilder relatorio = new StringBuilder("--- Relatorio da Playlist ---");

	@Override
	public void visitar(Filme filme) {
		this.linha("Filme", filme.getTitulo());
	}

	@Override
	public void visitar(Episodio episodio) {
		this.linha("Episodio", episodio.getTitulo());
	}

	@Override
	public void visitar(MP3 mp3) {
		this.linha("MP3", mp3.getNome());
	}

	@Override
	public void visitar(Video video) {
		this.linha("Video", video.getNome());
	}

	private void linha(String tipo, String titulo) {
		this.relatorio.append(System.lineSeparator())
				.append("* ").append(tipo).append(": ").append(titulo);
	}

	public String getRelatorio() {
		return this.relatorio.toString();
	}
}
