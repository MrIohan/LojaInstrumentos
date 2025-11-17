package grupo8.pessoas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;

public final class PessoaFisica extends Pessoa {
    
    private final int id;
    private String nome;
    private String cpf;
    private LocalDate dtNasc;
    private int idade;
    
    public PessoaFisica(String nome, String cpf, String dtNasc, String endereco, String telefone1, String telefone2, String email) {
        super(endereco, telefone1, telefone2, email);
        id = contador;
        
        setNome(nome);
        setCPF(cpf);
        setDtNasc(dtNasc);
    }
    
    public int getID() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public final void setNome(String nome) {
        nome = (nome == null ? "" : nome.trim());
        if(!nome.matches("[\\p{L} ]{5,}")) {
            throw new IllegalArgumentException("Por favor, digite seu nome completo, sem abreviações.\n");
        }
        this.nome = nome;        
    }
    
    public String getCPF() {
        return cpf;
    }
    
    public void setCPF(String cpf) {
        validaCPF(cpf);
        this.cpf = cpf;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        if (dtNasc == null || dtNasc.trim().isEmpty()) {
            throw new IllegalArgumentException("A data de nascimento não pode ser vazia.");
        }
        
        LocalDate hoje = LocalDate.now();
        LocalDate dtConvertida; 
        
        try {
            dtConvertida = LocalDate.parse(dtNasc, formato);
        } catch(Exception e) {
            throw new IllegalArgumentException("Data inválida. Use o formato DD/MM/AAAA.");
        }        
        
        if(dtConvertida.isAfter(hoje)) {
            throw new IllegalArgumentException("A data de nascimento não pode ser futura.");
        }
        
        this.dtNasc = dtConvertida;
        idade = Period.between(this.dtNasc, hoje).getYears();
    }
    
    public int getIdade() {
        return idade;
    }
    
    public void validaCPF(String cpf) {
        if(cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("Digite o CPF.");
        } else {
            cpf = cpf.trim();
        }
        
        if(!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Certifique-se de digitar somente os 11 números.");
        }
        
        if(cpf.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido. Os dígitos não podem ser todos iguais!");
        }
        
        String cpfTeste = cpf.substring(0,9);
        
        int peso = 10;
        int soma = 0;
        for(int i = 0; i < 9; i++) {
            int j = cpfTeste.charAt(i) - '0';
            soma += peso * j;
            peso--;
        }
        
        int dig1 = (soma % 11 < 2 ? 0 : 11 - soma % 11 );
        cpfTeste += dig1;
        
        soma = 0;
        peso = 11;
        for(int i = 0; i < 10; i++) {
            int j = cpfTeste.charAt(i) - '0';
            soma += peso * j;
            peso--;
        }
        
        int dig2 = (soma % 11 < 2 ? 0 : 11 - soma % 11 );
        cpfTeste += dig2;
        
        if(!cpf.equals(cpfTeste)) {
            throw new IllegalArgumentException("Ops, esse CPF não existe. Por favor, verifique os números digitados.");
        }
    }
    
    @Override
    public String toString() {
        return 
            "\nNome: " + nome +
            "\nCPF: " + cpf +
            "\nData de Nascimento: " + dtNasc.format(formato) +
            "\nIdade: " + idade +
            "\nEndereço: " + endereco +
            "\nTelefone 1: " + telefone1 +
            "\nTelefone 2: " + telefone2 +
            "\nEmail: " + email +
            "\n\n---------------------------------------------------------------";
    }
    
    
    
    public void salvar() throws FileNotFoundException, IOException {
        try (FileOutputStream fileOut = new FileOutputStream("src/data/Pessoa"+ id +"_PF.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
}
