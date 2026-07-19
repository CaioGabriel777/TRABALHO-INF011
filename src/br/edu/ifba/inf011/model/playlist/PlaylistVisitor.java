package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;

/**
 * VISITOR - Visitor.
 *
 * Declara uma sobrecarga visitar(...) para cada elemento concreto que pode
 * aparecer numa playlist. Cada nova operacao analitica pedida pela equipe de
 * Ciencia de Dados vira uma nova implementacao desta interface - sem tocar em
 * MP3, Video, Filme ou Episodio (Aberto/Fechado + Responsabilidade Unica).
 *
 * Trade-off consciente do padrao: incluir um NOVO TIPO de elemento exige
 * adicionar um metodo aqui. E aceitavel neste cenario porque o conjunto de
 * tipos de midia e estavel, enquanto o que muda com frequencia sao as
 * operacoes - exatamente a situacao descrita no enunciado.
 */
public interface PlaylistVisitor {
	public void visitar(Filme filme);
	public void visitar(Episodio episodio);
	public void visitar(MP3 mp3);
	public void visitar(Video video);
}
