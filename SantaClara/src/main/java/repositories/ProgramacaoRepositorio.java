package repositories;

import model.Espetaculo;
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

    @Override
    public void inserir(Programacao entidade) {
        String sql = "INSERT INTO Programacao (data_exibicao, espetaculo_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, entidade.getDataExibicao());
            statement.setInt(2, entidade.getEspetaculoID());
            statement.executeUpdate();
            System.out.println("O espetáculo foi cadastrado na data indicada.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar programação: " + e.getMessage());
        }
    }

    public void alterarEspetaculo(int id, int novoEspetaculo) {
        String sql = "UPDATE Programacao SET espetaculo_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(2, id);
            statement.setInt(1, novoEspetaculo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarData(int id, Date novaData) {
        String sql = "UPDATE Programacao SET data_exibicao = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(2, id);
            statement.setDate(1, novaData);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Programacao WHERE id = ?";
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

    @Override
    public List<Programacao> buscarTodos() {
        List<Programacao> programacao = new ArrayList<>();
        String sql = "SELECT Programacao.id, Espetaculos.titulo, Programacao.data_exibicao " +
                "FROM Espetaculos INNER JOIN Programacao ON Espetaculos.id = Programacao.espetaculo_id O" +
                "RDER BY Programacao.data_exibicao";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String titulo = resultSet.getString(2);
                Date dataExibicao = resultSet.getDate(3);
                Programacao prog = new Programacao(id, dataExibicao, titulo);
                programacao.add(prog);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar todas as programações: " + e.getMessage());
        }
        return programacao;
    }

    public List<Programacao> programacaoProximos30Dias() {
        List<Programacao> proximos30Dias = new ArrayList<>();
        String sql = "SELECT Programacao.id, Espetaculos.titulo, Programacao.data_exibicao " +
                "FROM Espetaculos INNER JOIN Programacao ON Espetaculos.id = Programacao.espetaculo_id " +
                "WHERE Programacao.data_exibicao BETWEEN CONVERT(date, GETDATE()) AND DATEADD(DAY, 30, GETDATE()) " +
                "ORDER BY Programacao.data_exibicao, Espetaculos.titulo";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String titulo = resultSet.getString(2);
                Date dataExibicao = resultSet.getDate(3);
                Programacao prog = new Programacao(id, dataExibicao, titulo);
                proximos30Dias.add(prog);
            }
        }  catch (SQLException e) {
            System.out.println("Erro ao buscar os programação dos próximos 30 dias: " + e.getMessage());
        }
        return proximos30Dias;
    }

    public List<Programacao> programacaoMensal(int mesDesejado, int anoDesejado) {
        List<Programacao> entradasNoMes = new ArrayList<>();
        String sql = "SELECT Programacao.id, Espetaculos.titulo, Programacao.data_exibicao " +
                "FROM Espetaculos INNER JOIN Programacao ON espetaculos.id = Programacao.espetaculo_id " +
                "WHERE MONTH(Programacao.data_exibicao) = ? AND YEAR(Programacao.data_exibicao) = ? " +
                "ORDER BY Programacao.data_exibicao, Espetaculos.titulo";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mesDesejado);
            statement.setInt(2, anoDesejado);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String titulo = resultSet.getString(2);
                Date dataExibicao = resultSet.getDate(3);
                Programacao prog = new Programacao(id, dataExibicao, titulo);
                entradasNoMes.add(prog);
            }
        }  catch (SQLException e) {
            System.out.println("Erro ao buscar os programação do mês: " + e.getMessage());
        }
        return entradasNoMes;
    }

}
