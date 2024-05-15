public class Compra extends HolyTickets {
    private Ingresso ingresso;
    private Assento assento;

    public Compra(int id, Ingresso ingresso, Assento assento) {
        super(id);
        this.ingresso = ingresso;
        this.assento = assento;
    }

    public void realizarCompra() {

        ingresso.vender();

        assento.ocupar();

    }
}
