package Editor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

/**
 * classe responsavel pela manipulacao de paragrafos em uma lista de paragrafos que formam o texto,
 * sendo assim a responsavel por realizar os comandos diretos de edicao do texto por parte do usuario
 */

public class Texto {
    private static LinkedList<Paragrafo> listaParagrafo;
    private static Stack<Paragrafo> stack;
    private static String ultimaAcao = ""; // Controle para o refazer desfazer e tipo de inserção

    /**
     * Insere uma String na lista de paragrafos
     * @param txt String para insercao
     */

    public static void inserir(String txt){
        if (listaParagrafo == null){ //Cria uma lista de paragrafos
            listaParagrafo = new LinkedList<>();
        }

        if(stack != null){ //Limpa se ja existir
            stack.clear();
        }

        if(ultimaAcao.equals(":r") && listaParagrafo.size() > 0){ //Verifica se a ultima ação foi remover
            txt = listaParagrafo.getLast().getParagrafo() + txt;
            listaParagrafo.removeLast();
        }
        Paragrafo paragrafo = new Paragrafo(txt); //Add o paragrafo
        listaParagrafo.addLast(paragrafo);
        ultimaAcao = ":i"; // Inserir
    }

    /**
     * lê a texto do aquivo e salva na lista.
     * @throws IOException caso ainda nao haja uma lista para realizar a leitura, abre-se uma nova lista.
     */

    public static void lerArquivo() throws IOException { //Le do arquivo e joga na lista
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
     * @throws IOException como o metodo "Arquivos.salvar" aqui chamado trabalha com excessao, eh dado o comando throws IOException.
     */

    public static void salvar() throws IOException { //Salva a lista
        Arquivos.salvar(listaParagrafo);
    }

    /**
     * acrescenta os paragrafos lidos ao texto (ListaParagrafo).
     * @return retorna o texto completo atual.
     */

    public static String getTexto() { //Retorna o texto inteiro da lista em uma String
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<listaParagrafo.size();i++){
            stringBuilder.append(listaParagrafo.get(i).getParagrafo());
            if(i != listaParagrafo.size()-1)
                stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Esvazia a lista na qual se encontra o texto (ListaParagrafo).
     */

    public static void limparTexto(){ //Limpa a Lista
        if(listaParagrafo != null)
            listaParagrafo.clear();
    }

    /**
     * Verifica se a Satck foi criada, caso nao, entao cria a Stack
     */
    private static void criaStack(){ //Cria a stack, caso ainda esja como null
        if(stack == null)
            stack = new Stack<>();
    }

    /**
     * Remove um caracter do paragrafo
     * @return Retorna o texto com o caracter removido
     */

    public static String remover(){
        if(listaParagrafo != null && listaParagrafo.size() > 0) {
            criaStack();
            Paragrafo paragrafo = new Paragrafo(listaParagrafo.getLast().getParagrafo());
            stack.push(paragrafo); //Coloca a alteração na Stack
            listaParagrafo.getLast().remover(); //Remove o ultimo caracter do texto
            ultimaAcao = ":r"; //Marcador
            return getTexto();
        }
        return "";
    }

    /**
     * Retira do texto o ultimo parágrafo adicionado a lista, jogando-o no topo da pilha caso se queira reescreve-lo.
     * @return retorna o texto com o paragrafo retirado.
     */

    public static String desfazer(){ //Faz push da Stack
        if(listaParagrafo != null){
            if(ultimaAcao.equals(":r")){
                if(stack != null && stack.size() != 0){
                    listaParagrafo.removeLast();
                    listaParagrafo.addLast(stack.pop());
                    ultimaAcao = ":zr";
                }
            }else {
                criaStack();
                stack.push(listaParagrafo.removeLast());
                ultimaAcao = ":z";
            }
            return getTexto();
        }
        return "";
    }

    /**
     * joga novamente na lista do texto o ultimo paragrafo excluido (pega o paragrafo do topo da pilha)
     * @return retorna o texto com o paragrafo reescrito.
     */

    public static String refazer(){ //Faz pop da stack
        if(listaParagrafo != null) {
            if (ultimaAcao.equals(":zr")) {
                remover();
            } else if(ultimaAcao.equals(":z")) {
                if (stack != null && stack.size() != 0) {
                    listaParagrafo.addLast(stack.pop());
                    ultimaAcao = ":y";
                }
            }
            return getTexto();
        }
        return "";
    }
}