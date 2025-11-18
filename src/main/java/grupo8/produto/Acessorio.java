/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.produto;

public final class Acessorio extends Produto {
    
    private final int id;
    private String tipo;
    private String indicacao;
    
    public Acessorio(String tipo, String indicacao,
                    String codigo, String descricao, String fabricante, String marca, 
                    String dataFabricacao, String paisFabricacao, String material, 
                    String cor, double preco, double peso, int estoque, int garantia) {
        
        super(codigo, descricao, fabricante, marca, dataFabricacao, paisFabricacao, 
              material, cor, preco, peso, estoque, garantia);
        this.id = proximoId;
        
        setTipo(tipo);
        setIndicacao(indicacao);
    }
    
    public int getID() {
        return id;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public final void setTipo(String tipo) {
        tipo = (tipo == null ? "" : tipo.trim());
        if(tipo.isEmpty()) {
            throw new IllegalArgumentException("Tipo do acessório é obrigatório.\n");
        } else {
            this.tipo = tipo;
        }
    }
    
    public String getIndicacao() {
        return indicacao;
    }
    
    public final void setIndicacao(String indicacao) {
        indicacao = (indicacao == null ? "" : indicacao.trim());
        if(indicacao.isEmpty()) {
            throw new IllegalArgumentException("Indicação é obrigatória.\n");
        } else {
            this.indicacao = indicacao;
        }
    }
    
    @Override
    public String toString() {
        return 
            "\nAcessório [ID: " + id + "]" +
            "\nTipo: " + tipo +
            "\nIndicação: " + indicacao +
            "\nCódigo: " + getCodigo() +
            "\nDescrição: " + getDescricao() +
            "\nFabricante: " + getFabricante() +
            "\nMarca: " + getMarca() +
            "\nData Fabricação: " + getDataFabricacao() +
            "\nPaís Fabricação: " + getPaisFabricacao() +
            "\nMaterial: " + getMaterial() +
            "\nCor: " + getCor() +
            "\nPreço: R$ " + String.format("%.2f", getPreco()) +
            "\nPeso: " + getPeso() + " kg" +
            "\nEstoque: " + getEstoque() +
            "\nGarantia: " + getGarantia() + " meses" +
            "\n\n---------------------------------------------------------------";
    }
    
    public void salvar() throws java.io.FileNotFoundException, java.io.IOException {
        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ id +"_ACS.ser");
             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
}