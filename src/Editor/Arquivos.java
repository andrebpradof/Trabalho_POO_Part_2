package Editor;

import java.io.*;
import java.util.LinkedList;

/**
 * Classe que realiza manipulacoes de arquivos para leitura e armazenamento dos textos editados pelo usuario
 */

public class Arquivos implements Serializable {
    private static String filename = "texto.bin";
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static File verifica;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;

    /**
     * Salva o os paragrafos no testo, isto e, na lista de paragrafos.
     * @param texto recebe como parametro a lista na qual esta sendo salvo o texto, paragrafo a paragrafo.
     * @throws IOException verifica se e possivel acressentar o paragrafo desejado a lista passada por parametro.
     */

    public static void salvar(LinkedList<Paragrafo> texto) throws IOException { // Escreve no arquivo
        try {
            fileOut = new FileOutputStream(filename);
            output = new ObjectOutputStream(new FileOutputStream(filename));
            output.writeObject(texto);
        } catch (IOException ex) {
            System.err.println("Erro ao salvar!");
        } finally {
            if (output != null && fileOut != null) {
                output.close();
                fileOut.close();
            }
        }
    }

    /**
     * Le o arquivo em que o texto foi salvo e o converte em uma forma assecivel e editavel.
     * @return retorna uma lista dos paragrafos do texto lido.
     * @throws IOException indica se não foi possivel ler o arquivo desejado, printando uma mensagem de erro
     */

    public static LinkedList ler() throws IOException {
        try{
            verifica = new File(filename);
            if(verifica.exists()){ //Se o arquivo não existir
                fileIn = new FileInputStream(filename);
                input = new ObjectInputStream(new FileInputStream(filename));
                return (LinkedList)input.readObject();
            }

        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Erro na leitura!");
            return null;
        } finally {
            if (input != null && fileIn != null) {
                input.close();
                fileIn.close();
            }
        }
        return null;
    }

}
