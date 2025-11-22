package grupo8.vendas;

import grupo8.produto.Produto;

public final class ItemVenda {
    
    private Produto produto;
    private int quantidade;
    private double vlrTotalProduto;
    private double vlrTotalDesconto;
    private double vlrTotalFinal;
    
    public ItemVenda(Produto produto, int quantidade) {
        setProduto(produto);
        setQuantidade(quantidade);
        vlrTotalProduto = produto.getPreco() * quantidade;
        //vlrTotalDesconto; façam como quiser
        vlrTotalFinal = vlrTotalProduto - vlrTotalDesconto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        if(produto == null) {
            throw new IllegalArgumentException("Produto não cadastrado");
        }
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        verificarEstoque(quantidade);
        atualizarEstoque(quantidade);
        this.quantidade = quantidade;
    }

    public double getVlrTotalProduto() {
        return vlrTotalProduto;
    }

    public double getVlrTotalDesconto() {
        return vlrTotalDesconto;
    }

    public double getVlrTotalFinal() {
        return vlrTotalFinal;
    }
    
    public void verificarEstoque(int quantidade) {
        if(quantidade > produto.getQntEstoque()) {
            throw new IllegalArgumentException("ATENÇÃO: esse produto tem apenas " + produto.getQntEstoque() + " unidade(s) em estoque");
        }
    }
    
    public void atualizarEstoque(int quantidade) {
        produto.setQntEstoque(String.valueOf(produto.getQntEstoque() - quantidade));
    }
}
