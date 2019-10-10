import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Texto {
    private static LinkedList<Paragrafo> listaParagrafo;
    private static Stack<Paragrafo> stack;

    public static void inserir(String txt){
        if (listaParagrafo == null){
            listaParagrafo = new LinkedList<>();
        }

        if(stack != null){
            stack.clear();
        }

        Paragrafo paragrafo = new Paragrafo(txt);
        listaParagrafo.addLast(paragrafo);
    }

    public static void lerArquivo() throws IOException {
        if(listaParagrafo == null)
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

    public static String getTexto() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<listaParagrafo.size();i++){
            stringBuilder.append(listaParagrafo.get(i).getParagrafo());
            if(i != listaParagrafo.size())
                stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void limparTexto(){
        if(listaParagrafo != null)
            listaParagrafo.clear();
    }

    public static String desfazer(){ //Push
        if(stack == null)
            stack = new Stack<>();
        stack.push(listaParagrafo.removeLast());
        return getTexto();
    }

    public static String refazer(){ //Pop
        if(stack != null && stack.size() != 0){
            listaParagrafo.addLast(stack.pop());
        }
        return getTexto();
    }
}