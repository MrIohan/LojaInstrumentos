package grupo8.pessoas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public abstract class Pessoa implements Serializable {
    
    public static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final long serialVersionUID = 1L;
    protected static int contador = 0;
    
    protected String endereco;
    protected String telefone1;
    protected String telefone2; // pode ser vazio
    protected String email;
    
    public Pessoa(String endereco, String telefone1, String telefone2, String email) {
        setEndereco(endereco);
        setTelefone1(telefone1);
        setTelefone2(telefone2);
        setEmail(email);
    }
        
    public String getEndereco() {
        return endereco;
    }
    
    public final void setEndereco(String endereco) {
        if(endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite um endereço.");
        } else if(endereco.length() < 10) {
            throw new IllegalArgumentException("Por favor, digite um endereço completo (rua, número, bairro, cidade, UF, CEP).\n");
        } else {
            this.endereco = endereco;
        }
    }

    public String getTelefone1() {
        return telefone1;
    }

    public final void setTelefone1(String telefone) {
        validarTelefone(telefone);
        telefone1 = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public final void setTelefone2(String telefone) {
        if(telefone != null && !telefone.trim().isEmpty()) {
            validarTelefone(telefone);
            telefone2 = telefone;
        } else {
            telefone2 = "";
        }
    }
    
    public String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite um email.\n");
        } 
        
        if(!email.trim().matches("^[a-zA-Z0-9\\._+-]+@[a-zA-Z0-9-]{2,}\\.([a-zA-Z0-9]{2,}\\.)*[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Por favor, digite um email válido.\n");
        } 

        this.email = email.trim();
    }
    
    public static String gerarCpf() {
        var random = new Random();
        String cpf = "";
        
        for(int i = 0; i < 9; i++) {
            cpf += random.nextInt(10);
        }
        
        int soma = 0;
        int peso = 10;
        for(int i = 0; i < 9; i++) {
            int digito = cpf.charAt(i) - '0';
            soma += digito * peso;
            peso--;
        }
        
        int dig1 = (soma % 11 < 2 ? 0 : 11 - soma % 11 );
        cpf += dig1;
        
        soma = 0;
        peso = 11;
        for(int i = 0; i < 10; i++) {
            int digito = cpf.charAt(i) - '0';
            soma += digito * peso;
            peso--;
        }
        
        int dig2 = (soma % 11 < 2 ? 0 : 11 - soma % 11 );
        cpf += dig2;
        
        return cpf;
    }
    
    public void validarTelefone(String telefone) {
        if(telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Por favor, digite um número de telefone");
        } 
        
        if(!telefone.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Por favor, digite um número de telefone com DDD, sem caracteres.\n");
        }
    }
    
    public static List<Pessoa> carregar() throws IOException, ClassNotFoundException {
        String diretorio = "src/data/";
        String padraoArquivo = "Pessoa\\d+_(PF|PJ)\\.ser";
        
        File pasta = new File(diretorio);
        Pattern pattern = Pattern.compile(padraoArquivo); 
        if (!pasta.exists()) {
            pasta.mkdirs(); // Cria a pasta e quaisquer pais necessários
        }
        File[] arquivos = pasta.listFiles((dir, name) -> pattern.matcher(name).matches()); 
        
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        if (arquivos == null) {
            System.out.println("Diretório não existe ou não contem arquivos compatíveis.");
            return pessoas;
        }
        
        for (File arq : arquivos) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arq))) {
                Pessoa p = (Pessoa) ois.readObject(); 
                pessoas.add(p);
            } catch (Exception e) {
                System.out.println("Erro ao ler arquivo: " + arq.getName() + "-" + e.getMessage());
            }
        }
        return pessoas;
    }
}
