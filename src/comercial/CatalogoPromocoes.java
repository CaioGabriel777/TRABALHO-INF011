package comercial;

public class CatalogoPromocoes {

    public Pacote trilogiaMatrix() {
        return new PacoteBuilder("Trilogia Matrix")
                .comDesconto(0.25)
                .comFilme("Matrix", 14.90, "4K", 136)
                .comFilme("Matrix Reloaded", 14.90, "4K", 138)
                .comFilme("Matrix Revolutions", 14.90, "4K", 129)
                .comDocumentarioBonus("The Matrix Revisited", 123)
                .construir();
    }

    public Pacote trilogiaStarWars() {
        return new PacoteBuilder("Star Wars - Trilogia Original")
                .comDesconto(0.20)
                .comFilme("Uma Nova Esperança", 12.90, "HD", 121)
                .comFilme("O Império Contra-Ataca", 12.90, "HD", 124)
                .comFilme("O Retorno de Jedi", 12.90, "HD", 131)
                .construir();
    }

    public Serie blackMirrorT1() {
        return new Serie("Black Mirror - Temporada 1")
                .comEpisodio("The National Anthem", 4.90, "FULLHD", 44)
                .comEpisodio("Fifteen Million Merits", 4.90, "FULLHD", 62)
                .comEpisodio("The Entire History of You", 4.90, "FULLHD", 49);
    }

    /** Super Pacote: pacotes dentro de pacotes + série + filme avulso. */
    public Pacote colecaoSciFi() {
        return new PacoteBuilder("Coleção Sci-Fi")
                .comDesconto(0.30)
                .comPacote(trilogiaMatrix())
                .comPacote(trilogiaStarWars())
                .comSerie(blackMirrorT1())
                .comFilme("Blade Runner", 14.90, "4K", 117)
                .construir();
    }
}
