package operations;
import model.Assentos;
import model.TiposAssento;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class OperacoesAssentos {
    private Scanner scanner;

    public OperacoesAssentos() {
        scanner = new Scanner(System.in);
    }

    public static void imprimir(String espetaculo, String data, TiposAssento tipo, String[][] lugares) {
        System.out.println(espetaculo);
        System.out.println(data);
        System.out.println(tipo);
        System.out.println();
        printLugares(lugares);
        System.out.println();
        System.out.println("X -> ASSENTO INDISPONÍVEL");
    }

    public static void visualizarAssentos(String[][] lugares) {
        printLugares(lugares);
    }

    private static void printLugares(String[][] lugares) {
        for (String[] linha : lugares) {
            for (String lugar : linha) {
                System.out.print(lugar + " ");
            }
            System.out.println();
        }
    }

    public void reservarAssento(String lugar, Connection connection, Assentos assentos) {
        try {
            // Verifica se o assento já existe na tabela
            if (!assentoExiste(connection, lugar)) {
                // Se não existir, insere o assento na tabela
                inserirAssento(connection, lugar, "Tipo do assento");
            }

            // Atualiza o status do assento
            atualizarStatusAssento(connection, lugar, assentos);

            // Restante do código...
        } catch (SQLException e) {
            System.out.println("Erro ao reservar o assento: " + e.getMessage());
        }
    }

    private boolean assentoExiste(Connection connection, String assento) throws SQLException {
        String sql = "SELECT COUNT(*) FROM assentos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, assento);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    private void inserirAssento(Connection connection, String assento, String tipoAssento) throws SQLException {
        String sql = "INSERT INTO assentos (id, tipo_assento, status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, assento);
            statement.setString(2, tipoAssento); // Utiliza o tipo de assento passado como parâmetro
            statement.setString(3, "Disponível"); // Define o status como disponível ao inserir
            statement.executeUpdate();
        }
    }

    private void atualizarStatusAssento(Connection connection, String assento, Assentos assentos) throws SQLException {
        String sql = "UPDATE assentos SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, true); // Define o status como reservado
            statement.setString(2, assento);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Assento reservado com sucesso!");
                // Atualiza o estado do assento na matriz de assentos
                atualizarEstadoAssento(assento, assentos);
            } else {
                System.out.println("Falha ao reservar o assento.");
            }
        }
    }

    private void atualizarEstadoAssento(String assento, Assentos assentos) {
        String[][] lugares = assentos.getLugares();
        for (int i = 0; i < lugares.length; i++) {
            for (int j = 0; j < lugares[i].length; j++) {
                if (assento.equals(lugares[i][j])) {
                    lugares[i][j] = "X "; // Atualiza o estado do assento para reservado
                    return;
                }
            }
        }
    }
}