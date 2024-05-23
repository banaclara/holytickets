package repositories;

import model.Espetaculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EspetaculoRepositorio implements Repositorio<Espetaculo> {

    private Connection connection;

    //Inicializador do repositorio com conexão ao BD
    public EspetaculoRepositorio(Connection connection) {
        this.connection = connection;
    }
    //Método de inserir que vamos usar nas Operações
    @Override
    public void inserir(Espetaculo entidade) {
        //INSERT dos valores
        String sql = "INSERT INTO espetaculos (titulo, diretor, elenco, descricao) VALUES (?, ?, ?, ?)";
        //A PreparedStatement define os valores que serão INSERT
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            //setString converte o valor definido para VARCHAR ou CHAR no BD
            statement.setString(1, entidade.getTitulo());
            statement.setString(2, entidade.getDiretor());
            statement.setString(3, entidade.getElenco());
            statement.setString(4, entidade.getDescricao());
            //executeUpdate atualiza a tabela com os valores definidos;
            statement.executeUpdate();
            System.out.println("Espetáculo inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o espetáculo: " + e.getMessage());
        }
    }
    //Método de excluir que vamos usar nas Operações
    @Override
    public void excluir(int id) {
        //DELETE executa a instrução de exclusão
        String sql = "DELETE FROM espetaculos WHERE id = ?";
        //Definimos o ID no BD para a exclusão
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            //setInt converte o valor definido para INTEGER no BD
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Espetáculo excluído com sucesso!");
            } else {
                System.out.println("Espetáculo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o espetáculo: " + e.getMessage());
        }
    }

    //Busca por nome que vamos usar nas Operações (a definir)
    public List<Espetaculo> buscarPorNome(String nome) {
        List<Espetaculo> espetaculos = new ArrayList<>();
        // SELECT executa a busca no BD após a definição do nome
        String sql = "SELECT * FROM espetaculos WHERE titulo LIKE ?";
        // Define o nome que será procurado
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nome + "%");
            //ResultSet processa a busca
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Espetaculo espetaculo = new Espetaculo(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("diretor"),
                        resultSet.getString("elenco"),
                        resultSet.getString("descricao")
                );
                espetaculos.add(espetaculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os espetáculos por nome: " + e.getMessage());
        }
        return espetaculos;
    }
    // Método que utilizaremos para listar os espetaculos
    @Override
    public List<Espetaculo> buscarTodos() {
        List<Espetaculo> espetaculos = new ArrayList<>();
        String sql = "SELECT * FROM espetaculos";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Espetaculo espetaculo = new Espetaculo(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("diretor"),
                        resultSet.getString("elenco"),
                        resultSet.getString("descricao")
                );
                espetaculos.add(espetaculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar todos os espetáculos: " + e.getMessage());
        }
        return espetaculos;
    }
    // Método teste para pesquisar data (a definir)

    //Define um novo titulo no BD
    public void alterarTitulo(int id, String novoTitulo) {
        String sql = "UPDATE espetaculos SET titulo = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoTitulo);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Define um novo diretor no BD
    public void alterarDiretor(int id, String novoDiretor) {
        String sql = "UPDATE espetaculos SET diretor = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoDiretor);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Define um novo elenco no BD
    public void alterarElenco(int id, String novoElenco) {
        String sql = "UPDATE espetaculos SET elenco = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoElenco);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Define uma nova descrição no BD
    public void alterarDescricao(int id, String novaDescricao) {
        String sql = "UPDATE espetaculos SET descricao = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novaDescricao);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
