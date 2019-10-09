import javax.imageio.stream.ImageInputStream;
import java.util.Scanner;

public class Main {

    public char lerCaracterTeclado() {
        Scanner ler = new Scanner(System.in);
        return ler.next().charAt(0);
    }

    public static void lerTexto(){
        Scanner ler = new Scanner(System.in);
        //ler.

    }


    public void menu() {
        System.out.println("*********** EDITOR DE TEXTO ***********");
        System.out.println("MENU");
        System.out.println("1 - Carregar texto salvo");
        System.out.println("2 - Novo texto");
        System.out.println("3 - Instruções");
        System.out.println("4 - Sair");
        System.out.println("Opção: ");

        switch (lerCaracterTeclado()) {
            case '1':

                break;
            case '2':

                break;

            case '3':
                System.out.println("    Comandos de edição:");
                System.out.println("-Desfazer= :Z");
                System.out.println("-Refazer= :Y");
                System.out.println("-Salvar= :S");
                break;

            case '4':
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {

    }
}
