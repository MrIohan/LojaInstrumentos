package grupo8.vendas;

import grupo8.pessoas.Pessoa; // Assumindo que voce tem essa classe
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable {

    private static int contadorId = 0; // Simples gerador de ID

    private int id;
    private LocalDateTime dataHora;
    private String nomeCliente; // Simplificando: Guardando só o nome por enquanto
    private List<ItemVenda> itens;
    private double valorTotal;

    public Venda(String nomeCliente) {
        this.id = ++contadorId;
        this.dataHora = LocalDateTime.now();
        this.nomeCliente = nomeCliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        this.valorTotal += item.getSubtotal();
    }

    public void removerItem(int index) {
        ItemVenda item = this.itens.get(index);
        this.valorTotal -= item.getSubtotal();
        this.itens.remove(index);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDataFormatada() {
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }
}
