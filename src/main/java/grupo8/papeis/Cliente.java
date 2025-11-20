package grupo8.papeis;

import grupo8.pessoas.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente {    
    
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final long serialVersionUID = 1L;
    public static int contador = 0;

    private final int codigo;
    private Pessoa dadosPessoa;
    private final LocalDate dtCadastro;
    private int pontuacaoFidelidade; //se atentar ao controle de prazos
//    private String categoria;
//    private double descontoCategoria;
    private LocalDate ultimaCompra;
    private double vlrTotalComprado;
   //private Venda[] historico;
    
    public Cliente() {
        dtCadastro = LocalDate.now();
        codigo = ++contador;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public Pessoa getDadosPessoa() {
        return dadosPessoa;
    }

    public void setDadosPessoa(Pessoa dadosPessoa) {
        if(dadosPessoa == null) {
            throw new IllegalArgumentException("Pessoa não cadastrada.");
        }
        this.dadosPessoa = dadosPessoa;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public int getPontuacaoFidelidade() {
        return pontuacaoFidelidade;
    }

    public void setPontuacaoFidelidade(int pontos) {
        pontuacaoFidelidade += pontos;
    }

//    public String getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(String categoria) {
//        this.categoria = categoria;
//    }
//
//    public double getDescontoCategoria() {
//        return descontoCategoria;
//    }
//
//    public void setDescontoCategoria(double descontoCategoria) {
//        this.descontoCategoria = descontoCategoria;
//    }

    public LocalDate getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(LocalDate ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public double getVlrTotalComprado() {
        return vlrTotalComprado;
    }

    public void setVlrTotalComprado(double vlrTotalComprado) {
        this.vlrTotalComprado = vlrTotalComprado;
    }
      
    
}
