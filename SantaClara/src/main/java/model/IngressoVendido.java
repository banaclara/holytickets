package model;

import java.sql.Date;

public class IngressoVendido extends Pagamento {
    String tituloEspetaculo;
    Date dataExibicao;
    String assentoId;

    public IngressoVendido(Pagamento p, Date d, String a) {
        super(p.tipoIngresso, p.valor);
        this.dataExibicao = d;
        this.assentoId = a;
    }

    public IngressoVendido(String t, Date d, String a) {
        this.dataExibicao = d;
        this.assentoId = a;
        this.tituloEspetaculo = t;
    }

    public Date getDataExibicao() {
        return dataExibicao;
    }

    public String getAssentoId() {
        return assentoId;
    }

    public String getTituloEspetaculo() { return tituloEspetaculo; }

}
