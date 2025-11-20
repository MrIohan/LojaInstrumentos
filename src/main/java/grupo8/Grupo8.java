package grupo8;

import grupo8.papeis.Funcionario;
import grupo8.pessoas.Pessoa;
import grupo8.pessoas.PessoaFisica;
import grupo8.pessoas.PessoaJuridica;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Grupo8 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        var sc = new Scanner(System.in);
        
        var pf1 = new PessoaFisica("Phellyppe Gomes", Pessoa.geraCpf(), "22/02/1999", "Rua Rio Xingu, nº 16, Grande Terceiro, Cuiabá/MT", "65996816470", null , "phellyppe.matos@estudante.ifmt.edu.br");
        var pf2 = new PessoaFisica("Pedro Escobar", Pessoa.geraCpf(), "12/03/1989", "Rua das Desgraças, 0, Inferno, Cracolândia/SP", "11930007000", null, "pedro157@vemprocrime.com.br");
        var pf3 = new PessoaFisica("Jacinto Leite", Pessoa.geraCpf(), "05/09/2004", "Rua Passivas Reinam, 24, Quero-Quero Pica-pau, Ratanabá", "69969696969", "", "mcleitinho@filhosdocatra.com.br");
        
        PessoaFisica[] sociosPF1 = {pf1};
        PessoaFisica[] sociosPF2 = {pf2, pf1, pf3};
        
        var pj1 = new PessoaJuridica("33711029000130", "", "Condominio GSC", null, "01/01/2009", "Gestão imobiliária", "Rua Desconhecida, 0, Duque de Caxias, Cuiabá/MT", "65933152100", null, "gsc@mechupa.com");
        pj1.setSociosPF(sociosPF1);
        
        var pj2 = new PessoaJuridica("35794505000150", "", "Consorcio GSC", "", "01/01/2019", "Gestão imobiliária", "Rua Desconhecida, 0, Duque de Caxias, Cuiabá/MT", "65933152100", null, "gsc@mechupa.com");
        pj2.setSociosPF(sociosPF2);
        
        PessoaJuridica[] socioPJ1 = {pj2};
        pj1.setSociosPJ(socioPJ1);

        System.out.println(pf1.toString());
        System.out.println(pj1.exibirDados());
        System.out.println(pj2.exibirDados());
        
        var func1 = new Funcionario(pf1, "20423575737", "Vendedor", "15/10/2025", "CLT", "2700");
        System.out.println(func1.toString());
        
//        List<Pessoa> lista = Pessoa.carregar();
//
//        for(int i = 0; i < lista.size(); i++) {
//            Pessoa p = lista.get(i);
//
//            switch (p) {
//                case PessoaFisica pf -> {
//                    System.out.printf("Pessoa %d - PF \n", i+1);
//                    System.out.println(pf.toString() + "\n");
//
//                }
//                case PessoaJuridica pj -> {
//                    System.out.printf("Pessoa %d - PJ \n", i+1);
//                    System.out.println(pj.exibirDados() + "\n");
//                }
//                default -> {
//                }
//            }
//        }
    }
}