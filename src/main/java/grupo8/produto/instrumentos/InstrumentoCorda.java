package grupo8.produto.instrumentos;

import grupo8.produto.Instrumento;

public final class InstrumentoCorda extends Instrumento {
    
    private String tipo;
    private int numCorda;
    private String encordoamento;
    private String captadores;
    private int numTrastes;
    private String materialBraco;
    private String encaixeBraco;
    private String formatoCorpo;
    private String corEscudo;
    
    public InstrumentoCorda(String tipo, String numCorda, String encordoamento, String captadores, String numTrastes, 
                            String materialBraco, String encaixeBraco, String formatoCorpo, String corEscudo,
                            String fabricante, String numSerie, String tipoProducaoSom, String nivelProfissional,
                            String codigo, String descricao, String marca, String dataFabricacao, String paisFabricacao, 
                            String material, String cor, String preco, String peso, String estoque, String prazoGarantia){
    
        super(fabricante, numSerie, tipoProducaoSom, nivelProfissional, 
                codigo, descricao, marca, dataFabricacao, paisFabricacao, 
                material, cor, preco, peso, estoque, prazoGarantia);
            
        setTipo(tipo);
        setNumCorda(numCorda);
        setEncordoamento(encordoamento);
        setCaptadores(captadores);
        setNumTrastes(numTrastes);
        setMaterialBraco(materialBraco);
        setEncaixeBraco(encaixeBraco);
        setFormatoCorpo(formatoCorpo);
        setCorEscudo(corEscudo);
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo do instrumento. Ex: Violão, Guitarra, Baixo.\n");
        }

        tipo = tipo.trim();

        if (!tipo.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Tipo inválido. Por favor, digite somente letras.\n");
        } 
        
        this.tipo = tipo;
    }
    
    public int getNumCorda() {
        return numCorda;
    }
    
    public final void setNumCorda(String numCorda) {
          if(numCorda == null || numCorda.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o número de cordas.\n");
        }
        
        int numCordaFormatado;
        
        try {
            numCordaFormatado = Integer.parseInt(numCorda);
            
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente números para o número de cordas.\n");
        }
        
        if(numCordaFormatado <= 0) {
            throw new IllegalArgumentException("Por favor, digite um número de cordas maior que 0.\n");
        }
        
        this.numCorda = numCordaFormatado;
    }
    
    public String getEncordoamento() {
        return encordoamento;
    }
    
    public final void setEncordoamento(String encordoamento) {
        if (encordoamento == null || encordoamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo de encordoamento.\n");
        }

        encordoamento = encordoamento.trim();

        if (!encordoamento.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Encordoamento inválido. Por favor, digite somente letras.\n");
        } 
        
        this.encordoamento = encordoamento;
    }
    
    public String getCaptadores() {
        return captadores;
    }
    
    public final void setCaptadores(String captadores) {
        if(captadores == null || captadores.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite os captadores.\n");
    }
    
    captadores = captadores.trim();
    
        if (!captadores.matches("[\\p{L}0-9 ]+")){
            throw new IllegalArgumentException("Captadores inválidos. Por favor, digite somente letras e números.\n");
    }
    
        this.captadores = captadores;
    }
    
    public int getNumTrastes() {
        return numTrastes;
    }
    
    public final void setNumTrastes(String numTrastes) {
        if(numTrastes == null || numTrastes.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o número de trastes.\n");
        }
        
        int numTrastesFormatado;
        
        try {
            numTrastesFormatado = Integer.parseInt(numTrastes);
            
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente números para o número de trastes.\n");
        }
        
        if(numTrastesFormatado < 0) {
            throw new IllegalArgumentException("Por favor, digite um número de trastes maior ou igual a 0.\n");
        }
        
        this.numTrastes = numTrastesFormatado;
    }
    
    public String getMaterialBraco() {
        return materialBraco;
    }
    
    public final void setMaterialBraco(String materialBraco) {
        if (materialBraco == null || materialBraco.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o material do braço.\n");
        }

        materialBraco = materialBraco.trim();

        if (!materialBraco.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Material do braço inválido. Por favor, digite somente letras.\n");
        } 
        
        this.materialBraco = materialBraco;
    }
    
    public String getEncaixeBraco() {
        return encaixeBraco;
    }
    
    public final void setEncaixeBraco(String encaixeBraco) {
        if (encaixeBraco == null || encaixeBraco.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo de encaixe do braço.\n");
        }

        encaixeBraco = encaixeBraco.trim();

        if (!encaixeBraco.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Encaixe do braço inválido. Por favor, digite somente letras.\n");
        } 
        
        this.encaixeBraco = encaixeBraco;
    }
    
    public String getFormatoCorpo() {
        return formatoCorpo;
    }
    
    public final void setFormatoCorpo(String formatoCorpo) {
        if (formatoCorpo == null || formatoCorpo.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o formato do corpo.\n");
        }

        formatoCorpo = formatoCorpo.trim();

        if (!formatoCorpo.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Formato do corpo inválido. Por favor, digite somente letras.\n");
        } 
        
        this.formatoCorpo = formatoCorpo;
    }
    
    public String getCorEscudo() {
        return corEscudo;
    }
    
    public final void setCorEscudo(String corEscudo) {
        if(corEscudo == null || corEscudo.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a cor do escudo.\n");
    }
    
        corEscudo = corEscudo.trim();
    
        if (!corEscudo.matches("[\\p{L}0-9 ]+")){
            throw new IllegalArgumentException("Cor do escudo inválida. Por favor, digite somente letras e números.\n");
    }
    
        this.corEscudo = corEscudo;
    }
    
    @Override
    public String toString() {
        return 
            "\nINSTRUMENTO DE CORDA [ID: " + idProduto + "]" +
            "\n    Tipo: " + tipo +
            "\n    Número de Cordas: " + numCorda +
            "\n    Encordoamento: " + encordoamento +
            "\n    Captadores: " + captadores +
            "\n    Número de Trastes: " + numTrastes +
            "\n    Material do Braço: " + materialBraco +
            "\n    Encaixe do Braço: " + encaixeBraco +
            "\n    Formato do Corpo: " + formatoCorpo +
            "\n    Cor do Escudo: " + corEscudo +
            super.toString() + "\n";
    }
    
    
   public void salvar() throws java.io.FileNotFoundException, java.io.IOException {
        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ getIdProduto() +"_COR.ser");
             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
   
    @Override
    public void afinar() {
        System.out.println("Afinando as cordas...");
    }
   
}
