package playlist;

/**
 * VISITOR — Element.
 * Tudo que pode entrar numa playlist (itens do catálogo OU uploads do
 * usuário) expõe um único ponto de entrada estável para operações externas.
 */
public interface ItemPlaylist {
    void aceitar(PlaylistVisitor visitante);
}
