import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main{

        public static char lerCaracterTeclado() {
        Scanner ler = new Scanner(System.in);
        return ler.next().charAt(0);
    }

    public static void ctrlZ(){
        Paragrafo memoria = Desfazer.pop();
        Refazer.push(memoria);
    }

    public static void ctrlY(){
        Paragrafo memoria = Refazer.pop();
        //printa
        Desfazer.push(memoria);
    }

    public static void lerTexto(){
        String comando;
        Scanner ler = new Scanner(System.in);
        String getTexto;
        getTexto = ler.nextLine();
        comando=getTexto.substring(0, 1);
        switch (comando){
            case ":Z":
                ctrlZ();
                break;
            case ":Y":
                ctrlY();
                break;
            case ":S":
                break;
        }

        Texto.inserirParagrafo(getTexto);



    }


    public static void menu() throws IOException, InterruptedException {
        System.out.println("*********** EDITOR DE TEXTO ***********");
        System.out.println("MENU");
        System.out.println("1 - Carregar texto salvo");
        System.out.println("2 - Novo texto");
        System.out.println("3 - Instruções");
        System.out.println("4 - Sair");
        System.out.println("Opção: ");

        switch (lerCaracterTeclado()) {
            case '1':
                Texto.lerArquivo();
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

    public static void main(String[] args) throws IOException, InterruptedException {
        menu();
    }
}
