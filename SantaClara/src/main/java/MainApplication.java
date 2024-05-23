import operations.OperacoesProgramacao;
import repositories.EspetaculoRepositorio;
import operations.OperacoesEspetaculo;
import repositories.ProgramacaoRepositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApplication {
    // Alterar o caminho da URL para o seu BD
    private static final String URL = "jdbc:sqlserver://DESKTOP-PC\\BD:1433;databaseName=teatrosc;integratedSecurity=false;user=sa;password=###;encrypt=false;";

    public static void main(String[] args) {
        try {
            System.setProperty("java.library.path", "SantaClara/lib/mssql-jdbc_auth-12.6.1.x64.dll");

            System.out.println("Iniciando a conexão com o banco de dados...");
            System.out.println("String de conexão: " + URL);

            Connection connection = DriverManager.getConnection(URL);
            System.out.println("Conexão bem-sucedida!");

            EspetaculoRepositorio espetaculoRepositorio = new EspetaculoRepositorio(connection);
            ProgramacaoRepositorio programacaoRepositorio = new ProgramacaoRepositorio(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                menuPrincipal();
                int opt, voltar;
                opt = scanner.nextInt();

                switch (opt) {
                    case 1:
                        menuEspetaculos(scanner, espetaculoRepositorio);
                    case 2:
                        // inserir menu e métodos da venda de ingressos
                        break;
                    case 3:
                        menuProgramacao(scanner, programacaoRepositorio, espetaculoRepositorio);
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

    public static void menuPrincipal() {
        System.out.println("1 - Espetáculos");
        System.out.println("2 - Venda de ingresssos");
        System.out.println("3 - Programação mensal");
        System.out.println("4 - Sair do programa");
    }

    public static void menuEspetaculos(Scanner scanner, EspetaculoRepositorio repositorio) {
        boolean sair = false;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Inserir espetáculo");
            System.out.println("2. Excluir espetáculo");
            System.out.println("3. Alterar espetáculo");
            System.out.println("4. Listar espetáculos");
            System.out.println("5. Pesquisar espetáculo por nome");
            System.out.println("0. Voltar ao menu principal");

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
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (sair == false);
    }

    public static void menuProgramacao(Scanner scanner, ProgramacaoRepositorio pRepositorio, EspetaculoRepositorio eRepositorio) {
        boolean sair = false;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar data de exibição a um espetáculo");
            System.out.println("2. Pesquisar programação mensal");
            System.out.println("3. Realizar alterações nas programações");
            System.out.println("4. Cancelar data de exibição");
            System.out.println("0. Voltar ao menu principal");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    OperacoesProgramacao.inserirProgramacao(scanner, pRepositorio, eRepositorio);
                    break;
                case 2:
                    OperacoesProgramacao.pesquisarProgramacaoMensal(scanner, pRepositorio);
                    break;
                case 3:
                    OperacoesProgramacao.alterarProgramacao(scanner, pRepositorio, eRepositorio);
                    break;
                case 4:
                    OperacoesProgramacao.excluirProgramacao(scanner, pRepositorio);
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (sair == false);
    }
}
