package playlist;

import comercial.Episodio;
import comercial.Filme;

import java.util.Locale;

/** VISITOR — ConcreteVisitor. Pedido mais recente da equipe de dados: "Exportador para XML". */
public class ExportadorXML implements PlaylistVisitor {

    private final StringBuilder corpo = new StringBuilder();

    @Override
    public void visitar(Filme filme) {
        corpo.append(String.format(Locale.US,
                "  <filme titulo=\"%s\" resolucao=\"%s\" duracaoMin=\"%d\" preco=\"%.2f\"/>%n",
                filme.getTitulo(), filme.getResolucao(), filme.getDuracaoMin(), filme.getPreco()));
    }

    @Override
    public void visitar(Episodio episodio) {
        corpo.append(String.format(Locale.US,
                "  <episodio titulo=\"%s\" resolucao=\"%s\" duracaoMin=\"%d\" preco=\"%.2f\"/>%n",
                episodio.getTitulo(), episodio.getResolucao(), episodio.getDuracaoMin(), episodio.getPreco()));
    }

    @Override
    public void visitar(MP3 mp3) {
        corpo.append(String.format(Locale.US,
                "  <mp3 titulo=\"%s\" tamanhoMB=\"%.1f\" duracaoMin=\"%d\"/>%n",
                mp3.getTitulo(), mp3.getTamanhoMB(), mp3.getDuracaoMin()));
    }

    @Override
    public void visitar(VideoClipe clipe) {
        corpo.append(String.format(Locale.US,
                "  <videoclipe titulo=\"%s\" tamanhoMB=\"%.1f\" duracaoMin=\"%d\"/>%n",
                clipe.getTitulo(), clipe.getTamanhoMB(), clipe.getDuracaoMin()));
    }

    public String exportar(String nomePlaylist) {
        return "<playlist nome=\"" + nomePlaylist + "\">" + System.lineSeparator()
                + corpo + "</playlist>";
    }
}
