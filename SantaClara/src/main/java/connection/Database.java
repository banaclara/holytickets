package connection;

import repositories.EspetaculoRepositorio;
import repositories.IngressosRepositorio;
import repositories.ProgramacaoRepositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // Alterar o caminho da URL para o seu BD
    private static final String URL = "jdbc:sqlserver://DESKTOP-***\\***:1433;databaseName=teatroStaClara;integratedSecurity=false;user=sa;password=***;encrypt=false;";

    private EspetaculoRepositorio espetaculo;
    private ProgramacaoRepositorio programacao;
    private IngressosRepositorio ingressos;

    private Connection connection;

    public Database() throws SQLException {
        System.setProperty("java.library.path", "SantaClara/lib/mssql-jdbc_auth-12.6.1.x64.dll");

        System.out.println("Iniciando a conexão com o banco de dados...");

        connection = DriverManager.getConnection(URL);
        System.out.println("Conexão bem-sucedida!");

        espetaculo = new EspetaculoRepositorio(connection);
        programacao = new ProgramacaoRepositorio(connection);
        ingressos = new IngressosRepositorio(connection);

    }

    public EspetaculoRepositorio getEspetaculo() {
        return espetaculo;
    }

    public IngressosRepositorio getIngressos() {
        return ingressos;
    }

    public ProgramacaoRepositorio getProgramacao() {
        return programacao;
    }

    public Connection getConnection() {
        return connection;
    }
}
