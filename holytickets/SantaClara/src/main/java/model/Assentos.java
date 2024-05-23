package model;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Assentos {
    Scanner sc = new Scanner(System.in);
    protected String lugares[][] = new String[10][10];
    protected String data;
    protected String espetaculo;
    protected TiposAssento tipo;

    public void printLugares() {
        for (int i = 0; i < this.lugares.length; i++) {
            for (int j = 0; j < this.lugares.length; j++) {
                System.out.print(this.lugares[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Scanner getSc() {
        return sc;
    }

    public String[][] getLugares() {
        return lugares;
    }

    public String getData() {
        return data;
    }

    public String getEspetaculo() {
        return espetaculo;
    }

    public TiposAssento getTipoAssento() {
        return tipo;
    }

    public void imprimir() {
        System.out.println(espetaculo);
        System.out.println(data);
        System.out.println(tipo);
        this.printLugares();
        System.out.println();
        System.out.println("X -> ASSENTO INDISPONÍVEL");
    }

    ;

    public void reservar(String r, Connection connection) {
        Scanner scanner = new Scanner(System.in);
        try {
            for (int i = 0; i < lugares.length; i++) {
                for (int j = 0; j < lugares[i].length; j++) {
                    if (r.equalsIgnoreCase(lugares[i][j])) {
                        System.out.println("Deseja reservar " + lugares[i][j] + "? Sim(1) / Não (0)");
                        int confirma = scanner.nextInt();
                        if (confirma == 1) {
                            String sql = "UPDATE assentos SET status = ? WHERE lugar = ?";
                            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                                statement.setBoolean(1, true);
                                statement.setString(2, r);
                                int rowsAffected = statement.executeUpdate();
                                if (rowsAffected > 0) {
                                    System.out.println("Assento reservado com sucesso!");
                                    lugares[i][j] = "X ";
                                    printLugares();
                                } else {
                                    System.out.println("Falha ao reservar o assento.");
                                }
                            } catch (SQLException e) {
                                System.out.println("Erro ao reservar o assento: " + e.getMessage());
                            }
                        }
                        return;
                    }
                }
            }
            System.out.println("Assento indisponível, por favor escolha outro");
        } finally {
            scanner.close();
        }
    }
}
