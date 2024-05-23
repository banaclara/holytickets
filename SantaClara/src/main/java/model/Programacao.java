package model;

import java.sql.Date;

public class Programacao extends HolyTickets {
    private int espetaculoID;
    private Date dataExibicao;
    String tituloEspetaculo;

    public Programacao(int id, Date dataExibicao, int espetaculoID) {
        super(id);
        this.dataExibicao = dataExibicao;
        this.espetaculoID = espetaculoID;
    }

    public Programacao(Date dataExibicao, String titulo) {
        super();
        this.dataExibicao = dataExibicao;
        this.tituloEspetaculo = titulo;
    }

    public int getEspetaculoID() {
        return espetaculoID;
    }

    public Date getDataExibicao() {
        return dataExibicao;
    }

    public String getTituloEspetaculo() {
        return tituloEspetaculo;
    }
}
