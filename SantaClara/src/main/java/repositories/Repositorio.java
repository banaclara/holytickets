package repositories;

import model.HolyTickets;

import java.util.List;
import java.time.LocalDate;

public interface Repositorio<Table extends HolyTickets> {
    //Insere uma nova entidade e adiciona um novo registro no BD
    void inserir(Table entidade);
    //modifica uma entidade e atualiza no BD
    void atualizar(Table entidade);
    //Exclui uma entidade no BD
    void excluir(int id);
    //Busca uma entidade por ID no BD
    Table buscarPorId(int id);
    //Busca todas as entidades do BD e retorna uma lista
    List<Table> buscarTodos();
    //Busca uma entidade por Data no BD
    List<Table> buscarPorDataExibicao(LocalDate dataExibicao);
}
