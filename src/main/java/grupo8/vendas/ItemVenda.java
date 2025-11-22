package grupo8.vendas;

import grupo8.produto.Produto;
import java.io.Serializable;

public class ItemVenda implements Serializable {
    private Produto produto;
    private int quantidade;
    private double precoUnitario; // Guardamos o preço do momento da venda!
    private double subtotal;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
        this.subtotal = this.quantidade * this.precoUnitario;
    }

    // Getters importantes para a tabela
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
    public double getSubtotal() { return subtotal; }
    
    @Override
    public String toString() {
        return produto.getDescricao() + " x" + quantidade;
    }
}