import java.time.LocalDateTime; // Importe LocalDateTime, não Date

public class Programacao extends SantaClara {
    private int espetaculoID;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private int bilheteriaID;

    public Programacao(int id, int espetaculoID, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, int bilheteriaID) { // Use LocalDateTime em vez de Date
        super(id);
        this.espetaculoID = espetaculoID;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.bilheteriaID = bilheteriaID;
    }

}
