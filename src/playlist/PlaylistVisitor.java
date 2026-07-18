package playlist;


import comercial.Episodio;
import comercial.Filme;

/**
 * VISITOR — Visitor.
 * Cada nova operação analítica pedida pela equipe de Ciência de Dados vira
 * uma nova implementação desta interface — sem tocar em MP3, VideoClipe,
 * Filme ou Episodio (Aberto/Fechado + Responsabilidade Única).
 */
public interface PlaylistVisitor {
    void visitar(Filme filme);
    void visitar(Episodio episodio);
    void visitar(MP3 mp3);
    void visitar(VideoClipe clipe);
}
