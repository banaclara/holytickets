import java.time.LocalDate;
public class Vendas extends HolyTickets {
    private int ingressoId;
    private LocalDate dataVenda;

    public Vendas(int id, int ingressoId, LocalDate dataVenda) {
        super(id);
        this.ingressoId = ingressoId;
        this.dataVenda = dataVenda;
    }

}