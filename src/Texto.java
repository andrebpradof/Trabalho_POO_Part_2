import java.util.LinkedList;

public class Texto {
    private static LinkedList<Paragrafo> texto;

    public static void InserirParagrafo(Paragrafo txt){
        if (texto==null) texto = new LinkedList();
        Paragrafo paragrafo = new Paragrafo();
        texto.addLast(paragrafo);
    }
}