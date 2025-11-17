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
    private static int id = 0;
    
    private Pessoa dadosPessoa;
    private String matricula;
    private String cargo; //gerente, vendedor, caixa
    private String status; //ativo, desligado, afastado
    private LocalDate dtAdmissao;
    private LocalDate dtDemissao; //não obrigatório
    private int tempoServico;
    private String tipoContrato; //CLT ou PJ
    private double salario;
    
    public Funcionario(Pessoa dadosPessoa, String matricula , String cargo, String status, String dtAdmissao, String tipoContrato, String salario) {
        id++;
        setDadosPessoa(dadosPessoa);
        setMatricula(matricula);
        setCargo(cargo);
        setStatus(status);
        setDtAdmissao(dtAdmissao);
        setTipoContrato(tipoContrato);
        setSalario(salario);
    }

    public Pessoa getDadosPessoa() {
        return dadosPessoa;
    }

    public void setDadosPessoa(Pessoa dadosPessoa) {
        if(dadosPessoa == null) {
            throw new IllegalArgumentException("Você deve fazer o cadastro da pessoa primeiro!");
        }
        this.dadosPessoa = dadosPessoa;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        matricula = (matricula == null ? "" : matricula.trim());
        if(matricula.isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a matrícula do(a) funcionário(a).");
        }
        this.matricula = matricula;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        cargo = (cargo == null ? "" : cargo.trim());
        if(!cargo.matches("(?i)Gerente|(?i)Vendedor|(?i)Caixa")) {
            throw new IllegalArgumentException("Por favor, digite um cargo existente: Gerente, Vendedor ou Caixa.");
        }
        
        this.cargo = cargo.substring(0,1).toUpperCase() + cargo.substring(1).toLowerCase();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = (status == null ? "" : status.trim());
        if(!status.matches("(?i)Ativo|(?i)Desligado|(?i)Afastado")) {
            throw new IllegalArgumentException("Status válidos: Ativo, Desligado e Afastado");
        }
        
        this.status = status.substring(0,1).toUpperCase() + status.substring(1).toLowerCase();
    }
    
    public LocalDate getDtAdmissao() {
        return dtAdmissao;
    }
    
    public void setDtAdmissao(String data) {
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("A data de admissão não pode ser vazia.");
        }
        
        try {
            this.dtAdmissao = LocalDate.parse(data, formato);
        } catch(Exception e) {
            throw new IllegalArgumentException("Data inválida. Use o formato DD/MM/AAAA.");
        }   
    }
    
    public LocalDate getDtDemissao() {
        return dtDemissao;
    }

    public void setDtDemissao(String data) {
        LocalDate hoje = LocalDate.now();
        
        if (data == null || data.trim().isEmpty()) {
            this.dtDemissao = null;
            return;
        }
        
        try {
            LocalDate temp = LocalDate.parse(data, formato);
            
            if(temp.isBefore(dtAdmissao)) {
                throw new IllegalArgumentException("A data de demissão não pode ser inferior à de admissão.");
            }
            this.dtDemissao = temp;
        } catch(Exception e) {
            throw new IllegalArgumentException("Data inválida. Use o formato DD/MM/AAAA.");
        }
        
        if(dtDemissao.isBefore(hoje)) {
            status = "Desligado";
            tempoServico = Period.between(dtAdmissao, dtDemissao).getMonths();
        } else {
            tempoServico = Period.between(dtAdmissao, hoje).getMonths(); //mudar!
        }
    }

    public int getTempoServico() {
        if(dtDemissao == null) {
            tempoServico = Period.between(dtAdmissao, LocalDate.now()).getMonths();
        } else {
            tempoServico = Period.between(dtAdmissao, dtDemissao).getMonths();
        }
        return tempoServico;
    }
    
    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        tipoContrato = (tipoContrato == null ? "" : tipoContrato.trim());
        if(!tipoContrato.matches("CLT|PJ")) {
            throw new IllegalArgumentException("Por favor, digite CLT ou PJ.");
        } 
        this.tipoContrato = tipoContrato;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        if(salario == null || salario.trim().isEmpty()) {
            throw new IllegalArgumentException("O salário não pode ser vazio!");
        }

        salario = salario.trim().replaceAll("[^0-9,.]", "").replaceAll("^0+", "");
        
        if(salario.isEmpty()) {
            throw new IllegalArgumentException("Nenhum valor numérico encontrado");
        }
        
        if(salario.contains(",")) {
            int i = salario.length() - salario.indexOf(",");
            while(salario.contains(",") && salario.length() - salario.indexOf(",") > 3) {
                int limite = salario.indexOf(",") + 1;
                String salario1 = salario.substring(0, limite);
                String salario2 = salario.substring(limite);
                salario1 = salario1.replace(",", "");
                salario = salario1 + salario2;
                i = salario.length() - salario.indexOf(",");
            }
        }
        
        if(salario.contains(".")) {
            int i = salario.length() - salario.indexOf(".");
            while(salario.contains(".") && i > 3) {
                int limite = salario.indexOf(".") + 1;
                String salario1 = salario.substring(0, limite);
                String salario2 = salario.substring(limite);
                salario1 = salario1.replace(".", "");
                salario = salario1 + salario2;
                i = salario.length() - salario.indexOf(".");
            }
        }
        
        salario = salario.replace(",", ".");
        
        try {
            double salarioFormatado = Double.parseDouble(salario);
            
            if(salarioFormatado < 1518) {
                throw new IllegalArgumentException("O salário não pode ser inferior ao mínimo previsto!");
            } else {
                this.salario = salarioFormatado;
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("Digite somente os números do salário, usando ponto como separador decimal.");
        }
    }
    
    public void salvar() throws FileNotFoundException, IOException {
        try (FileOutputStream fileOut = new FileOutputStream("src/data/Funcionario"+ id +".ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
    
    @Override
    public String toString() {
        
        var sb = new StringBuilder();
        
        switch(dadosPessoa) {
            case PessoaFisica pf -> {
                sb.append("Nome: ").append(pf.getNome())
                  .append("CPF: ").append(pf.getCPF())
                  .append("Telefone: ").append(pf.getTelefone1())
                  .append("Email: ").append(pf.getEmail());
            }
            case PessoaJuridica pj -> {
                
            }
            default -> {}
        }
        return sb.toString();
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
