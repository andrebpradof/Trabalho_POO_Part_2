import java.io.Serializable;
import java.util.LinkedList;

public class Paragrafo implements Serializable {
    private LinkedList<Caracteres> listaCaracteres;

    public Paragrafo(String txt){
        if (listaCaracteres==null){
            listaCaracteres= new LinkedList<>();
        }
        for (int i=0; i<txt.length();i++){
            Caracteres caracter = new Caracteres(txt.charAt(i),1);
            listaCaracteres.addLast(caracter);
        }
    }

    public String getParagrafo() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<listaCaracteres.size(); i++)
            stringBuilder.append(listaCaracteres.get(i).getCaracter());

        return stringBuilder.toString();
    }
}
