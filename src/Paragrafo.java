import java.util.LinkedList;

public class Paragrafo {
    private LinkedList<Caracteres> paragrafo;

    public void adicionarParagrafo(String txt){
        if (paragrafo==null){
            paragrafo=new LinkedList();
        }
        for (int i=0; i<txt.length();i++){
            Caracteres caracter=new Caracteres();
            paragrafo.addLast(caracter);
        }
    }
}
