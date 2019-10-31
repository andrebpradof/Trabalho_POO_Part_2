package Editor;

import java.io.*;
import java.util.LinkedList;

import javax.swing.*;

/**
 * Classe que responsavel pelas manipulacoes de arquivos para leitura e armazenamento
 * dos textos editados pelo usuario, bem como abertura de novos arquivos
 */

public class Arquivos implements Serializable {
    private static String filename = "texto.bin";
    private static JFileChooser fileChooser;
    private static File file;
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;

    /**
     * Exibe na tela uma mensagem que pergunta se o usuario tem a intencao de salvar o arquivo novo arquivo
     * aberto apos ler o clique no item "Novo" do menu "Arquivo".
     * Em caso de resposta afirmativa, abre-se a janela para que o usuario selecione onde deseja
     * salvar o arquivo, tendo a opcao de cancelar a operacao.
     * Ja para resposta negativa, a mensagem apenas deixara a tela o novo arquivo esta pronto para
     * ser editado, porem sem estar salvo na maquina do usuario.
     * Independentemente da resposta, a area de texto da interface grafica e limpa, permitindo que o usuario
     * inicie um novo texto.
     * @param textArea Area de texto da interface grafica.
     */
    public static void saveQuestion(JTextArea textArea){
        if(textArea.getText().equals("")){
            textArea.setText("");
        }
        else {
            int resposta = JOptionPane.showConfirmDialog(InterfaceGrafica.getFrames()[0], "Gostaria de salvar o arquivo?", "Novo", JOptionPane.YES_NO_OPTION);
            if(resposta == 0)
                salvar(textArea);
            textArea.setText("");
        }
    }

    /**
     *
     * @param textArea
     */
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
                fileWriter = new FileWriter(file, true);

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
