package grupo8;

//https://www.devmedia.com.br/conceitos-basicos-sobre-expressoes-regulares-em-java/27539

import grupo8.papeis.Funcionario;
import grupo8.pessoas.Pessoa;
import grupo8.pessoas.PessoaFisica;
import grupo8.pessoas.PessoaJuridica;
import java.io.IOException;
import java.util.List;

public class Grupo8 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
       
        var pf1 = new PessoaFisica("Phellyppe Gomes", "04597219170", "22/02/1999", "Rua Rio Xingu, nº 16, Grande Terceiro, Cuiabá/MT", "65996816470", null , "phellyppe.matos@estudante.ifmt.edu.br");
        var pf2 = new PessoaFisica("Pedro Escobar", "48198072187", "12/03/1989", "Rua das Desgraças, SN, Inferno, Cracolândia/SP", "11930007000", null, "pedro157@vemprocrime.com.br");
        pf1.salvar();
        pf2.salvar();
        
        PessoaFisica[] sociosPj1 = {pf1, pf2}; 
        var pj1 = new PessoaJuridica(sociosPj1, "33711029000130", "", "Condominio GSC", null, "01/01/2009", "Gestão imobiliária", "Rua Desconhecida, SN, Duque de Caxias, Cuiabá/MT", "65933152100", null, "gsc@mechupa.com");
        pj1.salvar();
        
        //System.out.println(pj1.dadosPJ());
        //System.out.println(pf1.toString());
        //System.out.println(pf2.toString());
        
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
//                    System.out.println(pj.dadosPJ() + "\n");
//                }
//                default -> {
//                }
//            }
//        }

        var func1 = new Funcionario(null, "2024273420014", "vendedor", "ativo", "22/02/2025", "CLT", "R$ 2.100,0");
        System.out.println(func1.getSalario());
    }
}
