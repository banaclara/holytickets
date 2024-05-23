package model;

public class IngressoVendido {
    String data;
    String espetaculo;
    TiposIngressos tipoIngresso;
    String valor;
    String assento;

    public IngressoVendido(String d, String e, TiposIngressos t, String v, String a) {
        this.data = d;
        this.espetaculo = e;
        this.valor = v;
        this.tipoIngresso = t;
        this.assento = a;
    }

    public void imprimirIngresso() {
        System.out.println(espetaculo);
        System.out.println(data);
        System.out.println(tipoIngresso + " R$" + valor);
        System.out.println("Assento: " + assento);
    }
    //adicionar método pra subir esass informações e criar uma linha na tabela de vendas, provavelmente deve ir em outra pasta
}
