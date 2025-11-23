package grupo8.produto.instrumentos;

import grupo8.produto.Instrumento;

public final class Bateria extends Instrumento {
    
    private int numTons;
    private int numPecas;
    private String materialPele;
    private double tamanhoCaixa;
    private double tamanhoBumbo;
    private String tipoPrato;
    private String tipoBaqueta;
    private String tipoAro;
    
    public Bateria(String numTons, String numPecas, String materialPele, String tamanhoCaixa,
              String tamanhoBumbo, String tipoPrato, String tipoBaqueta, String tipoAro,
              String fabricante, String numSerie, String tipoProducaoSom, String nivelProfissional,
              String codigo, String descricao, String marca, 
              String dataFabricacao, String paisFabricacao, String material, 
              String cor, String preco, String peso, String estoque, String prazoGarantia) {
        
        
        
        super(fabricante, numSerie, tipoProducaoSom, nivelProfissional, 
              codigo, descricao, marca, dataFabricacao, paisFabricacao, 
              material, cor, preco, peso, estoque, prazoGarantia);
        
        setNumTons(numTons);
        setNumPecas(numPecas);
        setMaterialPele(materialPele);
        setTamanhoCaixa(tamanhoCaixa);
        setTamanhoBumbo(tamanhoBumbo);
        setTipoPrato(tipoPrato);
        setTipoBaqueta(tipoBaqueta);
        setTipoAro(tipoAro);
    }
    
    public int getNumTons() {
        return numTons;
    }
    
    public final void setNumTons(String numTons) {
        if(numTons == null || numTons.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o número de tons que a bateria irá ter.\n");
        }
        
        int numTonsFormatado;
        
        try {
            numTonsFormatado = Integer.parseInt(numTons);
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente números.\n");
        }
        
        if(numTonsFormatado <= 0) {
            throw new IllegalArgumentException("Por favor, digite um número de tons maior que 0.\n");
        }
        
        this.numTons = numTonsFormatado;
    }
    
    public int getNumPecas() {
        return numPecas;
    }
    
    public final void setNumPecas(String numPecas) {
        if(numPecas == null || numPecas.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o número de peças.\n");
        }
        
        int numPecasFormatado;
        
        try {
            numPecasFormatado = Integer.parseInt(numPecas);
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente números.\n");
        }
        
        if(numPecasFormatado <= 2) {
            throw new IllegalArgumentException("Por favor, digite um número de peças maior que 2.\n");
        }
        
        this.numPecas = numPecasFormatado;
    }
    
    public String getMaterialPele() {
        return materialPele;
    }
    
    public final void setMaterialPele(String materialPele) {
        if (materialPele == null || materialPele.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o material da pele.\n");
        }

        materialPele = materialPele.trim();

        if (!materialPele.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Material da pele inválido. Por favor, digite somente letras.\n");
        } 
        
        this.materialPele = materialPele;
    }
    
    public double getTamanhoCaixa() {
        return tamanhoCaixa;
    }
    
    public final void setTamanhoCaixa(String tamanhoCaixa) {
        if(tamanhoCaixa == null || tamanhoCaixa.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tamanho da caixa.\n");
        }
        
        double tamanhoCaixaFormatado;
        
        try {
            tamanhoCaixaFormatado = Double.parseDouble(tamanhoCaixa);
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente números para o tamanho da caixa.\n");
        }
        
        if(tamanhoCaixaFormatado <= 0) {
            throw new IllegalArgumentException("Por favor, digite um tamanho de caixa maior que 0.\n");
        }
        
        this.tamanhoCaixa = tamanhoCaixaFormatado;
    }
    
    public double getTamanhoBumbo() {
        return tamanhoBumbo;
    }
    
    public final void setTamanhoBumbo(String tamanhoBumbo) {
        if(tamanhoBumbo == null || tamanhoBumbo.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tamanho do bumbo.\n");
        }
        
        double tamanhoBumboFormatado;
        
        try {
            tamanhoBumboFormatado = Double.parseDouble(tamanhoBumbo);
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite somente números para o tamanho do bumbo.\n");
        }
        
        if(tamanhoBumboFormatado <= 0) {
            throw new IllegalArgumentException("Por favor, digite um tamanho de bumbo maior que 0.\n");
        }
        
        this.tamanhoBumbo = tamanhoBumboFormatado;
    }
    
    public String getTipoPrato() {
        return tipoPrato;
    }
    
    public final void setTipoPrato(String tipoPrato) {
        if (tipoPrato == null || tipoPrato.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo de prato.\n");
        }

        tipoPrato = tipoPrato.trim();

        if (!tipoPrato.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Tipo de prato inválido. Por favor, digite somente letras.\n");
        } 
        
        this.tipoPrato = tipoPrato;
    }
    
    public String getTipoBaqueta() {
        return tipoBaqueta;
    }
    
    public final void setTipoBaqueta(String tipoBaqueta) {
        if (tipoBaqueta == null || tipoBaqueta.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo de baqueta.\n");
        }

        tipoBaqueta = tipoBaqueta.trim();

        if (!tipoBaqueta.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Tipo de baqueta inválido. Por favor, digite somente letras.\n");
        } 
        
        this.tipoBaqueta = tipoBaqueta;
    }
    
    public String getTipoAro() {
        return tipoAro;
    }
    
    public final void setTipoAro(String tipoAro) {
        if (tipoAro == null || tipoAro.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo de aro.\n");
        }

        tipoAro = tipoAro.trim();

        if (!tipoAro.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Tipo de aro inválido. Por favor, digite somente letras.\n");
        } 
        
        this.tipoAro = tipoAro;
    }
    
    @Override
    public String toString() {
        return 
            "\nBATERIA [ID: " + idProduto + "]" +
            "\n    Número de Tons: " + numTons +
            "\n    Número de Peças: " + numPecas +
            "\n    Material da Pele: " + materialPele +
            "\n    Tamanho da Caixa: " + String.format("%.1f", tamanhoCaixa) + " polegadas" +
            "\n    Tamanho do Bumbo: " + String.format("%.1f", tamanhoBumbo) + " polegadas" +
            "\n    Tipo de Prato: " + tipoPrato +
            "\n    Tipo de Baqueta: " + tipoBaqueta +
            "\n    Tipo de Aro: " + tipoAro +
            super.toString() + "\n";
    }
    
    public void salvar() throws java.io.FileNotFoundException, java.io.IOException {
        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ getIdProduto() +"_BAT.ser");
             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
    
    @Override
    public void afinar() {
        System.out.println("Afinando bateria...");
    }
        
        
}
