import java.util.LinkedList;

public class Texto {
    private static LinkedList<Caracteres> texto;

    public static void InserirTxt(String txt){
        if (texto==null){
            texto=new LinkedList();
        }
        for (int i=0; i<txt.length();i++){
            Caracteres caracter=new Caracteres();
            texto.add(caracter);
        }
    }
}