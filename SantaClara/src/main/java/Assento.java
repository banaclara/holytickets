public class Assento extends HolyTickets {
    private TipoAssento tipoAssento;
    private String status;

    public Assento(int id, TipoAssento tipoAssento, String status) {
        super(id);
        this.tipoAssento = tipoAssento;
        this.status = status;
    }

    // Método para ocupar o assento
    public void ocupar() {
        this.status = "Ocupado";
    }

    // Getters e setters
}
