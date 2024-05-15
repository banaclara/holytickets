import java.util.List;
import java.time.LocalDate;

public interface Repositorio<T extends HolyTickets> {
    void inserir(T entidade);
    void atualizar(T entidade);
    void excluir(int id);
    T buscarPorId(int id);
    List<T> buscarTodos();
    List<T> buscarPorDataExibicao(LocalDate dataExibicao);
}
