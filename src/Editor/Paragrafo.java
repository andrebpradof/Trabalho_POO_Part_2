package Editor;

import java.io.Serializable;
import java.util.LinkedList;

public class Paragrafo implements Serializable {
    private LinkedList<Caracteres> listaCaracteres;
    /**
     * acrescenta os caracteres lidos ao paragrafo (listaCaracteres).
     * @param txt string contendo os caracteres que serao listados no paragrafo.
     */
    public Paragrafo(String txt){
        if (listaCaracteres==null){
            listaCaracteres= new LinkedList<>();
        }
        for (int i=0; i<txt.length();i++){
            Caracteres caracter = new Caracteres(txt.charAt(i),1);
            listaCaracteres.addLast(caracter);
        }
    }

    /**
     * Le a lista de caracteres do paragrafo e converte em texto na forma de string.
     * @return retorna a string com o texto do paragrafo.
     */

    public String getParagrafo() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<listaCaracteres.size(); i++)
            stringBuilder.append(listaCaracteres.get(i).getCaracter());

        return stringBuilder.toString();
    }
}
