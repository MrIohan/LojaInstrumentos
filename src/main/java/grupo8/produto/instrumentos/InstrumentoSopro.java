package grupo8.produto.instrumentos;

import grupo8.produto.Instrumento;

public final class InstrumentoSopro extends Instrumento {
    
    private String tipo;
    private String afinacao;
    private String embocadura;
    private String chave;
    private String infoAdicionais;
    
    public InstrumentoSopro(String tipo, String afinacao, String embocadura, String chave, String infoAdicionais,
                            String fabricante, String numSerie, String tipoProducaoSom, String nivelProfissional,
                            String codigo, String descricao, String marca, String dataFabricacao, String paisFabricacao, 
                            String material, String cor, String preco, String peso, String estoque, String prazoGarantia){
    
        super(fabricante, numSerie, tipoProducaoSom, nivelProfissional,  // ← ORDEM CORRETA!
              codigo, descricao, marca, dataFabricacao, paisFabricacao, 
              material, cor, preco, peso, estoque, prazoGarantia);
    
        setTipo(tipo);
        setAfinacao(afinacao);
        setEmbocadura(embocadura);
        setChave(chave);
        setInfoAdicionais(infoAdicionais);
}
    
    public String getTipo() {
        return tipo;
    }
    
    public final void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo do instrumento de sopro. Ex: Saxofone, Trompete, Flauta.\n");
        }

        tipo = tipo.trim();

        if (!tipo.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Tipo inválido. Por favor, digite somente letras.\n");
        } 
        
        this.tipo = tipo;
    }
    
    public String getAfinacao() {
        return afinacao;
    }
    
    public final void setAfinacao(String afinacao) {
        if (afinacao == null || afinacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a afinação do instrumento. Ex: Dó, Ré, Mi, 440Hz...\n");
        }

        afinacao = afinacao.trim();

        if (!afinacao.matches("[\\p{L}0-9 ]+")){
            throw new IllegalArgumentException("Afinação inválida. Por favor, digite somente letras e números.\n");
        } 
        
        this.afinacao = afinacao;
    }
    
    public String getEmbocadura() {
        return embocadura;
    }
    
    public final void setEmbocadura(String embocadura) {
        if (embocadura == null || embocadura.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo de embocadura. Ex: Simples, Dupla...\n");
        }

        embocadura = embocadura.trim();

        if (!embocadura.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Embocadura inválida. Por favor, digite somente letras.\n");
        } 
        
        this.embocadura = embocadura;
    }
    
    public String getChave() {
        return chave;
    }
    
    public final void setChave(String chave) {
        if (chave == null || chave.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo de chave. Ex: Chave de oitava, Chave de polegar...\n");
        }

        chave = chave.trim();

        if (!chave.matches("[\\p{L}0-9 ]+")){
            throw new IllegalArgumentException("Chave inválida. Por favor, digite somente letras e números.\n");
        } 
        
        this.chave = chave;
    }
    
    public String getInfoAdicionais() {
        return infoAdicionais;
    }
    
    public final void setInfoAdicionais(String infoAdicionais) {
        if (infoAdicionais == null || infoAdicionais.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite informações adicionais.\n");
        }

        infoAdicionais = infoAdicionais.trim();

        if (!infoAdicionais.matches("[\\p{L}0-9 ,.-]+")){
            throw new IllegalArgumentException("Informações adicionais inválidas. Por favor, digite somente letras, números e pontuações básicas.\n");
        } 
        
        this.infoAdicionais = infoAdicionais;
    }
    
    @Override
    public String toString() {
        return 
            "\nINSTRUMENTO DE SOPRO [ID: " + idProduto + "]" +
            "\n    Tipo: " + tipo +
            "\n    Afinação: " + afinacao +
            "\n    Embocadura: " + embocadura +
            "\n    Chave: " + chave +
            "\n    Informações Adicionais: " + infoAdicionais +
            super.toString() + "\n";
    }
    
    public void salvar() throws java.io.FileNotFoundException, java.io.IOException {
        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ getIdProduto() +"_SOP.ser");
             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
    
    @Override
        public void afinar(){
            System.out.println("Ajustando a afinação...");
        }
    
}
