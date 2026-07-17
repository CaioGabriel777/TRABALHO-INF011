package app;

import comercial.CarrinhoDeCompras;
import comercial.CatalogoPromocoes;
import comercial.Filme;
import comercial.Pacote;
import comercial.PacoteBuilder;
import tecnico.Timeline;

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
    }
}
