package Editor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

public class Texto {
    private static LinkedList<Paragrafo> listaParagrafo;
    private static Stack<Paragrafo> stack;

    /**
     * Insere uma String na lista de paragrafos
     * @param txt String para insercao
     */

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

    /**
     * lê a texto do aquivo e salva na lista.
     * @throws IOException caso ainda nao haja uma lista para realizar a leitura, abre-se uma nova lista.
     */

    public static void lerArquivo() throws IOException {
        if(listaParagrafo == null)
            listaParagrafo = new LinkedList<>();
        LinkedList<Paragrafo> aux;
        aux = Arquivos.ler();
        if(aux != null){
            listaParagrafo.addAll(aux);
        }
    }

    /**
     * Comando para salvar a lista com o texto no arquivo.
     * @throws IOException como o metodo "Arquivos.salvar" aqui chamado trabalha com excessão, é dado o comando throws IOException.
     */

    public static void salvar() throws IOException {
        Arquivos.salvar(listaParagrafo);
    }

    /**
     * acrescenta os paragrafos lidos ao texto (ListaParagrafo).
     * @return retorna o texto completo atual.
     */

    public static String getTexto() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<listaParagrafo.size();i++){
            stringBuilder.append(listaParagrafo.get(i).getParagrafo());
            if(i != listaParagrafo.size())
                stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Esvazia a lista na qual se encontra o texto (ListaParagrafo).
     */

    public static void limparTexto(){
        if(listaParagrafo != null)
            listaParagrafo.clear();
    }

    /**
     * Retira do texto o último parágrafo adicionado à lista, jogando-o no topo da pilha caso se queira reescrevê-lo.
     * @return retorna o texto com o parágrafo retirado.
     */

    public static String desfazer(){ //Push
        if(stack == null)
            stack = new Stack<>();
        stack.push(listaParagrafo.removeLast());
        return getTexto();
    }

    /**
     * joga novamente na lista do texto o ultimo paragrafo excluido (pega o paragrafo do topo da pilha)
     * @return retorna o texto com o paragrafo reescrito.
     */

    public static String refazer(){ //Pop
        if(stack != null && stack.size() != 0){
            listaParagrafo.addLast(stack.pop());
        }
        return getTexto();
    }
}