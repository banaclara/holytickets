import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EspetaculoRepositorio implements Repositorio<Espetaculo> {

    private Connection connection;

    public EspetaculoRepositorio(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Espetaculo entidade) {
        String sql = "INSERT INTO espetaculos (titulo, diretor, elenco, descricao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entidade.getTitulo());
            statement.setString(2, entidade.getDiretor());
            statement.setString(3, entidade.getElenco());
            statement.setString(4, entidade.getDescricao());
            statement.executeUpdate();
            System.out.println("Espetáculo inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o espetáculo: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM espetaculos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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

    @Override
    public void atualizar(Espetaculo entidade) {
        String sql = "UPDATE espetaculos SET titulo = ?, diretor = ?, elenco = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entidade.getTitulo());
            statement.setString(2, entidade.getDiretor());
            statement.setString(3, entidade.getElenco());
            statement.setString(4, entidade.getDescricao());
            statement.setInt(5, entidade.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Espetáculo atualizado com sucesso!");
            } else {
                System.out.println("Espetáculo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o espetáculo: " + e.getMessage());
        }
    }

    @Override
    public Espetaculo buscarPorId(int id) {
        String sql = "SELECT * FROM espetaculos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Espetaculo(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("diretor"),
                        resultSet.getString("elenco"),
                        resultSet.getString("descricao")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o espetáculo por ID: " + e.getMessage());
        }
        return null;
    }

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

    public List<Espetaculo> buscarPorDataExibicao(LocalDate dataExibicao) {
        List<Espetaculo> espetaculos = new ArrayList<>();
        String sql = "SELECT * FROM espetaculos WHERE data_exibicao = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(dataExibicao));
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
            System.out.println("Erro ao buscar os espetáculos por data de exibição: " + e.getMessage());
        }
        return espetaculos;
    }
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
