package Editor;

import javax.swing.*;
import java.awt.*;

public class InterfaceGrafica extends JFrame {
    private JMenu menuFile = new JMenu("Arquivo");
    private JMenu menuEdit = new JMenu("Editar");
    private JMenuBar barra = new JMenuBar();
    private JMenuItem menuItemAbrir = new JMenuItem("Abrir");
    private JMenuItem menuItemSalvar = new JMenuItem("Salvar");
    private JMenuItem menuItemDesfazer = new JMenuItem("Desfazer");
    private JMenuItem menuItemRefazer = new JMenuItem("Refazer");
    private JMenuItem menuItemCopiar = new JMenuItem("Copiar");
    private JMenuItem menuItemRecortar = new JMenuItem("Recortar");
    private JMenuItem menuItemRemover = new JMenuItem("Remover");
    private JPanel painel = new JPanel();
    private TextArea textArea = new TextArea();

    public InterfaceGrafica(){

    }
}
