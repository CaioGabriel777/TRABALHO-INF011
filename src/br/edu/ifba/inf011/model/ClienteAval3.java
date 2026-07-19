package br.edu.ifba.inf011.model;

import java.util.Locale;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.model.comercial.CarrinhoDeCompras;
import br.edu.ifba.inf011.model.comercial.CatalogoPromocoes;
import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.playlist.CalculadoraLarguraBanda;
import br.edu.ifba.inf011.model.playlist.ExportadorXML;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Playlist;
import br.edu.ifba.inf011.model.playlist.RelatorioNomes;
import br.edu.ifba.inf011.model.playlist.Video;

/**
 * Cliente de demonstracao da Avaliacao III.
 *
 * runQuestaoI  -> Composite + Builder (catalogo promocional e carrinho).
 * runQuestaoII -> Visitor (analises sobre uma playlist heterogenea).
 */
public class ClienteAval3 {

	public void runQuestaoI() {
		System.out.println("############ QUESTAO I - Composite + Builder ############\n");

		CatalogoPromocoes catalogo = new CatalogoPromocoes();       // Director
		Pacote colecaoSciFi = catalogo.colecaoSciFi();              // Super Pacote aninhado

		// Montar promocoes novas tambem fica trivial e legivel com o Builder:
		Pacote comboIndie = new br.edu.ifba.inf011.model.comercial.PacoteBuilder("Combo Indie")
				.comDesconto(0.10)
				.comFilme("Primer", 9.90, "Primer_SD.mov")
				.comFilme("Coherence", 9.90, "Coherence_HD.mov")
				.construir();

		Timeline t = new CinemaTimelineBuilder().reset().addClassAdapterVideo("Interstellar_4K.mov").build();
		Filme avulso = new Filme("Interestelar", 16.90, t);

		// O Client (carrinho) trata filme avulso e super pacote da MESMA forma:
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras()
				.adicionar(colecaoSciFi)
				.adicionar(comboIndie)
				.adicionar(avulso);

		System.out.println(carrinho.resumo());
	}

	public void runQuestaoII() {
		System.out.println("\n\n############ QUESTAO II - Visitor ############\n");

		Timeline tFilme = new CinemaTimelineBuilder().reset().addClassAdapterVideo("Interstellar_4K.mov").build();
		Timeline tEp = new CinemaTimelineBuilder().reset().addClassAdapterVideo("BM_S01E01.mov").build();

		Playlist playlist = new Playlist("Favoritas do Fim de Semana")
				.addItem(new Filme("Interestelar", 16.90, tFilme))              // item do catalogo
				.addItem(new Episodio("The National Anthem", 4.90, 1, tEp))     // item do catalogo
				.addItem(new MP3("Kids - MGMT (upload)", 8.4))                  // upload do cliente
				.addItem(new Video("Gato tocando piano (upload)", 25.0, "http://uploads/gato.mp4"));

		CalculadoraLarguraBanda banda = new CalculadoraLarguraBanda();
		playlist.aceitar(banda);
		System.out.printf(Locale.US, "Largura de Banda Total: %.1f MB%n%n", banda.getTotalMB());

		RelatorioNomes relatorio = new RelatorioNomes();
		playlist.aceitar(relatorio);
		System.out.println(relatorio.getRelatorio());
		System.out.println();

		ExportadorXML xml = new ExportadorXML();
		playlist.aceitar(xml);
		System.out.println(xml.exportar(playlist.getNome()));
	}

	public void run() {
		this.runQuestaoI();
		this.runQuestaoII();
	}

	public static void main(String[] args) {
		new ClienteAval3().run();
	}
}
