package Editor;

import java.io.*;
import java.util.LinkedList;

import javax.swing.*;

/**
 * Classe que realiza manipulacoes de arquivos para leitura e armazenamento dos textos editados pelo usuario
 */

public class Arquivos implements Serializable {
    private static String filename = "texto.bin";
    private static JFileChooser fileChooser;
    private static File file;
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;



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


    public static void saveQuestion(JTextArea textArea){
        if(textArea.getText().equals("")){
            textArea.setText("");
        }
        else {
            int resposta = JOptionPane.showConfirmDialog(InterfaceGrafica.getFrames()[0], "Gostaria de salvar o arquivo?", "Novo", JOptionPane.YES_NO_OPTION);
            if(resposta == 0)
                salvar(textArea);
            else
                textArea.setText("");
        }
    }

    public static void novo(JTextArea textArea){
        saveQuestion(textArea);
    }

    public static void salvar(JTextArea textArea){
        // Create an object of JFileChooser class
        fileChooser = new JFileChooser("f:");

        // Invoke the showsSaveDialog function to show the save dialog
        int resposta = fileChooser.showSaveDialog(null);

        if (resposta == JFileChooser.APPROVE_OPTION) {

            // Set the label to the path of the selected directory
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            try {
                // Create a file writer
                fileWriter = new FileWriter(file, false);

                // Create buffered writer to write
                bufferedWriter = new BufferedWriter(fileWriter);

                // Write
                bufferedWriter.write(textArea.getText());

                bufferedWriter.flush();
                bufferedWriter.close();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(InterfaceGrafica.getFrames()[0], evt.getMessage());
            }
        }
        // If the user cancelled the operation
        else
            JOptionPane.showMessageDialog(InterfaceGrafica.getFrames()[0], "Operação cancelada");
    }

    public static void abrir(JTextArea textArea){
            fileChooser = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the save dialog 
            int resposta = fileChooser.showOpenDialog(null);

            // If the user selects a file 
            if (resposta == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory 
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    // String 
                    String s1 = "", sl = "";

                    // File reader 
                    fileReader = new FileReader(file);

                    // Buffered reader 
                    bufferedReader = new BufferedReader(fileReader);

                    // Initilize sl
                    sl = bufferedReader.readLine();

                    // Take the input from the file
                    while ((s1 = bufferedReader.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text 
                    textArea.setText(sl);
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(InterfaceGrafica.getFrames()[0], evt.getMessage());
                }
            }
            // If the user cancelled the operation 
            else
                JOptionPane.showMessageDialog(InterfaceGrafica.getFrames()[0], "Operação cancelada");
    }
}
