import java.time.LocalDate; // Importe LocalDateTime, não Date

public class Vendas extends SantaClara {
    private LocalDate dataVenda; // Use LocalDateTime em vez de Date
    private double valorVenda;
    private String formaPagamento;
    private int clienteID;

    public Vendas(int id, LocalDate dataVenda, double valorVenda, String formaPagamento, int clienteID) { // Use LocalDateTime em vez de Date
        super(id);
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
        this.formaPagamento = formaPagamento;
        this.clienteID = clienteID;
    }

}
