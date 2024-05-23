package repositories;

import model.HolyTickets;

import java.util.List;
import java.time.LocalDate;

public interface Repositorio<T extends HolyTickets> {
    //Insere uma nova entidade e adiciona um novo registro no BD
    void inserir(T entidade);
    //Exclui uma entidade no BD
    void excluir(int id);
    //Busca todas as entidades do BD e retorna uma lista
    List<T> buscarTodos();
}
