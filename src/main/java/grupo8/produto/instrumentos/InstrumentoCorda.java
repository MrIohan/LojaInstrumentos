///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package grupo8.produto.instrumentos;
//
//import grupo8.produto.Instrumento;
//
//public final class InstrumentoCorda extends Instrumento {
//    
//    private final int id;
//    private String tipo;
//    private int numCorda;
//    private String encordoamento;
//    private String captadores;
//    private int numTrastes;
//    private String materialBraco;
//    private String encaixeBraco;
//    private String formatoCorpo;
//    private String corEscudo;
//    
//    public InstrumentoCorda(String tipo, int numCorda, String encordoamento, String captadores,
//                           int numTrastes, String materialBraco, String encaixeBraco, 
//                           String formatoCorpo, String corEscudo,
//                           String numSerie, String tipoSaida, String nivelProfissional,
//                           String codigo, String descricao, String fabricante, String marca, 
//                           String dataFabricacao, String paisFabricacao, String material, 
//                           String cor, double preco, double peso, int qntEstoque, int prazoGarantia) {
//        
//        super(numSerie, tipoSaida, nivelProfissional, codigo, descricao, fabricante, marca,
//              dataFabricacao, paisFabricacao, material, cor, preco, peso, qntEstoque, prazoGarantia);
//        this.id = getID(); // Usa o ID herdado do Instrumento
//        
//        setTipo(tipo);
//        setNumCorda(numCorda);
//        setEncordoamento(encordoamento);
//        setCaptadores(captadores);
//        setNumTrastes(numTrastes);
//        setMaterialBraco(materialBraco);
//        setEncaixeBraco(encaixeBraco);
//        setFormatoCorpo(formatoCorpo);
//        setCorEscudo(corEscudo);
//    }
//    
//    public String getTipo() {
//        return tipo;
//    }
//    
//    public final void setTipo(String tipo) {
//        tipo = (tipo == null ? "" : tipo.trim());
//        if(tipo.isEmpty()) {
//            throw new IllegalArgumentException("Tipo do instrumento de corda é obrigatório.\n");
//        } else {
//            this.tipo = tipo;
//        }
//    }
//    
//    public int getNumCorda() {
//        return numCorda;
//    }
//    
//    public final void setNumCorda(int numCorda) {
//        if(numCorda <= 0) {
//            throw new IllegalArgumentException("Número de cordas deve ser positivo.\n");
//        } else {
//            this.numCorda = numCorda;
//        }
//    }
//    
//    public String getEncordoamento() {
//        return encordoamento;
//    }
//    
//    public final void setEncordoamento(String encordoamento) {
//        encordoamento = (encordoamento == null ? "" : encordoamento.trim());
//        if(encordoamento.isEmpty()) {
//            throw new IllegalArgumentException("Tipo de encordoamento é obrigatório.\n");
//        } else {
//            this.encordoamento = encordoamento;
//        }
//    }
//    
//    public String getCaptadores() {
//        return captadores;
//    }
//    
//    public final void setCaptadores(String captadores) {
//        captadores = (captadores == null ? "" : captadores.trim());
//        this.captadores = captadores; // Pode ser vazio para instrumentos acústicos
//    }
//    
//    public int getNumTrastes() {
//        return numTrastes;
//    }
//    
//    public final void setNumTrastes(int numTrastes) {
//        if(numTrastes < 0) {
//            throw new IllegalArgumentException("Número de trastes não pode ser negativo.\n");
//        } else {
//            this.numTrastes = numTrastes;
//        }
//    }
//    
//    public String getMaterialBraco() {
//        return materialBraco;
//    }
//    
//    public final void setMaterialBraco(String materialBraco) {
//        materialBraco = (materialBraco == null ? "" : materialBraco.trim());
//        if(materialBraco.isEmpty()) {
//            throw new IllegalArgumentException("Material do braço é obrigatório.\n");
//        } else {
//            this.materialBraco = materialBraco;
//        }
//    }
//    
//    public String getEncaixeBraco() {
//        return encaixeBraco;
//    }
//    
//    public final void setEncaixeBraco(String encaixeBraco) {
//        encaixeBraco = (encaixeBraco == null ? "" : encaixeBraco.trim());
//        if(encaixeBraco.isEmpty()) {
//            throw new IllegalArgumentException("Tipo de encaixe do braço é obrigatório.\n");
//        } else {
//            this.encaixeBraco = encaixeBraco;
//        }
//    }
//    
//    public String getFormatoCorpo() {
//        return formatoCorpo;
//    }
//    
//    public final void setFormatoCorpo(String formatoCorpo) {
//        formatoCorpo = (formatoCorpo == null ? "" : formatoCorpo.trim());
//        if(formatoCorpo.isEmpty()) {
//            throw new IllegalArgumentException("Formato do corpo é obrigatório.\n");
//        } else {
//            this.formatoCorpo = formatoCorpo;
//        }
//    }
//    
//    public String getCorEscudo() {
//        return corEscudo;
//    }
//    
//    public final void setCorEscudo(String corEscudo) {
//        corEscudo = (corEscudo == null ? "" : corEscudo.trim());
//        this.corEscudo = corEscudo; // Opcional
//    }
//    
//    @Override
//    public String toString() {
//        return 
//            "\nInstrumento de Corda [ID: " + id + "]" +
//            "\nTipo: " + tipo +
//            "\nNúmero de Cordas: " + numCorda +
//            "\nEncordoamento: " + encordoamento +
//            "\nCaptadores: " + (captadores.isEmpty() ? "N/A" : captadores) +
//            "\nNúmero de Trastes: " + numTrastes +
//            "\nMaterial do Braço: " + materialBraco +
//            "\nEncaixe do Braço: " + encaixeBraco +
//            "\nFormato do Corpo: " + formatoCorpo +
//            "\nCor do Escudo: " + (corEscudo.isEmpty() ? "N/A" : corEscudo) +
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
//        try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream("src/data/Produto"+ id +"_COR.ser");
//             java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut)) {
//             out.writeObject(this);
//        }
//    }
//}