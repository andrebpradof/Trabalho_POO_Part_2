package Editor;

import javax.swing.*;
import java.awt.*;

public class InterfaceGrafica extends JFrame {
    private JMenu menuFile = new JMenu("Arquivo");
    private JMenu menuEdit = new JMenu("Editar");
    private JMenu menuSair = new JMenu("Sair");
    private JMenuBar barra = new JMenuBar();
    private JMenuItem menuItemColar = new JMenuItem("Colar");
    private JMenuItem menuItemAbrir = new JMenuItem("Abrir");
    private JMenuItem menuItemSalvar = new JMenuItem("Salvar");
    private JMenuItem menuItemFechar = new JMenuItem("Fechar");
    private JMenuItem menuItemDesfazer = new JMenuItem("Desfazer");
    private JMenuItem menuItemRefazer = new JMenuItem("Refazer");
    private JMenuItem menuItemCopiar = new JMenuItem("Copiar");
    private JMenuItem menuItemRecortar = new JMenuItem("Recortar");
    private JMenuItem menuItemRemover = new JMenuItem("Remover");
    private JPanel painel = new JPanel();
    private TextArea textArea = new TextArea();
    private JScrollPane scrollPane = new JScrollPane();

    public InterfaceGrafica(){
        super("Editor de texto");

        this.setLayout(new BorderLayout());

        barra.add(menuFile);
        barra.add(menuEdit);
        barra.add(menuSair);

        menuFile.add(menuItemAbrir);
        menuFile.add(menuItemSalvar);
        menuFile.add(menuItemFechar);

        menuEdit.add(menuItemCopiar);
        menuEdit.add(menuItemColar);
        menuEdit.add(menuItemRecortar);
        menuEdit.add(menuItemRemover);
        menuEdit.add(menuItemDesfazer);
        menuEdit.add(menuItemRefazer);

        textArea.setEditable(true);
        scrollPane.add(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(barra, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 700);

    }
}
