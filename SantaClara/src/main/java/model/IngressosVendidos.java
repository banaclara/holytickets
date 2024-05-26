package model;

import java.math.BigDecimal;
import java.sql.Date;

public class IngressosVendidos extends Pagamento {
    Date dataExibicao;
    String assentoId;

    public IngressosVendidos(Pagamento p, Date d, String a) {
        super(p.tipoIngresso, p.valor);
        this.dataExibicao = d;
        this.assentoId = a;
    }

    public IngressosVendidos() {}

    public Date getDataExibicao() {
        return dataExibicao;
    }

    public String getAssentoId() {
        return assentoId;
    }


}
