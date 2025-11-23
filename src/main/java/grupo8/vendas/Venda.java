package grupo8.vendas;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable {
    
    private static int contadorId = 0;
    private int id;
    private LocalDateTime dataHora;
    private String nomeCliente; // Usamos String para facilitar por enquanto
    private List<ItemVenda> itens;
    private double valorTotal;

    // Construtor simplificado para a Tela de Venda funcionar
    public Venda(String nomeCliente) {
        this.id = ++contadorId; // Gera ID automático simples
        this.dataHora = LocalDateTime.now();
        this.nomeCliente = nomeCliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    // Métodos usados na Tela
    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        this.valorTotal += item.getSubtotal();
    }

    public int getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
    
    // Setter para atualizar o cliente no final da venda
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDataFormatada() {
        if (dataHora == null) return "---";
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
