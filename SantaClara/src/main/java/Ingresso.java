public class Ingresso extends HolyTickets {
    private int programacaoId;
    private String assentoId;
    private TipoIngresso tipoIngresso;
    private String status;

    public Ingresso(int id, int programacaoId, String assentoId, TipoIngresso tipoIngresso, String status) {
        super(id);
        this.programacaoId = programacaoId;
        this.assentoId = assentoId;
        this.tipoIngresso = tipoIngresso;
        this.status = status;
    }

    public void vender() {
        this.status = "Vendido";
    }


}
