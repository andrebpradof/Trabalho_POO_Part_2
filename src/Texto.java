import java.io.IOException;
import java.util.LinkedList;

public class Texto {
    private static LinkedList<Paragrafo> listaParagrafo;

    public static void inserirParagrafo(String txt){
        if (listaParagrafo == null)
            listaParagrafo = new LinkedList<>();

        Paragrafo paragrafo = new Paragrafo();
        paragrafo.adicionarParagrafo(txt);
        listaParagrafo.addLast(paragrafo);
    }

    public static void lerArquivo() throws IOException {
        listaParagrafo = new LinkedList<>();
        LinkedList<Paragrafo> aux;
        aux = Arquivos.ler();
        if(aux != null){
            listaParagrafo.addAll(aux);
        }
    }

    public static void salvar() throws IOException {
        Arquivos.salvar(listaParagrafo);
    }
}