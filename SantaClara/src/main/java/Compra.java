public class Compra {
    private Ingresso ingresso;
    private Assento assento;

    public Compra(Ingresso ingresso, Assento assento) {
        this.ingresso = ingresso;
        this.assento = assento;
    }

    public void realizarCompra() {

        ingresso.vender();

        assento.ocupar();

    }
}
