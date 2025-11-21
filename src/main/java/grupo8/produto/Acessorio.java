package grupo8.produto;

public final class Acessorio extends Produto {
    
    private static int idAcessorio = 0;
    
    private String tipo;
    private String indicacao;
    
    public Acessorio(String tipo, String indicacao,
                     String codigo, String descricao, String marca, String dataFabricacao, String paisFabricacao, 
                     String material, String cor, String preco, String peso, String qntEstoque, String prazoGarantia) {
        
        super(codigo, descricao, marca, dataFabricacao, paisFabricacao, 
              material, cor, preco, peso, qntEstoque, prazoGarantia);
        setTipo(tipo);
        setIndicacao(indicacao);
        idAcessorio++;
    }
    
    public int getIdAcessorio() {
        return idAcessorio;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo do acessório.\n");
        }

        tipo = tipo.trim();

        if (!tipo.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Tipo inválido. Por favor, digite somente letras.\n");
        } 
        
        this.tipo = tipo;
    }
    
    public String getIndicacao() {
        return indicacao;
    }
    
    public void setIndicacao(String indicacao) {
        if (indicacao == null || indicacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite os intrumentos indicados para este acessório.\n");
        }

        tipo = tipo.trim();

        if (!indicacao.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Indicação inválida. Por favor, digite somente letras.\n");
        } 
        
        this.indicacao = indicacao;
    }
    
    @Override
    public String toString() {
        return 
            "\nDADOS DO ACESSÓRIO [ID: " + idAcessorio + "]\n" +
            "\n    Tipo do Acessório: " + tipo +
            "\n    Instrumentos indicados: " + indicacao + "\n" +
            super.toString() + "\n";
    }
    
    public void salvar() throws java.io.FileNotFoundException, java.io.IOException {
        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ idProduto +"_ACS.ser");
             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
}