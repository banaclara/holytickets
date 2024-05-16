package model;

import model.HolyTickets;

import java.time.LocalDate;

public class Programacao extends HolyTickets {
    private int espetaculoID;
    private LocalDate dataExibicao;

    public Programacao(int id, int espetaculoID, LocalDate dataExibicao) {
        super(id);
        this.espetaculoID = espetaculoID;
        this.dataExibicao = dataExibicao;
    }


}
