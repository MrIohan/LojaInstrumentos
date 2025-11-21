package grupo8.papeis;

import grupo8.pessoas.Pessoa;
import grupo8.pessoas.PessoaFisica;
import grupo8.pessoas.PessoaJuridica;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class Cliente {    
    
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final long serialVersionUID = 1L;
    public static int contador = 0;

    private final int codigo;
    private Pessoa dadosPessoa;
    private final LocalDate dtCadastro;
    private int pontuacaoFidelidade; //se atentar ao controle de prazos
    private LocalDate dtUltimaCompra;
    private double vlrTotalComprado;
    
//    private Venda[] historico;
    
    public Cliente(Pessoa dadosPessoa) {
        setDadosPessoa(dadosPessoa);
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

    public LocalDate getDtUltimaCompra() {
        return dtUltimaCompra;
    }

    public void setDtUltimaCompra(LocalDate dtUltimaCompra) {
        this.dtUltimaCompra = dtUltimaCompra;
    }

    public double getVlrTotalComprado() {
        return vlrTotalComprado;
    }

    public void setVlrTotalComprado(double vlrTotalComprado) {
        this.vlrTotalComprado = vlrTotalComprado;
    }
    
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("\nDADOS DO CLIENTE\n")
          .append("\n    Código: ").append(codigo)
          .append("\n    Data do Cadastro: ").append(dtCadastro.format(formato));
          

        sb.append(dadosPessoa.toString()).append("\n");

        sb.append("\n    Pontos de Fidelidade: ").append(pontuacaoFidelidade)
          .append("\n    Data da última compra: ").append((dtUltimaCompra == null ? "Não há informações a exibir" : dtUltimaCompra.format(formato)))
          .append("\n    Valor total comprado: ").append((vlrTotalComprado == 0 ? "Não há informações a exibir" : vlrTotalComprado));
        
        return sb.toString();
    }
    
    public void salvar() throws FileNotFoundException, IOException {
        File pasta = new File("data");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
        
        try (FileOutputStream fileOut = new FileOutputStream("src/data/Cliente"+ codigo + ".ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
    
    public static List<Cliente> carregar() throws IOException, ClassNotFoundException {
        String diretorio = "src/data/";
        String padraoArquivo = "Cliente\\d+\\.ser";
        
        File pasta = new File(diretorio);
        Pattern pattern = Pattern.compile(padraoArquivo); 
        File[] arquivos = pasta.listFiles((dir, name) -> pattern.matcher(name).matches()); 
        
        ArrayList<Cliente> clientes = new ArrayList<>();

        if (arquivos == null) {
            System.out.println("Diretório não existe ou não contem arquivos compatíveis.");
            return clientes;
        }
        
        for (File arq : arquivos) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arq))) {
                Cliente c = (Cliente) ois.readObject(); 
                clientes.add(c);
            } catch (Exception e) {
                System.out.println("Erro ao ler arquivo: " + arq.getName() + "-" + e.getMessage());
            }
        }
        return clientes;
    }
}
