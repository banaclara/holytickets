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

    public void alterarEspetaculo(int id, int novoEspetaculo) {
        String sql = "UPDATE programacao SET espetaculo_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(2, id);
            statement.setInt(1, novoEspetaculo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarData(int id, Date novaData) {
        String sql = "UPDATE programacao SET data_exibicao = ? WHERE id = ?";
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

    @Override
    public List<Programacao> buscarTodos() {
        List<Programacao> programacao = new ArrayList<>();
        String sql = "SELECT programacao.id, espetaculos.titulo, programacao.data_exibicao FROM espetaculos INNER JOIN programacao ON espetaculos.id = programacao.espetaculo_id ORDER BY programacao.data_exibicao";
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

    public List<Programacao> programacaoMensal(int mesDesejado, int anoDesejado) {
        List<Programacao> entradasNoMes = new ArrayList<>();
        String sql = "SELECT espetaculos.titulo, programacao.data_exibicao FROM espetaculos INNER JOIN programacao ON espetaculos.id = programacao.espetaculo_id WHERE MONTH(programacao.data_exibicao) = ? AND YEAR(programacao.data_exibicao) = ? ORDER BY espetaculos.titulo, programacao.data_exibicao";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mesDesejado);
            statement.setInt(2, anoDesejado);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String titulo = resultSet.getString(1); // começa no 1
                Date dataExibicao = resultSet.getDate(2);
                Programacao programacao = new Programacao(dataExibicao, titulo); // valores
                entradasNoMes.add(programacao);
            }
        }  catch (SQLException e) {
                System.out.println("Erro ao buscar os programação do mês: " + e.getMessage());
            }
        return entradasNoMes;
    }
}
