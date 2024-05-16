package model;

public abstract class HolyTickets {
    protected int id;

    public HolyTickets(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

