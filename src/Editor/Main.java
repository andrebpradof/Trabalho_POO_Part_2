package Editor;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     * le o caracter digitado
     * @return retorna o caracter lido em um char
     */

    public static char lerCaracterTeclado() { //Le um caracter do teclado
        Scanner ler = new Scanner(System.in);
        return ler.next().charAt(0);
    }

    /**
     * Le os comandos do usuario e realiza as tarefas de edicoes compativeis com tais comandos
     * @throws IOException tratamento de excessao para o salvamento do texto
     */

    public static void editor() throws IOException { //Realiza as funções do editor
        Scanner ler = new Scanner(System.in);
        String opcao = "";
        String getTexto;

        while (true) {
            getTexto = ler.nextLine(); //Pega o texto

            if(getTexto.length() > 1) //Para verificar as opções abaixo (como o VIM)
                opcao = getTexto.substring(0, 2);

            switch (opcao) {
                case ":z": //Desfazer
                    System.out.print(Texto.desfazer());
                    break;
                case ":y": //Refazer
                    System.out.print(Texto.refazer());
                    break;
                case ":q": //Sair e pode salvar
                    System.out.print("\nDeseja salvar o texto? (s/n): ");
                    if(lerCaracterTeclado() == 's'){
                        Texto.salvar();
                        System.out.println("Editor.Texto salvo!");
                    }
                    return;
                default:
                    Texto.inserir(getTexto);
            }
        }
    }

    /**
     * Exibe as opcoes de navegacao do usuario no programa, le o comando do usuario e realiza/chama a opcao desejada.
     * @throws IOException tratamento de erro para as tarefas envolvendo arquivos.
     * @throws InterruptedException tratamento de erro ao sair-se do programa.
     */

    public static void menu() throws IOException, InterruptedException { //Menu do editor
        while (true) {
            System.out.println("*********** EDITOR DE TEXTO ***********");
            System.out.println("MENU");
            System.out.println("1 - Novo texto");
            System.out.println("2 - Carregar texto salvo");
            System.out.println("3 - Continuar edição");
            System.out.println("4 - Salvar");
            System.out.println("5 - Instruções");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");

            switch (lerCaracterTeclado()) {
                case '1':
                    Texto.limparTexto(); //Limpa a lista
                    System.out.println("\n******* EDITOR *******");
                    editor(); //Chama o editor
                    break;

                case '2':
                    System.out.println("\n******* EDITOR *******");
                    Texto.limparTexto(); //Limpa a lista
                    Texto.lerArquivo(); //Le um arquivo ja existente
                    System.out.print(Texto.getTexto()); //Imprime o que foi lido
                    editor(); //Chama o editor
                    break;

                case '3':
                    System.out.println("\n******* EDITOR *******");
                    System.out.print(Texto.getTexto()); //Só imprime o q ja estava na lista
                    editor();

                case '4':
                    Texto.salvar(); // Salva
                    System.out.println("Texto salvo!");
                    break;

                case '5':
                    System.out.println("    Comandos de edição:");
                    System.out.println("-Desfazer= :z");
                    System.out.println("-Refazer= :y");
                    System.out.println("-Sair= :q");
                    break;

                case '6':
                    System.exit(0);
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        menu();
    }
}
