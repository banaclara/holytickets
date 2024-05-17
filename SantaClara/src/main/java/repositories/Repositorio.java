package repositories;

import model.HolyTickets;

import java.util.List;
import java.time.LocalDate;

public interface Repositorio<T extends HolyTickets> {
    //Insere uma nova entidade e adiciona um novo registro no BD
    void inserir(T entidade);
    //modifica uma entidade e atualiza no BD
    void atualizar(T entidade);
    //Exclui uma entidade no BD
    void excluir(int id);
    //Busca uma entidade por ID no BD
    T buscarPorId(int id);
    //Busca todas as entidades do BD e retorna uma lista
    List<T> buscarTodos();
    //Busca uma entidade por Data no BD
    List<T> buscarPorDataExibicao(LocalDate dataExibicao);
}
