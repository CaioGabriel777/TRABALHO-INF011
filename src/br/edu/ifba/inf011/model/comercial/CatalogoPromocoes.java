package br.edu.ifba.inf011.model.comercial;

/**
 * BUILDER - Director.
 *
 * Centraliza as "receitas" oficiais das promocoes da Black Friday, dirigindo o
 * PacoteBuilder. As receitas ficam reutilizaveis e testaveis, e note que
 * colecaoSciFi() monta um Super Pacote: pacotes dentro de pacotes, mais uma
 * serie e um filme avulso.
 */
public class CatalogoPromocoes {

	public Pacote trilogiaMatrix() {
		return new PacoteBuilder("Trilogia Matrix")
				.comDesconto(0.25)
				.comFilme("Matrix", 14.90, "Matrix_4K.mov")
				.comFilme("Matrix Reloaded", 14.90, "MatrixReloaded_4K.mov")
				.comFilme("Matrix Revolutions", 14.90, "MatrixRevolutions_4K.mov")
				.comDocumentarioBonus("The Matrix Revisited", "MatrixRevisited_HD.mov")
				.construir();
	}

	public Pacote trilogiaStarWars() {
		return new PacoteBuilder("Star Wars - Trilogia Original")
				.comDesconto(0.20)
				.comFilme("Uma Nova Esperanca", 12.90, "StarWars_IV.mov")
				.comFilme("O Imperio Contra-Ataca", 12.90, "StarWars_V.mov")
				.comFilme("O Retorno de Jedi", 12.90, "StarWars_VI.mov")
				.construir();
	}

	public Serie blackMirrorT1() {
		return new Serie("Black Mirror", 1)
				.comEpisodio("The National Anthem", 4.90, "BM_S01E01.mov")
				.comEpisodio("Fifteen Million Merits", 4.90, "BM_S01E02.mov")
				.comEpisodio("The Entire History of You", 4.90, "BM_S01E03.mov");
	}

	/** Super Pacote: pacotes dentro de pacotes + serie + filme avulso. */
	public Pacote colecaoSciFi() {
		return new PacoteBuilder("Colecao Sci-Fi")
				.comDesconto(0.30)
				.comPacote(this.trilogiaMatrix())
				.comPacote(this.trilogiaStarWars())
				.comSerie(this.blackMirrorT1())
				.comFilme("Blade Runner", 14.90, "BladeRunner_4K.mov")
				.construir();
	}
}
