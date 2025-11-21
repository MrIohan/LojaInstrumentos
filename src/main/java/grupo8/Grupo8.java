package grupo8;

import grupo8.papeis.Cliente;
import grupo8.papeis.Funcionario;
import grupo8.pessoas.Pessoa;
import grupo8.pessoas.PessoaFisica;
import grupo8.pessoas.PessoaJuridica;
import grupo8.produto.Produto;
import grupo8.produto.Acessorio;
import grupo8.persistencia.GerenciadorDados;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;

public class Grupo8 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
            GerenciadorDados g = new GerenciadorDados();
            Acessorio p = new Acessorio("Amplificador","Lazaro Crauz","12345678"," Amplificador preto dos top",
                    "MegaSom","01/01/1902","Australia","Obsidiana","preto","1000.00","58250","2","30");
            ArrayList<Produto> ListaProdutos = new ArrayList<>();
            ListaProdutos.add(p);
            g.salvarLista(ListaProdutos);
        
//        var sc = new Scanner(System.in);
//        
//        var pf1 = new PessoaFisica("Phellyppe Gomes", Pessoa.geraCpf(), "22/02/1999", "Rua Rio Xingu, nº 16, Grande Terceiro, Cuiabá/MT", "65996816470", null , "phellyppe.matos@estudante.ifmt.edu.br");
//        var pf2 = new PessoaFisica("Pedro Escobar", Pessoa.geraCpf(), "12/03/1989", "Rua das Desgraças, 0, Inferno, Cracolândia/SP", "11930007000", null, "pedro157@vemprocrime.com.br");
//        var pf3 = new PessoaFisica("Jacinto Leite", Pessoa.geraCpf(), "05/09/2004", "Rua Passivas Reinam, 24, Quero-Quero Pica-pau, Ratanabá", "69969696969", "", "mcleitinho@filhosdocatra.com.br");
//        
//        PessoaFisica[] sociosPF1 = {pf1};
//        PessoaFisica[] sociosPF2 = {pf2, pf1, pf3};
//        
//        var pj1 = new PessoaJuridica("33711029000130", "", "Condominio GSC", null, "01/01/2009", "Gestão imobiliária", "Rua Desconhecida, 0, Duque de Caxias, Cuiabá/MT", "65933152100", null, "gsc@mechupa.com");
//        pj1.setSociosPF(sociosPF1);
//        
//        var pj2 = new PessoaJuridica("35794505000150", "", "Consorcio GSC", "", "01/01/2019", "Gestão imobiliária", "Rua Desconhecida, 0, Duque de Caxias, Cuiabá/MT", "65933152100", null, "gsc@mechupa.com");
//        pj2.setSociosPF(sociosPF2);
//        
//        PessoaJuridica[] socioPJ1 = {pj2};
//        pj1.setSociosPJ(socioPJ1);

//        System.out.println(pf1.toString());
//        System.out.println(pj1.exibirDados());
//        System.out.println(pj2.exibirDados());
        
//        var func1 = new Funcionario(pf1, "20423575737", "Vendedor", "15/10/2025", "CLT", "2700");;;;
//        System.out.println(func1.toString());
//        
//        var cliente1 = new Cliente(pj1);
//        System.out.println(cliente1.toString());
//        
//        var acessorio1 = new Acessorio("Braçadeira", "Todos os violões", "P001", "Braçadeira Gianini", "Gianini", "10/10/2025", "Brasil", "Gala condensada", "Branca", "50", "20", "5", "0");
//        System.out.println(acessorio1.toString());
//        
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