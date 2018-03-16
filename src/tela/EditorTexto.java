package tela;

import arquivo.Arquivo;

import javax.swing.*;
import java.awt.*;


public class EditorTexto extends JFrame{

    private JTextArea campoTexto;
    private String caminhoArquivo;
    private static final Arquivo arquivo = new Arquivo();

    public EditorTexto() {

        //Setup da janela
        super("Editor Texto - Novo Arquivo");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();

        //Area de escrita
        campoTexto = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(campoTexto);
        c.add(jScrollPane);

        //Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArquivo = new JMenu("Arquivo");

        JMenuItem menuItemNovo = new JMenuItem("Novo");
        menuItemNovo.addActionListener(  e -> {
            setTitle("Editor Texto - Novo Arquivo");
            campoTexto.setText("");
            caminhoArquivo = null;
        });
        menuArquivo.add(menuItemNovo);

        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        menuItemAbrir.addActionListener( e -> {

            caminhoArquivo = arquivo.dialogDiretorioAbrirArquivo(this);
            if(caminhoArquivo != null){
                setTitle("Editor Texto - " + caminhoArquivo);
                campoTexto.setText(arquivo.lerArquivo(caminhoArquivo));
            }
        });
        menuArquivo.add(menuItemAbrir);

        JMenuItem menuItemSalvar = new JMenuItem("Salvar");
        menuItemSalvar.addActionListener( e -> {

            if(caminhoArquivo == null){
               caminhoArquivo = arquivo.dialogDiretorioSalvarArquivo(this);

               if (caminhoArquivo != null){
                   setTitle("Editor Texto - " + caminhoArquivo);
               }
            }

            arquivo.gravarArquivo(caminhoArquivo, campoTexto.getText());
        });
        menuArquivo.add(menuItemSalvar);

        menuArquivo.addSeparator();

        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuItemSair.addActionListener( e -> {
            System.exit(0);
        });
        menuArquivo.add(menuItemSair);

        menuBar.add(menuArquivo);

        JMenu menuSobre = new JMenu("Sobre");

        JMenuItem menuItemSobre = new JMenuItem("Sobre o Editor");
        menuItemSobre.addActionListener( e -> {
            JOptionPane.showMessageDialog(null, "Autor: Marcelo Rezin\nVersão: 0.0.1", "Sobre o Editor", JOptionPane.INFORMATION_MESSAGE);
        });
        menuSobre.add(menuItemSobre);

        menuBar.add(menuSobre);

        setJMenuBar(menuBar);

    }

    public static void main(String[] args){
        new EditorTexto().setVisible(true);
    }
}
