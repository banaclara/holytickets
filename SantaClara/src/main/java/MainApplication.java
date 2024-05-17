import repositories.EspetaculoRepositorio;
import operations.OperacoesEspetaculo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApplication {
    // Alterar o caminho da URL para o seu BD
    private static final String URL = "jdbc:sqlserver://DESKTOP-SEUPC\\NOMECONEXAO:1433;databaseName=teatrosc;integratedSecurity=false;user=sa;password=SENHA;encrypt=false;";

    public static void main(String[] args) {
        try {
            System.setProperty("java.library.path", "SantaClara/lib/mssql-jdbc_auth-12.6.1.x64.dll");

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
                System.out.println("4. Listar espetáculos");
                System.out.println("5. Pesquisar espetáculo por nome");
                System.out.println("6. Sair");

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
                        OperacoesEspetaculo.listarEspetaculos(repositorio);
                        break;
                    case 5:
                        OperacoesEspetaculo.pesquisarEspetaculoPorNome(scanner, repositorio);
                        break;
                    case 6:
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
