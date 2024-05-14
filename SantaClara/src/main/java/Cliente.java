import java.time.LocalDate;

public class Cliente extends SantaClara {
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;

    public Cliente(int id, String nomeCompleto, String cpf, LocalDate dataNascimento, String email) { // Use LocalDate em vez de Date
        super(id);
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }


}
