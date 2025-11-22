///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package grupo8.produto;
//
//public final class InstrumentoSopro extends Instrumento {
//    
//    private final int id;
//    private String tipo;
//    private String afinacao;
//    private String embocadura;
//    private String chave;
//    private String infoAdicionais;
//    
//    public InstrumentoSopro(String tipo, String afinacao, String embocadura, String chave,
//                           String infoAdicionais, String numSerie, String tipoSaida, 
//                           String nivelProfissional, String codigo, String descricao, 
//                           String fabricante, String marca, String dataFabricacao, 
//                           String paisFabricacao, String material, String cor, double preco, 
//                           double peso, int qntEstoque, int prazoGarantia) {
//        
//        super(numSerie, tipoSaida, nivelProfissional, codigo, descricao, fabricante, marca,
//              dataFabricacao, paisFabricacao, material, cor, preco, peso, qntEstoque, prazoGarantia);
//        this.id = getID(); // Usa o ID herdado do Instrumento
//        
//        setTipo(tipo);
//        setAfinacao(afinacao);
//        setEmbocadura(embocadura);
//        setChave(chave);
//        setInfoAdicionais(infoAdicionais);
//    }
//    
//    public String getTipo() {
//        return tipo;
//    }
//    
//    public final void setTipo(String tipo) {
//        tipo = (tipo == null ? "" : tipo.trim());
//        if(tipo.isEmpty()) {
//            throw new IllegalArgumentException("Tipo do instrumento de sopro é obrigatório.\n");
//        } else {
//            this.tipo = tipo;
//        }
//    }
//    
//    public String getAfinacao() {
//        return afinacao;
//    }
//    
//    public final void setAfinacao(String afinacao) {
//        afinacao = (afinacao == null ? "" : afinacao.trim());
//        if(afinacao.isEmpty()) {
//            throw new IllegalArgumentException("Afinação é obrigatória.\n");
//        } else {
//            this.afinacao = afinacao;
//        }
//    }
//    
//    public String getEmbocadura() {
//        return embocadura;
//    }
//    
//    public final void setEmbocadura(String embocadura) {
//        embocadura = (embocadura == null ? "" : embocadura.trim());
//        if(embocadura.isEmpty()) {
//            throw new IllegalArgumentException("Tipo de embocadura é obrigatório.\n");
//        } else {
//            this.embocadura = embocadura;
//        }
//    }
//    
//    public String getChave() {
//        return chave;
//    }
//    
//    public final void setChave(String chave) {
//        chave = (chave == null ? "" : chave.trim());
//        this.chave = chave; // Pode ser vazio para alguns instrumentos
//    }
//    
//    public String getInfoAdicionais() {
//        return infoAdicionais;
//    }
//    
//    public final void setInfoAdicionais(String infoAdicionais) {
//        infoAdicionais = (infoAdicionais == null ? "" : infoAdicionais.trim());
//        this.infoAdicionais = infoAdicionais; // Opcional
//    }
//    
//    @Override
//    public String toString() {
//        return 
//            "\nInstrumento de Sopro [ID: " + id + "]" +
//            "\nTipo: " + tipo +
//            "\nAfinação: " + afinacao +
//            "\nEmbocadura: " + embocadura +
//            "\nChave: " + (chave.isEmpty() ? "N/A" : chave) +
//            "\nInformações Adicionais: " + (infoAdicionais.isEmpty() ? "Nenhuma" : infoAdicionais) +
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
//        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ id +"_SOP.ser");
//             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
//             out.writeObject(this);
//        }
//    }
//}