/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.produto;

public final class Instrumento extends Produto {
    
    private final int id;
    private String numSerie;
    private String tipoSaida;
    private String nivelProfissional;
    
    public Instrumento(String numSerie, String tipoSaida, String nivelProfissional,
                      String codigo, String descricao, String fabricante, String marca, 
                      String dataFabricacao, String paisFabricacao, String material, 
                      String cor, double preco, double peso, int estoque, int garantia) {
        
        super(codigo, descricao, fabricante, marca, dataFabricacao, paisFabricacao, 
              material, cor, preco, peso, estoque, garantia);
        this.id = proximoId;
        
        setNumSerie(numSerie);
        setTipoSaida(tipoSaida);
        setNivelProfissional(nivelProfissional);
    }
    
    public int getID() {
        return id;
    }
    
    public String getNumSerie() {
        return numSerie;
    }
    
    public final void setNumSerie(String numSerie) {
        numSerie = (numSerie == null ? "" : numSerie.trim());
        if(numSerie.isEmpty()) {
            throw new IllegalArgumentException("Número de série é obrigatório.\n");
        } else if(!numSerie.matches("[A-Z0-9]{5,15}")) {
            throw new IllegalArgumentException("Número de série deve conter 5-15 caracteres alfanuméricos.\n");
        } else {
            this.numSerie = numSerie;
        }
    }
    
    public String getTipoSaida() {
        return tipoSaida;
    }
    
    public final void setTipoSaida(String tipoSaida) {
        tipoSaida = (tipoSaida == null ? "" : tipoSaida.trim());
        if(tipoSaida.isEmpty()) {
            throw new IllegalArgumentException("Tipo de saída é obrigatório.\n");
        } else {
            this.tipoSaida = tipoSaida;
        }
    }
    
    public String getNivelProfissional() {
        return nivelProfissional;
    }
    
    public final void setNivelProfissional(String nivelProfissional) {
        nivelProfissional = (nivelProfissional == null ? "" : nivelProfissional.trim());
        if(nivelProfissional.isEmpty()) {
            throw new IllegalArgumentException("Nível profissional é obrigatório.\n");
        } else if(!nivelProfissional.matches("(?i)iniciante|intermediario|profissional")) {
            throw new IllegalArgumentException("Nível profissional deve ser: Iniciante, Intermediário ou Profissional.\n");
        } else {
            this.nivelProfissional = nivelProfissional;
        }
    }
    
    @Override
    public String toString() {
        return 
            "\nInstrumento [ID: " + id + "]" +
            "\nNúmero de Série: " + numSerie +
            "\nTipo de Saída: " + tipoSaida +
            "\nNível Profissional: " + nivelProfissional +
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
        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ id +"_INS.ser");
             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
}