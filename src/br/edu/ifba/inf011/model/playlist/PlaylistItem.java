package br.edu.ifba.inf011.model.playlist;

/**
 * VISITOR - Element.
 *
 * Tudo que pode entrar numa playlist (itens do catalogo OU uploads do proprio
 * cliente) expoe um unico ponto de extensao estavel: aceitar(PlaylistVisitor).
 * Antes, cada nova analise (banda, relatorio, XML) exigia alterar MP3, Video,
 * Filme e Episodio; agora esse ponto de entrada nunca muda.
 */
public interface PlaylistItem {

	/** Taxa de estimativa de banda dos itens de catalogo (MB por segundo). */
	public static Double BAND_PER_SECOND = 1.5;

	public void aceitar(PlaylistVisitor visitante);
}
