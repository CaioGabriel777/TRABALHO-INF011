package br.edu.ifba.inf011.model.playlist;

/**
 * VISITOR - ConcreteElement. Upload do proprio cliente (fora do catalogo).
 *
 * Note que a classe voltou a ter uma unica responsabilidade: descrever um MP3.
 * Nenhuma logica de banda/relatorio/XML mora mais aqui.
 */
public class MP3 implements PlaylistItem {

	private String nome;
	private double tamanhoMegaBytes;

	public MP3(String nome, double tamanho) {
		this.nome = nome;
		this.tamanhoMegaBytes = tamanho;
	}

	public String getNome() {
		return this.nome;
	}

	public double getTamanhoMegaBytes() {
		return this.tamanhoMegaBytes;
	}

	/** VISITOR - double dispatch. */
	@Override
	public void aceitar(PlaylistVisitor visitante) {
		visitante.visitar(this);
	}
}
