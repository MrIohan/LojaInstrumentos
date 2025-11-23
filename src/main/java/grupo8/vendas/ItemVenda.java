package grupo8.vendas;

import grupo8.produto.Produto;
import java.io.Serializable;

public class ItemVenda implements Serializable {
    private Produto produto;
    private int quantidade;
    private double precoUnitario; 
    private double subtotal;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
        this.subtotal = this.quantidade * this.precoUnitario;
    }

    // Getters usados na JFrameTelaVenda
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
    public double getSubtotal() { return subtotal; }
    
    @Override
    public String toString() {
        // Verifica se o produto tem getDescricao (como na classe nova) ou getNome
        return produto.getDescricao() + " x" + quantidade;
    }
}
