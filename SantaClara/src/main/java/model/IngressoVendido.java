package model;

import java.sql.Date;

public class IngressoVendido extends Pagamento {
    Date dataExibicao;
    String assentoId;

    public IngressoVendido(Pagamento p, Date d, String a) {
        super(p.tipoIngresso, p.valor);
        this.dataExibicao = d;
        this.assentoId = a;
    }

    public Date getDataExibicao() {
        return dataExibicao;
    }

    public String getAssentoId() {
        return assentoId;
    }


}
