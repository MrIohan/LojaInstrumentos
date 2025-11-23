package grupo8.persistencia;

import grupo8.produto.Produto;
import grupo8.pessoas.Pessoa;
import grupo8.vendas.Venda;
import java.io.*;
import java.util.ArrayList;

public class GerenciadorDados {
    
    private String caminhoArquivo = "src/main/java/grupo8/dados/produtos.dat";
    private String caminhoPessoas = "src/main/java/grupo8/dados/pessoas.dat";
    private String caminhoVendas = "src/main/java/grupo8/dados/vendas.dat";

    // --- PRODUTOS ---
    public void salvarLista(ArrayList<Produto> lista) {
        File arquivo = new File(caminhoArquivo);
        if (arquivo.getParentFile() != null) arquivo.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public ArrayList<Produto> carregarLista() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            ArrayList<Produto> lista = (ArrayList<Produto>) ois.readObject();
            
            // --- CORREÇÃO DO ID ---
            // Descobre qual o maior ID que já existe para continuar a contagem de lá
            int maiorId = 0;
            for (Produto p : lista) {
                if (p.getIdProduto() > maiorId) {
                    maiorId = p.getIdProduto();
                }
            }
            
            return lista;
        } catch (Exception e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // ... (Mantenha os métodos de Pessoa e Venda iguais) ...
    
    // --- PESSOAS ---
    public void salvarPessoas(ArrayList<Pessoa> lista) {
        File arquivo = new File(caminhoPessoas);
        if (arquivo.getParentFile() != null) arquivo.getParentFile().mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro salvar pessoa: " + e.getMessage());
        }
    }

    public ArrayList<Pessoa> carregarPessoas() {
        File arquivo = new File(caminhoPessoas);
        if (!arquivo.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Pessoa>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // --- VENDAS ---
    public void salvarVendas(ArrayList<Venda> lista) {
        File arquivo = new File(caminhoVendas);
        if (arquivo.getParentFile() != null) arquivo.getParentFile().mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro salvar venda: " + e.getMessage());
        }
    }

    public ArrayList<Venda> carregarVendas() {
        File arquivo = new File(caminhoVendas);
        if (!arquivo.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Venda>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}