/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package produto;

import java.io.Serializable;

public class Produto implements Serializable {
    public static final long serialVersionUID = 1L;
    private static int qntProduto = 0;
    private static int proximoId = 1;
    private static int estoqueMaximo = 1000; //statico sem ser contador...

    public static int getQntProduto() {
        return qntProduto;
    }

    public static void setQntProduto(int aQntProduto) {
        qntProduto = aQntProduto;
    }
    
    public static int getEstoqueMaximo() {
        return estoqueMaximo;
    }
    
    public static void setEstoqueMaximo(int estoqueMaximo) {
        if (estoqueMaximo <= 0) {
            throw new IllegalArgumentException("Estoque máximo deve ser positivo.\n");
        }
        Produto.estoqueMaximo = estoqueMaximo;
    }
    
    public static boolean validarCodigo(String codigo) {
        codigo = (codigo == null ? "" : codigo.trim());
        if (codigo.isEmpty()) {
            return false;
        }
        return codigo.matches("[A-Z0-9]{3,10}");
    }
    
    public static boolean estoqueExcedido(int estoqueAtual) { // ✅ MÉTODO STATIC ADICIONAL
        return estoqueAtual > estoqueMaximo;
    }
    
    private final int id;
    private String codigo;
    private String descricao;
    private String fabricante;
    private String marca;
    private String dataFabricacao;
    private String paisFabricacao;
    private String material;
    private String cor;
    private double preco;
    private double peso;
    private int estoque;
    private int garantia;
    
    public Produto(String codigo, String descricao, String fabricante, String marca, 
                   String dataFabricacao, String paisFabricacao, String material, 
                   String cor, double preco, double peso, int estoque, int garantia) {
        
        this.id = proximoId++;
        qntProduto++;
        
        setCodigo(codigo);
        setDescricao(descricao);
        setFabricante(fabricante);
        setMarca(marca);
        setDataFabricacao(dataFabricacao);
        setPaisFabricacao(paisFabricacao);
        setMaterial(material);
        setCor(cor);
        setPreco(preco);
        setPeso(peso);
        setEstoque(estoque);
        setGarantia(garantia);
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public final void setCodigo(String codigo) {
        codigo = (codigo == null ? "" : codigo.trim());
        if (codigo.isEmpty()) {
            throw new IllegalArgumentException("Código é obrigatório.\n");
        } else if (!codigo.matches("[A-Z0-9]{3,10}")) {
            throw new IllegalArgumentException("Código deve ter 3-10 caracteres (A-Z, 0-9).\n");
        } else {
            this.codigo = codigo;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public final void setDescricao(String descricao) {
        descricao = (descricao == null ? "" : descricao.trim());
        if (descricao.isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória.\n");
        } else if (descricao.length() < 3) {
            throw new IllegalArgumentException("Descrição deve ter pelo menos 3 caracteres.\n");
        } else {
            this.descricao = descricao;
        }
    }

    public String getFabricante() {
        return fabricante;
    }

    public final void setFabricante(String fabricante) {
        fabricante = (fabricante == null ? "" : fabricante.trim());
        if (fabricante.isEmpty()) {
            throw new IllegalArgumentException("Fabricante é obrigatório.\n");
        } else if (fabricante.length() < 2) {
            throw new IllegalArgumentException("Fabricante deve ter pelo menos 2 caracteres.\n");
        } else {
            this.fabricante = fabricante;
        }
    }

    public String getMarca() {
        return marca;
    }

    public final void setMarca(String marca) {
        marca = (marca == null ? "" : marca.trim());
        if (marca.isEmpty()) {
            throw new IllegalArgumentException("Marca é obrigatória.\n");
        } else if (marca.length() < 2) {
            throw new IllegalArgumentException("Marca deve ter pelo menos 2 caracteres.\n");
        } else {
            this.marca = marca;
        }
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public final void setDataFabricacao(String dataFabricacao) {
        dataFabricacao = (dataFabricacao == null ? "" : dataFabricacao.trim());
        if (dataFabricacao.isEmpty()) {
            throw new IllegalArgumentException("Data de fabricação é obrigatória.\n");
        } else if (!dataFabricacao.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("Data deve estar no formato DD/MM/AAAA.\n");
        } else {
            this.dataFabricacao = dataFabricacao;
        }
    }

    public String getPaisFabricacao() {
        return paisFabricacao;
    }

    public final void setPaisFabricacao(String paisFabricacao) {
        paisFabricacao = (paisFabricacao == null ? "" : paisFabricacao.trim());
        if (paisFabricacao.isEmpty()) {
            throw new IllegalArgumentException("País de fabricação é obrigatório.\n");
        } else if (paisFabricacao.length() < 3) {
            throw new IllegalArgumentException("Digite o nome completo do país.\n");
        } else {
            this.paisFabricacao = paisFabricacao;
        }
    }

    public String getMaterial() {
        return material;
    }

    public final void setMaterial(String material) {
        material = (material == null ? "" : material.trim());
        if (material.isEmpty()) {
            throw new IllegalArgumentException("Material é obrigatório.\n");
        } else {
            this.material = material;
        }
    }

    public String getCor() {
        return cor;
    }

    public final void setCor(String cor) {
        cor = (cor == null ? "" : cor.trim());
        if (cor.isEmpty()) {
            throw new IllegalArgumentException("Cor é obrigatória.\n");
        } else {
            this.cor = cor;
        }
    }

    public double getPreco() {
        return preco;
    }

    public final void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.\n");
        } else if (preco == 0) {
            throw new IllegalArgumentException("Preço não pode ser zero.\n");
        } else {
            this.preco = preco;
        }
    }

    public double getPeso() {
        return peso;
    }

    public final void setPeso(double peso) {
        if (peso < 0) {
            throw new IllegalArgumentException("Peso não pode ser negativo.\n");
        } else {
            this.peso = peso;
        }
    }

    public int getEstoque() {
        return estoque;
    }

    public final void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.\n");
        } else if (estoque > estoqueMaximo) {
            throw new IllegalArgumentException("Estoque não pode exceder " + estoqueMaximo + " unidades.\n");
        } else {
            this.estoque = estoque;
        }
    }

    public int getGarantia() {
        return garantia;
    }

    public final void setGarantia(int garantia) {
        if (garantia < 0) {
            throw new IllegalArgumentException("Garantia não pode ser negativa.\n");
        } else {
            this.garantia = garantia;
        }
    }
    
    @Override
    public String toString() {
        
        return String.format("Produto[ID: %d, Codigo: %s, Descricao: %s, Preco: R$ %.2f]", id, codigo, descricao, preco);
    }
}