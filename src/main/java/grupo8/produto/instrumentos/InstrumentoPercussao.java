///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package grupo8.produto.instrumentos;
//
//import grupo8.produto.Instrumento;
//
//public final class Bateria extends Instrumento {
//    
//    private final int id;
//    private int numTons;
//    private int numPecas;
//    private String materialPele;
//    private double tamanhoCaixa;
//    private double tamanhoBumbo;
//    private String tipoPrato;
//    private String tipoBaqueta;
//    private String tipoAro;
//    
//    public Bateria(int numTons, int numPecas, String materialPele, double tamanhoCaixa,
//                  double tamanhoBumbo, String tipoPrato, String tipoBaqueta, String tipoAro,
//                  String numSerie, String tipoSaida, String nivelProfissional,
//                  String codigo, String descricao, String fabricante, String marca, 
//                  String dataFabricacao, String paisFabricacao, String material, 
//                  String cor, double preco, double peso, int qntEstoque, int prazoGarantia) {
//        
//        super(numSerie, tipoSaida, nivelProfissional, codigo, descricao, fabricante, marca,
//              dataFabricacao, paisFabricacao, material, cor, preco, peso, qntEstoque, prazoGarantia);
//        this.id = getID(); // Usa o ID herdado do Instrumento
//        
//        setNumTons(numTons);
//        setNumPecas(numPecas);
//        setMaterialPele(materialPele);
//        setTamanhoCaixa(tamanhoCaixa);
//        setTamanhoBumbo(tamanhoBumbo);
//        setTipoPrato(tipoPrato);
//        setTipoBaqueta(tipoBaqueta);
//        setTipoAro(tipoAro);
//    }
//    
//    public int getNumTons() {
//        return numTons;
//    }
//    
//    public final void setNumTons(int numTons) {
//        if(numTons <= 0) {
//            throw new IllegalArgumentException("Número de tons deve ser positivo.\n");
//        } else {
//            this.numTons = numTons;
//        }
//    }
//    
//    public int getNumPecas() {
//        return numPecas;
//    }
//    
//    public final void setNumPecas(int numPecas) {
//        if(numPecas <= 0) {
//            throw new IllegalArgumentException("Número de peças deve ser positivo.\n");
//        } else {
//            this.numPecas = numPecas;
//        }
//    }
//    
//    public String getMaterialPele() {
//        return materialPele;
//    }
//    
//    public final void setMaterialPele(String materialPele) {
//        materialPele = (materialPele == null ? "" : materialPele.trim());
//        if(materialPele.isEmpty()) {
//            throw new IllegalArgumentException("Material da pele é obrigatório.\n");
//        } else {
//            this.materialPele = materialPele;
//        }
//    }
//    
//    public double getTamanhoCaixa() {
//        return tamanhoCaixa;
//    }
//    
//    public final void setTamanhoCaixa(double tamanhoCaixa) {
//        if(tamanhoCaixa <= 0) {
//            throw new IllegalArgumentException("Tamanho da caixa deve ser positivo.\n");
//        } else {
//            this.tamanhoCaixa = tamanhoCaixa;
//        }
//    }
//    
//    public double getTamanhoBumbo() {
//        return tamanhoBumbo;
//    }
//    
//    public final void setTamanhoBumbo(double tamanhoBumbo) {
//        if(tamanhoBumbo <= 0) {
//            throw new IllegalArgumentException("Tamanho do bumbo deve ser positivo.\n");
//        } else {
//            this.tamanhoBumbo = tamanhoBumbo;
//        }
//    }
//    
//    public String getTipoPrato() {
//        return tipoPrato;
//    }
//    
//    public final void setTipoPrato(String tipoPrato) {
//        tipoPrato = (tipoPrato == null ? "" : tipoPrato.trim());
//        if(tipoPrato.isEmpty()) {
//            throw new IllegalArgumentException("Tipo de prato é obrigatório.\n");
//        } else {
//            this.tipoPrato = tipoPrato;
//        }
//    }
//    
//    public String getTipoBaqueta() {
//        return tipoBaqueta;
//    }
//    
//    public final void setTipoBaqueta(String tipoBaqueta) {
//        tipoBaqueta = (tipoBaqueta == null ? "" : tipoBaqueta.trim());
//        if(tipoBaqueta.isEmpty()) {
//            throw new IllegalArgumentException("Tipo de baqueta é obrigatório.\n");
//        } else {
//            this.tipoBaqueta = tipoBaqueta;
//        }
//    }
//    
//    public String getTipoAro() {
//        return tipoAro;
//    }
//    
//    public final void setTipoAro(String tipoAro) {
//        tipoAro = (tipoAro == null ? "" : tipoAro.trim());
//        if(tipoAro.isEmpty()) {
//            throw new IllegalArgumentException("Tipo de aro é obrigatório.\n");
//        } else {
//            this.tipoAro = tipoAro;
//        }
//    }
//    
//    @Override
//    public String toString() {
//        return 
//            "\nBateria [ID: " + id + "]" +
//            "\nNúmero de Tons: " + numTons +
//            "\nNúmero de Peças: " + numPecas +
//            "\nMaterial da Pele: " + materialPele +
//            "\nTamanho da Caixa: " + tamanhoCaixa + " polegadas" +
//            "\nTamanho do Bumbo: " + tamanhoBumbo + " polegadas" +
//            "\nTipo de Prato: " + tipoPrato +
//            "\nTipo de Baqueta: " + tipoBaqueta +
//            "\nTipo de Aro: " + tipoAro +
//            "\nNúmero de Série: " + getNumSerie() +
//            "\nTipo de Saída: " + getTipoSaida() +
//            "\nNível Profissional: " + getNivelProfissional() +
//            "\nCódigo: " + getCodigo() +
//            "\nDescrição: " + getDescricao() +
//            "\nFabricante: " + getFabricante() +
//            "\nMarca: " + getMarca() +
//            "\nData Fabricação: " + getDataFabricacao() +
//            "\nPaís Fabricação: " + getPaisFabricacao() +
//            "\nMaterial: " + getMaterial() +
//            "\nCor: " + getCor() +
//            "\nPreço: R$ " + String.format("%.2f", getPreco()) +
//            "\nPeso: " + getPeso() + " kg" +
//            "\nEstoque: " + getQntEstoque() +
//            "\nGarantia: " + getPrazoGarantia() + " meses" +
//            "\n\n---------------------------------------------------------------";
//    }
//    
//    @Override
//    public void salvar() throws java.io.FileNotFoundException, java.io.IOException {
//        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ id +"_BAT.ser");
//             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
//             out.writeObject(this);
//        }
//    }
//}