public class Assento extends SantaClara {
    private char fila;
    private int numero;
    private TipoAssento tipoAssento;
    private String statusAssento;
    private int programacaoID;

    public Assento(int id, char fila, int numero, TipoAssento tipoAssento, String statusAssento, int programacaoID) {
        super(id);
        this.fila = fila;
        this.numero = numero;
        this.tipoAssento = tipoAssento;
        this.statusAssento = statusAssento;
        this.programacaoID = programacaoID;
    }

    public void setStatusAssento(String statusAssento) {
        this.statusAssento = statusAssento;
    }

    public void ocupar() {
        this.setStatusAssento("Ocupado");
    }
}
