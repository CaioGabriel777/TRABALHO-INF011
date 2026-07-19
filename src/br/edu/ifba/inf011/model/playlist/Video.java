package br.edu.ifba.inf011.model.playlist;

/**
 * VISITOR - ConcreteElement. Pequeno videoclipe carregado pelo proprio cliente.
 */
public class Video implements PlaylistItem {

	private String nome;
	private double tamanhoMegaBytes;
	private String link;

	public Video(String nome, double tamanho, String link) {
		this.nome = nome;
		this.tamanhoMegaBytes = tamanho;
		this.link = link;
	}

	public String getNome() {
		return this.nome;
	}

	public double getTamanhoMegaBytes() {
		return this.tamanhoMegaBytes;
	}

	public String getLink() {
		return this.link;
	}

	/** VISITOR - double dispatch. */
	@Override
	public void aceitar(PlaylistVisitor visitante) {
		visitante.visitar(this);
	}
}
