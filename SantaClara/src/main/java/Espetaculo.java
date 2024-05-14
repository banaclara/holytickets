public class Espetaculo extends SantaClara {
    private String nomeEspetaculo;
    private String genero;
    private int duracao;
    private String diretor;
    private String elenco;
    private String descricao;

    public Espetaculo(int id, String nomeEspetaculo, String genero, int duracao, String diretor, String elenco, String descricao) {
        super(id);
        this.nomeEspetaculo = nomeEspetaculo;
        this.genero = genero;
        this.duracao = duracao;
        this.diretor = diretor;
        this.elenco = elenco;
        this.descricao = descricao;
    }

}
