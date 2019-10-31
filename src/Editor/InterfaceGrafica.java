package Editor;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 * Classe com a interface grafica, suas funcionalidades de edição de texto e
 * comando de fechamento e as chamadas para as funcionalidades de manipulaçãod e arquivos.
 */

public class InterfaceGrafica extends JFrame{

    private JMenu menuFile = new JMenu("Arquivo");
    private JMenu menuEdit = new JMenu("Editar");
    private JMenu menuOpcao = new JMenu("Opções");
    private JMenuItem menuItemSair = new JMenuItem("Sair");
    private JMenuBar barra = new JMenuBar();
    private JMenuItem menuItemNovo = new JMenuItem("Novo");
    private JMenuItem menuItemColar = new JMenuItem("Colar");
    private JMenuItem menuItemAbrir = new JMenuItem("Abrir");
    private JMenuItem menuItemSalvar = new JMenuItem("Salvar");
    private JMenuItem menuItemDesfazer = new JMenuItem("Desfazer");
    private JMenuItem menuItemRefazer = new JMenuItem("Refazer");
    private JMenuItem menuItemCopiar = new JMenuItem("Copiar");
    private JMenuItem menuItemRecortar = new JMenuItem("Recortar");
 //   private JMenuItem menuItemRemover = new JMenuItem("Remover");
    private JMenuItem menuItemSelecionar = new JMenuItem("Selecionar");
    private JScrollPane scrollPane;
    private JTextArea textArea = new JTextArea();
    private UndoManager undoManager = new UndoManager();

    /** Construtor da classe InterfaceGrafica.
     * Inicializa a interface, estrutura a janela com a area de texto e a barra de ferramentas, com cada um dos
     * menus nela presentes, nos quais sao organizados seus respectivos itens.
     * Implementa as funcionalidades dos itens de edicao de texto e do item "Sair",
     * alem de chamar os metodos da classe "Arqivos" que implementam as funcionalidades
     * dos itens de manipulacao de arquivo.
     */

    public InterfaceGrafica(){
        super("Editor de texto");

        this.setLayout(new BorderLayout());

        menuFile.add(menuItemNovo);
        menuFile.add(menuItemAbrir);
        menuFile.add(menuItemSalvar);

        menuOpcao.add(menuItemSair);

        menuItemDesfazer.setEnabled(false);
        menuItemRefazer.setEnabled(false);

        menuEdit.add(menuItemCopiar);
        menuEdit.add(menuItemColar);
        menuEdit.add(menuItemRecortar);
        menuEdit.add(menuItemSelecionar);
      //  menuEdit.add(menuItemRemover);
        menuEdit.add(menuItemDesfazer);
        menuEdit.add(menuItemRefazer);

        barra.add(menuFile);
        barra.add(menuEdit);
        barra.add(menuOpcao);

        textArea.setEditable(true);
        textArea.setBorder(new EmptyBorder(5,5,5,5));

        this.setJMenuBar(barra);
        scrollPane = new JScrollPane(textArea);
        this.getContentPane().add(scrollPane,BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);

        menuItemNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arquivos.novo(textArea);
            }
        });

        menuItemAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arquivos.abrir(textArea);
            }
        });

        menuItemSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arquivos.salvar(textArea);
            }
        });

        menuItemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuItemCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        menuItemColar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        menuItemRecortar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });
/*
        menuItemRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));
            }
        });
*/
        menuItemDesfazer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    undoManager.undo();
                }
                catch (CannotUndoException cre){
                    cre.printStackTrace();
                }
                updateMenuItens();
            }
        });

        menuItemRefazer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    undoManager.redo();
                }
                catch (CannotRedoException cre){
                    cre.printStackTrace();
                }
                updateMenuItens();
            }
        });

        menuItemSelecionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();
            }
        });

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
                updateMenuItens();
            }
        });
    }

    private void updateMenuItens(){
        //this.menuItemDesfazer.setText(undoManager.getUndoPresentationName());
        //this.menuItemRefazer.setText(undoManager.getRedoPresentationName());
        this.menuItemDesfazer.setEnabled(undoManager.canUndo());
        this.menuItemRefazer.setEnabled(undoManager.canRedo());
    }
}
