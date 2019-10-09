import java.util.LinkedList;

public class Texto {
    private static LinkedList<Paragrafo> texto;

    public static void inserirParagrafo(String txt){
        if (texto==null)
            texto = new LinkedList();

        Paragrafo paragrafo = new Paragrafo();
        paragrafo.adicionarParagrafo(txt);
        texto.addLast(paragrafo);
    }
}