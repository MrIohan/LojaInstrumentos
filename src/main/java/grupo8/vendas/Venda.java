package grupo8.vendas;

import grupo8.papeis.Cliente;
import grupo8.papeis.Funcionario;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public final class Venda implements Serializable {
    
    public static final long serialVersionUID = 1L;
    public static int idVenda;
    
    private Cliente cliente;
    private Funcionario vendedor;
    private List<ItemVenda> produtos;
    
    private final LocalDate dtVenda;
    private int qtdTotalProdutos;
    private double vlrTotalProdutos; //soma dos preços brutos de cada produto
    private double vlrTotalDescontos; //soma dos descontos de cada produto (adicionar esse atributo em Produto)
    private double vlrTotalRecebido; //vlrTotalBruto - vlrTotalDescontos - bônus do cliente
    private double bonus; //toda compra gera bônus, com validade de x dias, aplicado automaticamente na próxima compra
    
    public Venda() {
        setCliente(cliente);
        setVendedor(vendedor);
        setProdutos(produtos);
        dtVenda = LocalDate.now();
        
        for(ItemVenda linha : produtos) {
            qtdTotalProdutos += linha.getQuantidade();
            vlrTotalProdutos += linha.getVlrTotalProduto();
            vlrTotalDescontos += linha.getVlrTotalDesconto();
        }
        
        double vlrBase = vlrTotalProdutos - vlrTotalDescontos;
        setBonus(vlrBase);
        
        vlrTotalRecebido = vlrTotalProdutos - vlrTotalDescontos - bonus;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if(cliente == null ) {
            throw new IllegalArgumentException("Cliente não cadastrado.");
        }
        this.cliente = cliente;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        if(vendedor == null ) {
            throw new IllegalArgumentException("Vendedor(a) não cadastrado(a).");
        }
        this.vendedor = vendedor;
    }

    public List<ItemVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemVenda> produtos) {
        if(produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Não é possível registrar uma venda sem produtos.");
        }
        this.produtos = produtos;
    }

    public LocalDate getDtVenda() {
        return dtVenda;
    }

    public int getQtdTotalProdutos() {
        return qtdTotalProdutos;
    }

    public double getVlrTotalProdutos() {
        return vlrTotalProdutos;
    }

    public double getVlrTotalDescontos() {
        return vlrTotalDescontos;
    }

    public double getVlrTotalRecebido() {
        return vlrTotalRecebido;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double vlrBase) {
        if(vlrBase < 500) {
            bonus = vlrBase* 0.1;
        } else if(vlrBase < 1000) {
            bonus = vlrBase* 0.08;
        } else if(vlrBase < 3000) {
            bonus = vlrBase* 0.065;
        } else {
            bonus = vlrBase* 0.05;
        }
    }
    
}
