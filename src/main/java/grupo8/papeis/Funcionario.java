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
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class Funcionario implements Serializable {    
    
    private static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final long serialVersionUID = 1L;
    public static int contador = 0;
    
    private final int matricula; 
    private Pessoa dadosPessoa;
    private String pis;
    private String cargo; //gerente, vendedor, caixa
    private String status; //ativo, desligado, afastado
    private LocalDate dtAdmissao;
    private LocalDate dtDemissao; //não obrigatório
    private String tipoContrato; //CLT ou PJ
    private double salario;
    
    public Funcionario(Pessoa dadosPessoa, String pis, String cargo, String dtAdmissao, String tipoContrato, String salario) {
        setDadosPessoa(dadosPessoa);
        setPis(pis);
        setCargo(cargo);
        setDtAdmissao(dtAdmissao);
        setTipoContrato(tipoContrato);
        setSalario(salario);
        status = "Ativo";
        matricula = ++contador;
    }    
    
    public int getMatricula() {
        return matricula;
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
    
    public String getPis() {
        return pis;
    }
    
    public void setPis(String pis) {
        validarPis(pis);
        this.pis = pis;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if(cargo == null || cargo.trim().isEmpty()){
            throw new IllegalArgumentException("Por favor, digite o cargo do funcionário.");
        }
        
        if(!cargo.matches("(?i)Gerente|(?i)Vendedor|(?i)Caixa")) {
            throw new IllegalArgumentException("Por favor, digite um cargo existente: Gerente, Vendedor ou Caixa.");
        }
        
        this.cargo = cargo.substring(0,1).toUpperCase() + cargo.substring(1).toLowerCase();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o status do funcionário (Ativo, Desligado ou Afastado).");
        }
        
        if(!status.matches("(?i)Ativo|(?i)Desligado|(?i)Afastado")) {
            throw new IllegalArgumentException("Status válidos: Ativo, Desligado e Afastado");
        }
        
        this.status = status.substring(0,1).toUpperCase() + status.substring(1).toLowerCase();
    }
    
    public LocalDate getDtAdmissao() {
        return dtAdmissao;
    }
    
    public void setDtAdmissao(String dtAdmissao) {
        if (dtAdmissao == null || dtAdmissao.trim().isEmpty()) {
            throw new IllegalArgumentException("A data de admissão não pode ser vazia.");
        }
        
        LocalDate hoje = LocalDate.now();
        LocalDate dtConvertida;
        
        try {
            dtConvertida = LocalDate.parse(dtAdmissao, formato);
        } catch(Exception e) {
            throw new IllegalArgumentException("Data de admissão inválida. Use o formato DD/MM/AAAA.");
        }
        
        if(dtConvertida.isAfter(hoje)) {
            throw new IllegalArgumentException("A data de admissão não pode ser anterior à data atual.");
        }
        
        this.dtAdmissao = dtConvertida;
    }
    
    public LocalDate getDtDemissao() {
        return dtDemissao;
    }

    public void setDtDemissao(String dtDemissao) {
        if (dtDemissao == null || dtDemissao.trim().isEmpty()) {
            this.dtDemissao = null;
            return;
        }
        
        LocalDate dtConvertida;
        try {
            dtConvertida = LocalDate.parse(dtDemissao, formato);
        } catch(Exception e) {
            throw new IllegalArgumentException("Data de demissão inválida. Use o formato DD/MM/AAAA.\n"
                    + "\nATENÇÃO: só preencha esse campo se houver uma data de demissão.");
        }
        
        if(dtConvertida.isBefore(dtAdmissao)) {
            throw new IllegalArgumentException("A data de demissão não pode ser inferior à de admissão."
                    + "\nATENÇÃO: só preencha esse campo se houver uma data de demissão.");
        }
        
        this.dtDemissao = dtConvertida;
    }

    public String getTempoServico() {
        String tempoServico = "O tempo de serviço deste funcionário é: ";
        if(dtDemissao == null) {
            tempoServico += Period.between(dtAdmissao, LocalDate.now()).getYears() + " ano(s), " +
                Period.between(dtAdmissao, LocalDate.now()).getMonths() + " mês(es) e " + 
                Period.between(dtAdmissao, LocalDate.now()).getDays() + " dia(s).";
        } else {
            tempoServico += Period.between(dtAdmissao, dtDemissao).getYears() + " ano(s), " +
                Period.between(dtAdmissao, dtDemissao).getMonths() + " mês(es) e " + 
                Period.between(dtAdmissao, dtDemissao).getDays() + " dia(s).";
        }
        return tempoServico;
    }
    
    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        if(tipoContrato == null || tipoContrato.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o tipo do contrato de trabalho.");
        }
        if(!tipoContrato.matches("(?i)CLT|(?i)PJ")) {
            throw new IllegalArgumentException("Por favor, digite CLT ou PJ.");
        } 
        this.tipoContrato = tipoContrato.toUpperCase();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        if(salario == null || salario.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o salário do funcionário.");
        }

        salario = salario.trim().replaceAll("[^0-9,.]", "").replaceAll("^0+", "");
        
        if(salario.isEmpty()) {
            throw new IllegalArgumentException("Nenhum valor numérico encontrado");
        }
        
        double salarioFormatado;
        try {
            salarioFormatado = Double.parseDouble(salario);
        } catch(Exception e) {
            throw new IllegalArgumentException("Por favor, digite o salário usando ponto como separador decimal, sem separar os milhares.\n"
                    + "\nExemplo: 2100");
        }
        
        if(salarioFormatado <= 0) {
                throw new IllegalArgumentException("Por favor, digite um salário maior que 0.");
        }
        
        this.salario = salarioFormatado;
    }

    public void validarPis(String pis) {
        if(pis == null || pis.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o PIS do funcionário.");
        } 

        pis = pis.trim();
        
        if(!pis.matches("\\d{11}")) {
            throw new IllegalArgumentException("PIS inválido. Por favor, certifique-se de digitar somente os 11 números.");
        }
        
        String pisTeste = pis.substring(0,10);
        
        int[] peso = {3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for(int i = 0; i < peso.length; i++) {
            int digito = pisTeste.charAt(i) - '0';
            soma += digito * peso[i];
        }
        
        pisTeste += (11 - soma % 11);
        
        if(!pis.equals(pisTeste)) {
            throw new IllegalArgumentException("PIS inválido. Por favor, verifique os números digitados.");
        }
    }
    
    @Override
    public String toString() {
        var sb = new StringBuilder();
        
        sb.append("\nDADOS DO FUNCIONÁRIO\n")
          .append("\n    Matrícula: ").append(matricula);
        
        sb.append(dadosPessoa.toString()).append("\n");
        
        sb.append("\n    PIS: ").append(pis)
          .append("\n    Cargo: ").append(cargo)
          .append("\n    Status: ").append(status)
          .append("\n    Data de Admissão: ").append(dtAdmissao.format(formato))
          .append("\n    Data de Demissão: ").append((dtDemissao == null ? "" : dtDemissao.format(formato)))
          .append("\n    Tipo de Contrato: ").append(tipoContrato)
          .append("\n    Salário: R$").append(String.format("%.2f", salario));
                
        return sb.toString();
    }
    
    public void salvar() throws FileNotFoundException, IOException {
        File pasta = new File("data");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
        
        try (FileOutputStream fileOut = new FileOutputStream("src/data/Funcionario"+ matricula +".ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
    
    public static List<Funcionario> carregar() throws IOException, ClassNotFoundException {
        String diretorio = "src/data/";
        String padraoArquivo = "Funcionario\\d+\\.ser";
        
        File pasta = new File(diretorio);
        Pattern pattern = Pattern.compile(padraoArquivo); 
        File[] arquivos = pasta.listFiles((dir, name) -> pattern.matcher(name).matches()); 
        
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        if (arquivos == null) {
            System.out.println("Diretório não existe ou não contem arquivos compatíveis.");
            return funcionarios;
        }
        
        for (File arq : arquivos) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arq))) {
                Funcionario f = (Funcionario) ois.readObject(); 
                funcionarios.add(f);
            } catch (Exception e) {
                System.out.println("Erro ao ler arquivo: " + arq.getName() + "-" + e.getMessage());
            }
        }
        return funcionarios;
    }
}
