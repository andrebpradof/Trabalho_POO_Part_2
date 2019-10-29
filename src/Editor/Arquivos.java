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
