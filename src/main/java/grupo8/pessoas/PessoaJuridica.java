package grupo8.pessoas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class PessoaJuridica extends Pessoa {
    
    private final int id;
    private String cnpj;
    private String inscricao;
    private String razaoSocial;
    private String nomeFantasia;
    private LocalDate dtAbertura;
    private String atividade;
    private PessoaFisica[] sociosPF;
    
    public PessoaJuridica(PessoaFisica[] sociosPF, String cnpj, String inscricao, String razao, String nome, String dtAbertura, String atividade, String endereco, String telefone1, String telefone2, String email) {
        super(endereco, telefone1, telefone2, email);
        id = contador;
        
        setCnpj(cnpj);
        setInscricao(inscricao);
        setRazaoSocial(razao);
        setNomeFantasia(nome);
        setDtAbertura(dtAbertura);
        setAtividade(atividade);
        setSociosPF(sociosPF);
    }
    
    public int getId() {
        return id;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        validaCnpj(cnpj);
        this.cnpj = cnpj;
    }
    
    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        inscricao = (inscricao == null ? "" : inscricao.trim());
        if(inscricao.isEmpty()) {
            this.inscricao = "";
        } else if(!inscricao.matches("\\d{8,14}")) {
            throw new IllegalArgumentException("Digite apenas os números da inscrição estadual OU deixe em branco");
        } else {
            this.inscricao = inscricao;
        }
    }
    
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razao) {
        razao = (razao == null ? "" : razao.trim());
        if(!razao.matches("[\\p{L}\\w\\. ]{5,}")) {
            throw new IllegalArgumentException("Por favor, digite a razão social completa.");
        }
        razaoSocial = razao;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nome) {
        nome = (nome == null ? "" : nome.trim());
        if (nome.isEmpty()) {
            this.nomeFantasia = "";
        } else {
            this.nomeFantasia = nome;
        }
    }

    public LocalDate getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(String dtAbertura) {
        if(dtAbertura == null || dtAbertura.trim().isEmpty()) {
            throw new IllegalArgumentException("A data de abertura não pode ser vazia");
        }
        
        LocalDate hoje = LocalDate.now();
        LocalDate dtConvertida;
        
        try {
            dtConvertida = LocalDate.parse(dtAbertura, formato);
        } catch(Exception e) {
            throw new IllegalArgumentException("Data inválida. Use o formato DD/MM/AAAA.");
        }
        
        if(dtConvertida.isAfter(hoje)) {
            throw new IllegalArgumentException("A data de cadastro não pode ser futura.");
        }
        
        this.dtAbertura = dtConvertida;
    }
    
    public String getAtividade() {
        return atividade;
    }
    
    public void setAtividade(String atividade) {
        atividade = (atividade == null ? "" : atividade.trim());
        if(!atividade.matches("[\\p{L}\\w ]{5,}")) {
            throw new IllegalArgumentException("Por favor, digite, pelo menos, a atividade principal.");
        }
        this.atividade = atividade;
    }

    public PessoaFisica[] getSociosPF() {
        return sociosPF;
    }

    public void setSociosPF(PessoaFisica[] sociosPF) {
        this.sociosPF = sociosPF;
    }
    
    public void validaCnpj(String cnpj) {
        cnpj = (cnpj == null ? "" : cnpj.trim());
        if(cnpj.isEmpty()) {
            throw new IllegalArgumentException("Digite um CNPJ.");
        }
        
        if(!cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ inválido. Por favor, digite somente os 14 números!");
        }
        
        if(cnpj.matches("(\\d)\\1{13}")) {
            throw new IllegalArgumentException("CNPJ inválido. Os dígitos não podem ser todos iguais!");
        }
        
        String cnpjTeste = cnpj.substring(0,12);
        
        List<Integer> teste = new ArrayList();
        for(int i = 5; i >= 2; i--) {
            teste.add(i);
        }
        for(int i = 9; i >= 2; i--) {
            teste.add(i);
        }
        
        int soma = 0;
        for(int i = 0; i < 12; i++) {
            int j = cnpjTeste.charAt(i) - '0';
            soma += j * teste.get(i);
        }
        
        int dig1 = (soma % 11 < 2 ? 0 : 11 - soma % 11);
        cnpjTeste += dig1;
        
        teste.add(0, 6);
        soma = 0;
        for(int i = 0; i < 13; i++) {
            int j = cnpjTeste.charAt(i) - '0';
            soma += j * teste.get(i);
        }
        
        int dig2 = (soma % 11 < 2 ? 0 : 11 - soma % 11);
        cnpjTeste += dig2;
        
        if(!cnpj.equals(cnpjTeste)) {
            throw new IllegalArgumentException("CNPJ inválido. Por favor, digite somente os 14 números!");
        }
    }

    @Override
    public String toString() {
        return
        "\nCNPJ: " + cnpj +
        "\nInscrição Estadual: " + inscricao +
        "\nRazão Social: " + razaoSocial +
        "\nNome Fantasia: " +
        "\nEndereço: " + endereco +
        "\nTelefone 1: " + telefone1 +
        "\nTelefone 2: " + telefone2 +
        "\nEmail: " + email +
        "\nData de Abertura: " + dtAbertura.format(formato) +
        "\nAtividade: " + atividade;
    }

    public String dadosPJ() {
        StringBuilder sb = new StringBuilder(toString());
        
        if (sociosPF == null || sociosPF.length == 0) {
            sb.append("\n\nQUADRO SOCIETÁRIO: Nenhum sócio cadastrado.\n");
            return sb.toString();
        }
        
        sb.append("\n\nQUADRO SOCIETÁRIO (Total: ")
          .append(sociosPF.length)
          .append(" sócios)\n");
        
        int i = 0;
        for(PessoaFisica socio : sociosPF) {
            i++;
            sb.append("\nSócio ").append(i).append("\n");
            sb.append(socio.toString()).append("\n");            
        }
        
        return sb.toString();
    }
    
    public void salvar() throws FileNotFoundException, IOException {
        try (FileOutputStream fileOut = new FileOutputStream("src/data/Pessoa"+ id +"_PJ.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
}
