package grupo8.pessoas;

import java.io.File;
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
    
    public PessoaFisica(String nome, String cpf, String dtNasc, String endereco, String telefone1, String telefone2, String email) {
        super(endereco, telefone1, telefone2, email);
        setNome(nome);
        setCPF(cpf);
        setDtNasc(dtNasc);
        id = ++contador;
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite um nome!");
        }
        
        if(!nome.matches("[\\p{L} ]{5,}")) {
            throw new IllegalArgumentException("Por favor, digite, ao menos, o nome e último sobrenome da pessoa.\n");
        }
        this.nome = nome;        
    }
    
    public String getCPF() {
        return cpf;
    }
    
    public void setCPF(String cpf) {
        validarCPF(cpf);
        this.cpf = cpf;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        if (dtNasc == null || dtNasc.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite a data de nascimento.");
        }
        
        LocalDate hoje = LocalDate.now();
        LocalDate dtConvertida; 
        
        try {
            dtConvertida = LocalDate.parse(dtNasc, formato);
        } catch(Exception e) {
            throw new IllegalArgumentException("Data inválida. Por favor, use o formato DD/MM/AAAA.");
        }        
        
        if(dtConvertida.isAfter(hoje)) {
            throw new IllegalArgumentException("A data de nascimento não pode ser futura.");
        }
        
        this.dtNasc = dtConvertida;
    }
    
    public int getIdade() {
        LocalDate hoje = LocalDate.now();
        return Period.between(dtNasc, hoje).getYears();
    }
    
    public void validarCPF(String cpf) {
        if(cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite o CPF.");
        } 

        cpf = cpf.trim();
        
        if(!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Por favor, certifique-se de digitar somente os 11 números.");
        }
        
        if(cpf.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido. Os dígitos não podem ser todos iguais!");
        }
        
        String cpfTeste = cpf.substring(0,9);
        
        int soma = 0;
        int peso = 10;
        for(int i = 0; i < 9; i++) {
            int digito = cpfTeste.charAt(i) - '0';
            soma += digito * peso;
            peso--;
        }
        
        int dig1 = (soma % 11 < 2 ? 0 : 11 - soma % 11 );
        cpfTeste += dig1;
        
        soma = 0;
        peso = 11;
        for(int i = 0; i < 10; i++) {
            int digito = cpfTeste.charAt(i) - '0';
            soma += digito * peso;
            peso--;
        }
        
        int dig2 = (soma % 11 < 2 ? 0 : 11 - soma % 11 );
        cpfTeste += dig2;
        
        if(!cpf.equals(cpfTeste)) {
            throw new IllegalArgumentException("CPF inválido. Por favor, verifique os números digitados.");
        }
    }
    
    @Override
    public String toString() {
        return 
            "\n    Nome: " + nome +
            "\n    CPF: " + cpf +
            "\n    Data de Nascimento: " + dtNasc.format(formato) +
            "\n    Idade: " + getIdade() +
            "\n    Endereço: " + endereco +
            "\n    Telefone 1: " + telefone1 +
            "\n    Telefone 2: " + telefone2 +
            "\n    Email: " + email;
    }
    
    public void salvar() throws FileNotFoundException, IOException {
        File pasta = new File("data");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
        
        try (FileOutputStream fileOut = new FileOutputStream("src/data/Pessoa"+ id +"_PF.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
             out.writeObject(this);
        }
    }
}
