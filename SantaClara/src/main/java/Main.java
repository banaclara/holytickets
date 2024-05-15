import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:sqlserver://DESKTOP-G0B3QMS\\BD:1433;databaseName=teatrosc;integratedSecurity=false;user=sa;password=12345678!;encrypt=false;";

    public static void main(String[] args) {
        try {
            System.setProperty("java.library.path", "C:\\Users\\breno\\IdeaProjects\\SantaClara\\lib\\mssql-jdbc_auth-12.6.1.x64.dll");

            System.out.println("Iniciando a conexão com o banco de dados...");
            System.out.println("String de conexão: " + URL);

            Connection connection = DriverManager.getConnection(URL);
            System.out.println("Conexão bem-sucedida!");

            EspetaculoRepositorio repositorio = new EspetaculoRepositorio(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Inserir espetáculo");
                System.out.println("2. Excluir espetáculo");
                System.out.println("3. Alterar espetáculo");
                System.out.println("4. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        OperacoesEspetaculo.inserirEspetaculo(scanner, repositorio);
                        break;
                    case 2:
                        OperacoesEspetaculo.excluirEspetaculo(scanner, repositorio);
                        break;
                    case 3:
                        OperacoesEspetaculo.alterarEspetaculo(scanner, repositorio);
                        break;
                    case 4:
                        System.out.println("Encerrando o programa...");
                        connection.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
