package comercial;

public interface ProdutoComercial {

    String getTitulo();

    double getPreco();

    int getDuracaoMin();

    String descrever(int nivel);

    default String descrever() {
        return descrever(0);
    }
}
