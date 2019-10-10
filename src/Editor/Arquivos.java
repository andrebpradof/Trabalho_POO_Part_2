package Editor;

import java.io.*;
import java.util.LinkedList;

public class Arquivos implements Serializable {
    private static String filename = "texto.bin";
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static File verifica;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;

    public static void salvar(LinkedList<Paragrafo> texto) throws IOException {
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

    public static LinkedList ler() throws IOException {
        try{
            verifica = new File(filename);
            if(verifica.exists()){
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
