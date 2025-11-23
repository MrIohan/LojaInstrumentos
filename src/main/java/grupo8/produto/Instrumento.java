package grupo8.produto;

import grupo8.interfaces.InferfaceInstrumento;
import grupo8.interfaces.InterfaceProduto;
import grupo8.pessoas.Pessoa;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Instrumento extends Produto implements InferfaceInstrumento {
    
    public static int idInstrumento = 0;

    private String fabricante;
    private String numSerie;
    private String tipoProducaoSom; //implementar uma estrutura de constantes
    private String nivelProfissional; 
    
    public Instrumento(String fabricante, String numSerie, String tipoProducaoSom, String nivelProfissional,
                      String codigo, String descricao, String marca, String dataFabricacao, String paisFabricacao, 
                      String material, String cor, String preco, String peso, String qntEstoque, String prazoGarantia) {
        
        super(codigo, descricao, marca, dataFabricacao, paisFabricacao, 
              material, cor, preco, peso, qntEstoque, prazoGarantia);
        
        setFabricante(fabricante);
        setNumSerie(numSerie);
        setTipoProducaoSom(tipoProducaoSom);
        setNivelProfissional(nivelProfissional);
        idInstrumento++;
    }

    public String getFabricante() {
        return fabricante;
    }

    public final void setFabricante(String fabricante) {
        if (fabricante == null || fabricante.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o fabricante do instrumento.\n");
        }

        fabricante = fabricante.trim();

        if (!fabricante.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Fabricante inválido. Por favor, digite somente letras.\n");
        } 
        
        this.fabricante = fabricante;
    }

    public String getNumSerie() {
        return numSerie;
    }
    
    public final void setNumSerie(String numSerie) {
        if (numSerie == null || numSerie.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o núemro de série do instrumento.\n");
        }

        numSerie = numSerie.trim();

        if (!numSerie.matches("\\d+")){
            throw new IllegalArgumentException("Número de série inválido. Por favor, digite somente números.\n");
        } 
        
        this.numSerie = numSerie;
    }
    
    public String getTipoProducaoSom() {
        return tipoProducaoSom;
    }
    
    public final void setTipoProducaoSom(String tipoProducaoSom) {
        if (tipoProducaoSom == null || tipoProducaoSom.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo de produção de som do instrumento.\n");
        }

        tipoProducaoSom = tipoProducaoSom.trim();

        if (!tipoProducaoSom.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Tipo de produção de som inválido. Por favor, digite somente letras.\n");
        } 
        
        this.tipoProducaoSom = tipoProducaoSom;
    }
    
    public String getNivelProfissional() {
        return nivelProfissional;
    }
    
    public final void setNivelProfissional(String nivelProfissional) {
        if (nivelProfissional == null || nivelProfissional.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o nível profissional indicado para este instrumento.\n");
        }

        nivelProfissional = nivelProfissional.trim();

        if (!nivelProfissional.matches("[\\p{L} ]+")){
            throw new IllegalArgumentException("Nível profissional inválido. Por favor, digite somente letras.\n");
        } 
        
        this.nivelProfissional = nivelProfissional;
    }
    
    @Override
    public String toString() {
        return
            super.toString() +
            "\nFabricante: " + fabricante +
            "\nNúmero de Série: " + numSerie +
            "\nTipo de Produção de Som: " + tipoProducaoSom +
            "\nNível Profissional: " + nivelProfissional;
    }
        
    public void afinar() {
        System.out.println("Afinando instrumento (geral)...");

    }
}