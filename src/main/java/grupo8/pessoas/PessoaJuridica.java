package grupo8.pessoas;

import java.io.File;
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
    private PessoaJuridica[] sociosPJ;
    
    public PessoaJuridica(String cnpj, String inscricao, String razaoSocial, String nomeFantasia, String dtAbertura, 
                          String atividade, PessoaFisica[] sociosPF, 
                          String endereco, String telefone1, String telefone2, String email) {
        super(endereco, telefone1, telefone2, email);
        setCnpj(cnpj);
        setInscricao(inscricao);
        setRazaoSocial(razaoSocial);
        setNomeFantasia(nomeFantasia);
        setDtAbertura(dtAbertura);
        setAtividade(atividade);
        setSociosPF(sociosPF);
        id = ++contador;
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

        if(!inscricao.isEmpty() && !inscricao.matches("\\d{8,14}")) {
            throw new IllegalArgumentException("Digite apenas os números da inscrição estadual OU deixe em branco");
        }
        
        this.inscricao = inscricao;
    }
    
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        if(razaoSocial == null || razaoSocial.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a razão social (nome empresarial).");
        } 
        
        if(!razaoSocial.matches("[\\p{L}\\d\\.& ]{5,}")) {
            throw new IllegalArgumentException("Por favor, digite a razão social completa.");
        }
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        nomeFantasia = (nomeFantasia == null ? "" : nomeFantasia.trim());
        
        this.nomeFantasia = nomeFantasia;
    }

    public LocalDate getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(String dtAbertura) {
        if(dtAbertura == null || dtAbertura.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a data de abertura da empresa.");
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
        if(atividade == null || atividade.trim().isEmpty()){
            throw new IllegalArgumentException("Por favor, digite, pelo menos, a atividade principal da empresa.");
        }
        if(!atividade.matches("[\\p{L} ]{5,}")) {
            throw new IllegalArgumentException("Esperam-se somente letras no campo Atividade.");
        }
        this.atividade = atividade;
    }

    public PessoaFisica[] getSociosPF() {
        return sociosPF;
    }

    public void setSociosPF(PessoaFisica[] sociosPF) {
        if(sociosPF == null || sociosPF.length == 0) {
            throw new IllegalArgumentException("Ao menos um sócio deve ser associado ao cadastro da empresa!");
        }
        this.sociosPF = sociosPF;
    }
    
    public PessoaJuridica[] getSociosPJ() {
        return sociosPJ;
    }
    
    public void setSociosPJ(PessoaJuridica[] sociosPJ) {
        if(sociosPJ == null || sociosPJ.length == 0) {
            throw new IllegalArgumentException("Ao menos um sócio deve ser associado ao cadastro da empresa!");
        }
        this.sociosPJ = sociosPJ;
    }
    
    public void validaCnpj(String cnpj) {
        if(cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite um CNPJ.");
        }
        
        cnpj = cnpj.trim();
        
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
            int digito = cnpjTeste.charAt(i) - '0';
            soma += teste.get(i) * digito;
        }
        
        int dig1 = (soma % 11 < 2 ? 0 : 11 - soma % 11);
        cnpjTeste += dig1;
        
        teste.add(0, 6);
        soma = 0;
        for(int i = 0; i < 13; i++) {
            int digito = cnpjTeste.charAt(i) - '0';
            soma += teste.get(i) * digito;
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
        "\n    Razão Social: " + razaoSocial +
        "\n    Nome Fantasia: " +
        "\n    CNPJ: " + cnpj +
        "\n    Inscrição Estadual: " + inscricao +
        "\n    Data de Abertura: " + dtAbertura.format(formato) +
        "\n    Atividade: " + atividade +
        "\n    Endereço: " + endereco +
        "\n    Telefone 1: " + telefone1 +
        "\n    Telefone 2: " + telefone2 +
        "\n    Email: " + email;
    }

    public String qsa() {
        var sb = new StringBuilder();
        
        if ((sociosPF == null || sociosPF.length == 0) && (sociosPJ == null || sociosPJ.length == 0)) {
            sb.append("\nQUADRO SOCIETÁRIO: Nenhum sócio cadastrado.\n");
            return sb.toString();
        }
        
        sb.append("\nQUADRO SOCIETÁRIO:\n");
        
        int i = 0;
        if(sociosPF != null && sociosPF.length > 0) {
            for(PessoaFisica socio : sociosPF) {
                i++;
                sb.append("\nSÓCIO ").append(i).append("\n");
                sb.append(socio.toString()).append("\n");            
            }
        }
        
        if(sociosPJ != null && sociosPJ.length > 0) {
            for(PessoaJuridica socio : sociosPJ) {
                i++;
                sb.append("\nSÓCIO ").append(i).append("\n")
                  .append(toString()).append("\n");
                
                int j = 0;
                for(PessoaFisica socioPF : socio.getSociosPF()) {
                    j++;
                    sb.append("\n    ").append("Sócio ").append(j).append(" - PF (ID: ").append(socioPF.getId()).append(")\n\n");
                    sb.append("         Nome: ").append(socioPF.getNome()).append("\n");
                    sb.append("         CPF: ").append(socioPF.getCPF()).append("\n");
                    sb.append("         Telefone: ").append(socioPF.getTelefone1()).append("\n");
                    sb.append("         Email: ").append(socioPF.getEmail()).append("\n");
                }
            }
        }
        return sb.toString();
    }
    
    public String exibirDados() {
        var sb = new StringBuilder(this.toString()).append("\n");
        sb.append(this.qsa());
        return sb.toString();
    }
    
    public void salvar() throws FileNotFoundException, IOException {
        File pasta = new File("data");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
        
        try (FileOutputStream fileOut = new FileOutputStream("src/data/Pessoa"+ id +"_PJ.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
}
