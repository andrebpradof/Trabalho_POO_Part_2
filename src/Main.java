import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static char lerCaracterTeclado() {
        Scanner ler = new Scanner(System.in);
        return ler.next().charAt(0);
    }

    public static void editor() throws IOException {
        Scanner ler = new Scanner(System.in);
        String getTexto;
        while (true) {
            getTexto = ler.nextLine();
            switch (getTexto.substring(0, 2)) {
                case ":z":
                    System.out.print(Texto.desfazer());
                    break;
                case ":y":
                    System.out.print(Texto.refazer());
                    break;
                case ":q":
                    System.out.print("\nDeseja salvar o texto? (s/n): ");
                    if(lerCaracterTeclado() == 's'){
                        Texto.salvar();
                        System.out.println("Texto salvo!");
                    }
                    return;
                default:
                    Texto.inserir(getTexto);
            }
        }
    }


    public static void menu() throws IOException, InterruptedException {
        while (true) {
            System.out.println("*********** EDITOR DE TEXTO ***********");
            System.out.println("MENU");
            System.out.println("1 - Carregar texto salvo");
            System.out.println("2 - Novo texto");
            System.out.println("3 - Continuar edição");
            System.out.println("4 - Instruções");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");

            switch (lerCaracterTeclado()) {
                case '1':
                    System.out.println("\n******* EDITOR *******");
                    Texto.limparTexto();
                    Texto.lerArquivo();
                    System.out.print(Texto.getTexto());
                    editor();
                    break;
                case '2':
                    System.out.println("\n******* EDITOR *******");
                    editor();
                    break;

                case '3':
                    System.out.println("\n******* EDITOR *******");
                    System.out.print(Texto.getTexto());
                    editor();

                case '4':
                    System.out.println("    Comandos de edição:");
                    System.out.println("-Desfazer= :z");
                    System.out.println("-Refazer= :y");
                    System.out.println("-Sair= :q");
                    break;

                case '5':
                    System.exit(0);
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        menu();
    }
}
