package repositories;

import model.Programacao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramacaoRepositorio implements Repositorio<Programacao>{
    private Connection connection;

    public ProgramacaoRepositorio(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Programacao entidade) {
        String sql = "INSERT INTO programacao (data_exibicao, espetaculo_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, entidade.getDataExibicao());
            statement.setInt(2, entidade.getEspetaculoID());
            statement.executeUpdate();
            System.out.println("Programação atualizada! O espetáculo foi cadastrado na data indicada.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar programação: " + e.getMessage());
        }
    }

    public void atualizar(Programacao entidade) {}

    public void excluir(int id) {
        String sql = "DELETE FROM programacao WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Programação removida.");
            } else {
                System.out.println("Programação não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir a programação: " + e.getMessage());
        }
    }
    public Programacao buscarPorId(int id) {
        return null;
    }
    public List<Programacao> buscarTodos() {
        return null;
    }
    public List<Programacao> buscarPorDataExibicao(LocalDate dataExibicao) {
        return null;
    }

    public List<Programacao> programacaoMensal(int mesDesejado, int anoDesejado) {
        List<Programacao> entradasNoMes = new ArrayList<>();
        String sql = "SELECT espetaculos.titulo, programacao.data_exibicao FROM espetaculos INNER JOIN programacao ON espetaculos.id = programacao.espetaculo_id WHERE MONTH(programacao.data_exibicao) = ? AND YEAR(programacao.data_exibicao) = ? ORDER BY espetaculos.titulo, programacao.data_exibicao";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mesDesejado);
            statement.setInt(2, anoDesejado);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String titulo = resultSet.getString(1); // Indicando o número da coluna, começando por 1
                Date dataExibicao = resultSet.getDate(2); // Indicando o número da coluna, começando por 1

                // Criar um novo objeto Programacao com os valores obtidos
                Programacao programacao = new Programacao(dataExibicao, titulo);
                entradasNoMes.add(programacao);
            }
        }  catch (SQLException e) {
                System.out.println("Erro ao buscar os programação do mês: " + e.getMessage());
            }
        return entradasNoMes;
    }
}
