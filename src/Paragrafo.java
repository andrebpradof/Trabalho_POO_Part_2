import java.io.Serializable;
import java.util.LinkedList;

public class Paragrafo implements Serializable {
    private LinkedList<Caracteres> listaCaracteres;

    public void adicionarParagrafo(String txt){
        if (listaCaracteres==null){
            listaCaracteres= new LinkedList<>();
        }
        for (int i=0; i<txt.length();i++){
            Caracteres caracter=new Caracteres();
            listaCaracteres.addLast(caracter);
        }
    }
}
