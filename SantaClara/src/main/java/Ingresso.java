import java.time.LocalDate; // Importe LocalDate, não Date

public class Ingresso extends SantaClara implements IngressoInter {
    private int vendaID;
    private String codigoIngresso;
    private TipoIngresso tipoIngresso;
    private LocalDate dataValidade;
    private String statusIngresso;

    public Ingresso(int id, int vendaID, String codigoIngresso, TipoIngresso tipoIngresso, LocalDate dataValidade, String statusIngresso) { // Use LocalDate em vez de Date
        super(id);
        this.vendaID = vendaID;
        this.codigoIngresso = codigoIngresso;
        this.tipoIngresso = tipoIngresso;
        this.dataValidade = dataValidade;
        this.statusIngresso = statusIngresso;
    }

    @Override
    public void setStatusIngresso(String status) {
        this.statusIngresso = status;
    }

    @Override
    public String getStatusIngresso() {
        return this.statusIngresso;
    }

    public void vender() {
        this.setStatusIngresso("Vendido");
    }

    public int getVendaID() {
        return vendaID;
    }
}
