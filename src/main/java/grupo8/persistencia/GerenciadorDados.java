    package grupo8.persistencia;

import grupo8.produto.Produto;
import java.io.*;
import java.util.ArrayList;

public class GerenciadorDados {
    
    private String caminhoArquivo = "src/main/java/grupo8/dados/produtos.dat";

    // Salva a lista inteira no arquivo
    public void salvarLista(ArrayList<Produto> lista) {
        File arquivo = new File(caminhoArquivo);
        
        // ADICIONE ESTA LINHA PARA DEPURAR:
        System.out.println("Tentando salvar em: " + arquivo.getAbsolutePath());

        // --- CORREÇÃO: Cria as pastas se elas não existirem ---
        if (arquivo.getParentFile() != null) {
            arquivo.getParentFile().mkdirs(); 
        }
        // ------------------------------------------------------

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
            // E ESTA AQUI TAMBÉM:
            System.out.println("Arquivo gravado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            e.printStackTrace(); // Ajuda a ver o erro exato no console
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