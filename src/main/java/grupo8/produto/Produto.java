package grupo8.produto;

import grupo8.pessoas.Pessoa;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Produto implements Serializable {
    public static final long serialVersionUID = 1L;
    public static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static int idProduto = 0;

    private String codigo; // alfanumérico
    private String descricao; //letras
    
    private String marca; 
    private LocalDate dataFabricacao; 
    private String paisFabricacao; 
    private String material;
    private String cor;
    private double preco;
    private int peso; //guardar em gramas, mas, exibir em quilos.
    private int qntEstoque;
    private int prazoGarantia; // Meses
    //FABRICANTE FOI PARA INSTRUMENTO
    
    public Produto(String codigo, String descricao, String marca, 
                   String dataFabricacao, String paisFabricacao, String material, 
                   String cor, String preco, String peso, String estoque, String garantia) {
                        
        setCodigo(codigo);
        setDescricao(descricao);
        setMarca(marca);
        setDataFabricacao(dataFabricacao);
        setPaisFabricacao(paisFabricacao);
        setMaterial(material);
        setCor(cor);
        setPreco(preco);
        setPeso(peso);
        setQntEstoque(estoque);
        setPrazoGarantia(garantia);
        idProduto++;
    }
    

    public int getIdProduto() {
        return idProduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public final void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite um código.\n");
        }
        
        codigo = codigo.trim();
        
        if (!codigo.matches("\\w+")){
            throw new IllegalArgumentException("Por favor, digite um código contendo somente letras e/ou números.\n");
        } 
        
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public final void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite uma descrição do produto.\n");
        }

        descricao = descricao.trim();

        if (!descricao.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Descrição inválida. Por favor, digite somente letras.\n");
        } 
        
        this.descricao = descricao;
    }


    public String getMarca() {
        return marca;
    }

    public final void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a marca do produto.\n");
        }

        marca = marca.trim();

        if (!marca.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Marca inválida. Por favor, digite somente letras.\n");
        } 
        
        this.marca = marca;
    }
    
    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public final void setDataFabricacao(String dataFabricacao) {
        if (dataFabricacao == null || dataFabricacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a marca do produto.\n");
        }

        dataFabricacao = dataFabricacao.trim();
        LocalDate hoje = LocalDate.now();
        LocalDate temp;

        try{
            temp = LocalDate.parse(dataFabricacao, formato);
        } catch(Exception e){
            throw new IllegalArgumentException("Data inválida. Por favor, use o formato DD/MM/AAAA");
        }
        
        if (temp.isAfter(hoje)){
            throw new IllegalArgumentException("A data de fabricação não pode ser futura.");
        }        
        
        this.dataFabricacao = temp;
    }

    public String getPaisFabricacao() {
        return paisFabricacao;
    }

    public final void setPaisFabricacao(String paisFabricacao) {
        if (paisFabricacao == null || paisFabricacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o país de fabricação.\n");
        }

        paisFabricacao = paisFabricacao.trim();

        if (!paisFabricacao.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("País não existe. Por favor, digite somente letras.\n");
        } 
        
        this.paisFabricacao = paisFabricacao;
    }

    public String getMaterial() {
        return material;
    }

    public final void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o material do produto.\n");
        }

        material = material.trim();

        if (!material.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Material não existe. Por favor, digite somente letras.\n");
        } 
        
        this.material = material;
    }

    public String getCor() {
        return cor;
    }

    public final void setCor(String cor) {
        if (cor == null || cor.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a cor do produto.\n");
        }

        cor = cor.trim();

        if (!cor.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Cor não existe. Por favor, digite somente letras.\n");
        } 
        
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public final void setPreco(String preco) {
        if(preco == null || preco.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o salário do funcionário.");
        }
        
        double precoFormatado;
        
        try {
            precoFormatado = Double.parseDouble(preco);
                      
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente os números do preço, usando ponto como separador decimal.");
        }
        
        if(precoFormatado <= 0) {
            throw new IllegalArgumentException("Por favor, digite um preço maior que 0.");
        }      
            
        this.preco = precoFormatado;
    }

    public double getPeso() {
        return peso;
    }

    public final void setPeso(String peso) {
        if(peso == null || peso.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o peso do produto.");
        }
        
        int pesoFormatado;
        
        try {
            pesoFormatado = Integer.parseInt(peso);
                        
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente os números do peso, em gramas.");
        }
        
        if(pesoFormatado <= 0) {
            throw new IllegalArgumentException("Por favor, digite um peso maior que 0.");
        } 

        this.peso = pesoFormatado;
    }

    public int getPrazoGarantia() {
        return prazoGarantia;
    }

    public final void setPrazoGarantia(String prazoGarantia) {
        if(prazoGarantia == null || prazoGarantia.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o prazo da garantia em meses.");
        }

        int prazoGarantiaFormatado;
        
        try {
            prazoGarantiaFormatado = Integer.parseInt(prazoGarantia);
            
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente os números .");
        }
        
        if(prazoGarantiaFormatado < 0) {
            throw new IllegalArgumentException("Por favor, digite um prazo maior que 0.");
        }

        this.prazoGarantia = prazoGarantiaFormatado;
    }
    
    public int getQntEstoque() {
        return qntEstoque;
    }

    public final void setQntEstoque(String qntEstoque) {
        if(qntEstoque == null || qntEstoque.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a quantidade de produtos no estoque.");
        }

        int qntEstoqueFormatado;
        
        try {
            qntEstoqueFormatado = Integer.parseInt(qntEstoque);
            
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente números.");
        }
        
        if(qntEstoqueFormatado <= 0) {
            throw new IllegalArgumentException("Por favor, digite um número maior que 0.");
        }

        this.qntEstoque = qntEstoqueFormatado;
    }
    
    @Override
    public String toString() {
        return
            "\n    Código: " + codigo +
            "\n    Descrição: " + descricao +
            "\n    Marca: " + marca +
            "\n    Data da Fabricação: " + dataFabricacao.format(formato) + 
            "\n    País de Fabricação: " + paisFabricacao +
            "\n    Material: " + material +
            "\n    Cor: " + cor +
            "\n    Preço: R$" + String.format("%.2f", preco) +
            "\n    Peso: " + (peso < 1000 ? String.format("%d g", peso) : String.format("%.1f kg", peso / 1000.0)) +
            "\n    Estoque: " + qntEstoque + " unidade(s)" +
            "\n    Garantia: " + (prazoGarantia == 0 ? "Expirada" : prazoGarantia + " mês(es)");
    }
    
    public static List<Produto> carregar() throws IOException, ClassNotFoundException {
        String diretorio = "src/data/";
        String padraoArquivo = "Produto\\d+_(instrumento|ACS)\\.ser";
        
        File pasta = new File(diretorio);
        Pattern pattern = Pattern.compile(padraoArquivo); 
        if (!pasta.exists()) {
            pasta.mkdirs(); // Cria a pasta e quaisquer pais necessários
        }
        File[] arquivos = pasta.listFiles((dir, name) -> pattern.matcher(name).matches()); 
        
        ArrayList<Produto> produtos = new ArrayList<>();

        if (arquivos == null) {
            System.out.println("Diretório não existe ou não contem arquivos compatíveis.");
            return produtos;
        }
        
        for (File arq : arquivos) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arq))) {
                Produto p = (Produto) ois.readObject(); 
                produtos.add(p);
            } catch (Exception e) {
                System.out.println("Erro ao ler arquivo: " + arq.getName() + "-" + e.getMessage());
            }
        }
        return produtos;
    }
}