package playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * VISITOR — Object Structure.
 * Agrega itens heterogêneos (catálogo + uploads) e sabe percorrê-los
 * aplicando qualquer visitante via double dispatch.
 */
public class Playlist {

    private final String nome;
    private final List<ItemPlaylist> itens = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = nome;
    }

    public Playlist adicionar(ItemPlaylist item) {
        itens.add(item);
        return this;
    }

    public String getNome() { return nome; }

    public void aceitar(PlaylistVisitor visitante) {
        for (ItemPlaylist item : itens) {
            item.aceitar(visitante);
        }
    }
}
