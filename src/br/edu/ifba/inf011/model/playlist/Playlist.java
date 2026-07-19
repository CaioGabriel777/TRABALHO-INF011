package br.edu.ifba.inf011.model.playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * VISITOR - Object Structure.
 *
 * Agrega itens heterogeneos (catalogo + uploads) e sabe percorre-los aplicando
 * qualquer visitante. A playlist nao conhece o que cada analise faz - apenas
 * repassa o visitante para cada item via double dispatch.
 */
public class Playlist {

	private String nome;
	private List<PlaylistItem> itens;

	public Playlist(String nome) {
		this.nome = nome;
		this.itens = new ArrayList<PlaylistItem>();
	}

	public Playlist addItem(PlaylistItem item) {
		this.itens.add(item);
		return this;
	}

	public String getNome() {
		return this.nome;
	}

	public void aceitar(PlaylistVisitor visitante) {
		for (PlaylistItem item : this.itens)
			item.aceitar(visitante);
	}
}
