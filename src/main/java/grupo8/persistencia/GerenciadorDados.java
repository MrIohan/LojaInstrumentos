package grupo8.persistencia; // Ou utils

import grupo8.produto.Produto;
import java.io.*;
import java.util.ArrayList;

public class GerenciadorDados {
    
    private String caminhoArquivo = "dados_produtos.dat";

    // Salva a lista inteira no arquivo
    public void salvarLista(ArrayList<Produto> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    // Carrega a lista do arquivo (se não existir, retorna lista vazia)
    public ArrayList<Produto> carregarLista() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return new ArrayList<>(); // Retorna lista vazia se é a 1ª vez
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Produto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}