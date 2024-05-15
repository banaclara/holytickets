public class Espetaculo extends HolyTickets {
    private String titulo;
    private String diretor;
    private String elenco;
    private String descricao;

    public Espetaculo(int id, String titulo, String diretor, String elenco, String descricao) {
        super(id);
        this.titulo = titulo;
        this.diretor = diretor;
        this.elenco = elenco;
        this.descricao = descricao;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public String getElenco() {
        return elenco;
    }

    public String getDescricao() {
        return descricao;
    }


}
