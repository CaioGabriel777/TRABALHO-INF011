package br.edu.ifba.inf011.model.playlist;

import java.util.Locale;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;

/**
 * VISITOR - ConcreteVisitor.
 *
 * Pedido mais recente da equipe de dados: "Exportador para XML". Mantem os
 * formatos de tag ja usados no projeto (filme, episodio, mp3, video) e, de
 * novo, sem modificar nenhuma classe de midia.
 */
public class ExportadorXML implements PlaylistVisitor {

	private StringBuilder corpo = new StringBuilder();

	@Override
	public void visitar(Filme filme) {
		this.corpo.append(String.format(Locale.US,
				"  <filme titulo=\"%s\" preco=\"%.2f\" duracao=\"%d\"/>%n",
				filme.getTitulo(), filme.getPreco(), filme.getDuracao()));
	}

	@Override
	public void visitar(Episodio episodio) {
		this.corpo.append(String.format(Locale.US,
				"  <episodio titulo=\"%s\" numero=\"%d\" preco=\"%.2f\" duracao=\"%d\"/>%n",
				episodio.getTitulo(), episodio.getNumero(), episodio.getPreco(), episodio.getDuracao()));
	}

	@Override
	public void visitar(MP3 mp3) {
		this.corpo.append(String.format(Locale.US,
				"  <mp3 nome=\"%s\" tamanhoMB=\"%.1f\"/>%n",
				mp3.getNome(), mp3.getTamanhoMegaBytes()));
	}

	@Override
	public void visitar(Video video) {
		this.corpo.append(String.format(Locale.US,
				"  <video nome=\"%s\" link=\"%s\" tamanhoMB=\"%.1f\"/>%n",
				video.getNome(), video.getLink(), video.getTamanhoMegaBytes()));
	}

	public String exportar(String nomePlaylist) {
		return "<playlist nome=\"" + nomePlaylist + "\">" + System.lineSeparator()
				+ this.corpo + "</playlist>";
	}
}
