/*import model.Assentos;
import model.Espetaculo;
import operations.OperacoesProgramacao;
import repositories.EspetaculoRepositorio;
import operations.OperacoesEspetaculo;
import repositories.ProgramacaoRepositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/

import java.util.Scanner;

import model.AssentoCamarote;
import model.AssentoNormal;
import model.Assentos;

public class MainApplication {
    // Alterar o caminho da URL para o seu BD
    //private static final String URL = "jdbc:sqlserver://;serverName=localhost;databaseName=teatrosc\\BD:1433;databaseName=teatrosc;integratedSecurity=false;user=sa;password=Porra123;encrypt=false;";

    public static void main(String[] args) {
        /*try {
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
                        do {
                            menuEspetaculos(connection, scanner, espetaculoRepositorio);
                            System.out.println("0 - Voltar ao menu principal");
                            System.out.println("Qualquer outro número para continuar no menu de espetáculos.");
                            voltar = scanner.nextInt();
                        } while (voltar != 0);
                    case 2:
                        // inserir menu e métodos da venda de ingressos
                        break;
                    case 3:
                        do {
                            menuProgramacao(connection, scanner, programacaoRepositorio);
                            System.out.println("0 - Voltar ao menu principal");
                            System.out.println("Qualquer outro número para continuar no menu de programação.");
                            voltar = scanner.nextInt();
                        } while (voltar != 0);
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

    public static void menuEspetaculos(Connection connection, Scanner scanner, EspetaculoRepositorio repositorio) {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Inserir espetáculo");
        System.out.println("2. Excluir espetáculo");
        System.out.println("3. Alterar espetáculo");
        System.out.println("4. Listar espetáculos");
        System.out.println("5. Pesquisar espetáculo por nome");

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
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public static void menuProgramacao(Connection connection, Scanner scanner, ProgramacaoRepositorio repositorio) {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Inserir programação");
        System.out.println("2. Pesquisar programação");
        System.out.println("3. Adicionar data de exibição a um espetáculo");
        System.out.println("4. Remover programação");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                OperacoesProgramacao.inserirProg(scanner, repositorio);
                break;
            case 2:
                OperacoesProgramacao.pesquisarProgMensal(scanner, repositorio);
                break;
            case 3:
//                OperacoesEspetaculo.alterarEspetaculo(scanner, repositorio);
                break;
            case 4:
                OperacoesProgramacao.excluirProg(scanner, repositorio);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }*/
        Scanner sc = new Scanner(System.in);
        AssentoCamarote a = new AssentoCamarote();
        a.imprimir();
    }

}
