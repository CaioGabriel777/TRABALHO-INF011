package app;

import comercial.*;
import playlist.*;
import tecnico.Timeline;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        System.out.println("############ QUESTÃO I - Composite + Builder ############\n");

        CatalogoPromocoes catalogo = new CatalogoPromocoes(); // Director
        Pacote colecaoSciFi = catalogo.colecaoSciFi();        // Super Pacote aninhado

        // Montar promoções novas também fica trivial e legível com o Builder:
        Pacote comboIndie = new PacoteBuilder("Combo Indie")
                .comDesconto(0.10)
                .comFilme("Primer", 9.90, "SD", 77)
                .comFilme("Coherence", 9.90, "HD", 89)
                .construir();

        Filme avulso = new Filme("Interestelar", 16.90, "4K", Timeline.padrao(169));

        // O Client (carrinho) trata filme avulso e super pacote da MESMA forma:
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras()
                .adicionar(colecaoSciFi)
                .adicionar(comboIndie)
                .adicionar(avulso);

        System.out.println(carrinho.resumo());


        System.out.println("\n############ QUESTÃO II - Visitor ############\n");

        Playlist playlist = new Playlist("Favoritas do Fim de Semana")
                .adicionar(avulso) // item do catálogo
                .adicionar(new Episodio("The National Anthem", 4.90, "FULLHD", Timeline.padrao(44)))
                .adicionar(new MP3("Kids - MGMT (upload)", 8.4, 5))
                .adicionar(new VideoClipe("Gato tocando piano (upload)", 25.0, 2));

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
}
