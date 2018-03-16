package arquivo;

import javax.swing.*;
import java.io.*;

public class Arquivo {

    public void gravarArquivo(String nomeArquivo, String textoArquivo){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        if(nomeArquivo == null){
            return;
        }
        try {
            fileWriter = new FileWriter(nomeArquivo, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textoArquivo);
            bufferedWriter.flush();
            //Se chegou ate essa linha, conseguiu salvar o arquivo com sucesso.
                    JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo: " + ex.getMessage());
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo: " + ex.getMessage());
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo: " + ex.getMessage());
                }
            }
        }
    }

    public String lerArquivo(String caminhoArquivo){
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(caminhoArquivo);
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            while (bufferedReader.ready()) {
                sb.append(bufferedReader.readLine());
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo: " + ex.getMessage(
            ));
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo: " + ex.getMessage());
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo: " + ex.getMessage());
                }
            }
        }
        return null;
    }

    public String dialogDiretorioAbrirArquivo(JFrame frame){
        JFileChooser fc = new JFileChooser();
        //Exibe o diálogo. Deve ser passado por parâmetro o JFrame de origem.
        fc.showOpenDialog(frame);
        //Captura o objeto File que representa o arquivo selecionado.
        File selFile = fc.getSelectedFile();
        return selFile != null ? selFile.getAbsolutePath() : null;
    }

    public String dialogDiretorioSalvarArquivo(JFrame frame){
        //Exibe o diálogo. Deve ser passado por parâmetro o JFrame de origem.
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(frame);
        //Captura o objeto File que representa o arquivo selecionado.
        File selFile = fc.getSelectedFile();
        return selFile != null ? selFile.getAbsolutePath() : null;
    }
}
