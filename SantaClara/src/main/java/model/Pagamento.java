package model;

import java.math.BigDecimal;

public class Pagamento {
    BigDecimal valor;
    TipoIngressos tipoIngresso;

    public Pagamento(TipoIngressos t, BigDecimal v) {
        this.tipoIngresso = t;
        this.valor = v;
    }

    public Pagamento() {}

    public String getTipoIngresso() {
        return tipoIngresso.name().toLowerCase();
    }

    public BigDecimal getValor() {
        return valor;
    }
}
