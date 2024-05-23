package model;

import java.sql.Date;

public class Programacao extends HolyTickets {
    private int espetaculoID;
    private Date dataExibicao;

    public Programacao(int id, Date dataExibicao, int espetaculoID) {
        super(id);
        this.dataExibicao = dataExibicao;
        this.espetaculoID = espetaculoID;
    }

    public int getEspetaculoID() {
        return espetaculoID;
    }

    public Date getDataExibicao() {
        return dataExibicao;
    }
}
