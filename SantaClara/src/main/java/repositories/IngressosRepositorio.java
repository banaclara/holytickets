package repositories;

import model.IngressosVendidos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngressosRepositorio {
    private Connection connection;

    public IngressosRepositorio(Connection connection) {
        this.connection = connection;
    }

    public void venderIngresso(IngressosVendidos entidade) {
        String sql = "INSERT INTO IngressosVendidos (data_exibicao, assento_id, tipo_ingresso, valor_pago) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, entidade.getDataExibicao());
            statement.setString(2, entidade.getAssentoId());
            statement.setString(3, entidade.getTipoIngresso());
            statement.setBigDecimal(4, entidade.getValor());
            statement.executeUpdate();
            System.out.println("Ingresso vendido! O assento " + entidade.getAssentoId() + " já está reservado para o dia " + entidade.getDataExibicao() + ".");
        } catch (SQLException e) {
            System.out.println("Erro na venda do ingresso: " + e.getMessage());
        }
    }

    public void cancelarIngresso(String assento, Date dataExibicao) {
        String sql = "DELETE FROM IngressosVendidos WHERE assento_id = ? AND data_exibicao = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, assento);
            statement.setDate(2, dataExibicao);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("A reserva do assento " + assento + " foi cancelada.");
            } else {
                System.out.println("Ingresso não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cancelar compra: " + e.getMessage());
        }
    }
}
