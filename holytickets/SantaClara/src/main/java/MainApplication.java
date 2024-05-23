import operations.OperacoesAssentos;
import operations.OperacoesProgramacao;
import repositories.EspetaculoRepositorio;
import repositories.ProgramacaoRepositorio;
import operations.OperacoesEspetaculo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.AssentoNormal;
import model.AssentoCamarote;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApplication {
    // Alterar o caminho da URL para o seu BD
    private static final String URL = "jdbc:sqlserver://;serverName=localhost;databaseName=teatrosc\\BD:1433;databaseName=teatrosc;integratedSecurity=false;user=sa;password=12345678!;encrypt=false;";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        try {
            System.setProperty("java.library.path", "SantaClara/lib/mssql-jdbc_auth-12.6.1.x64.dll");

            System.out.println("Iniciando a conexão com o banco de dados...");
            System.out.println("String de conexão: " + URL);

            connection = DriverManager.getConnection(URL);
            System.out.println("Conexão bem-sucedida!");

            EspetaculoRepositorio espetaculoRepositorio = new EspetaculoRepositorio(connection);
            ProgramacaoRepositorio programacaoRepositorio = new ProgramacaoRepositorio(connection);

            while (true) {
                menuPrincipal();
                int opt = lerInteiro(scanner);

                switch (opt) {
                    case 1:
                        menuEspetaculos(connection, scanner, espetaculoRepositorio);
                        break;
                    case 2:
                        menuVendaIngressos(scanner, connection);
                        break;
                    case 3:
                        menuProgramacao(connection, scanner, programacaoRepositorio);
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
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }

    public static void menuPrincipal() {
        System.out.println("1 - Espetáculos");
        System.out.println("2 - Venda de ingressos");
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

        int opcao = lerInteiro(scanner);

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

    public static void menuVendaIngressos(Scanner scanner, Connection connection) {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Visualizar Assentos");
        System.out.println("2. Reservar Assentos");

        int opcao = lerInteiro(scanner);

        switch (opcao) {
            case 1:
                System.out.println("Escolha o tipo de assento:");
                System.out.println("1. Assentos Normais");
                System.out.println("2. Assentos de Camarote");

                int tipoAssento = lerInteiro(scanner);

                switch (tipoAssento) {
                    case 1:
                        AssentoNormal assentoNormal = new AssentoNormal();
                        String[][] lugaresNormais = assentoNormal.getLugares();
                        OperacoesAssentos.visualizarAssentos(lugaresNormais);
                        break;
                    case 2:
                        AssentoCamarote assentoCamarote = new AssentoCamarote();
                        String[][] lugaresCamarote = assentoCamarote.getLugares();
                        OperacoesAssentos.visualizarAssentos(lugaresCamarote);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
                break;
            case 2:
                System.out.println("Escolha o tipo de assento:");
                System.out.println("1. Assentos Normais");
                System.out.println("2. Assentos de Camarote");

                int tipoAssentoReserva = lerInteiro(scanner);
                OperacoesAssentos operacoesAssentos = new OperacoesAssentos();

                switch (tipoAssentoReserva) {
                    case 1:
                        AssentoNormal assentoNormalReserva = new AssentoNormal();
                        String[][] lugaresNormaisReserva = assentoNormalReserva.getLugares();
                        OperacoesAssentos.visualizarAssentos(lugaresNormaisReserva);

                        System.out.println("Digite o assento que deseja reservar:");
                        String assentoNormalEscolhido = scanner.nextLine();
                        operacoesAssentos.reservarAssento(assentoNormalEscolhido, connection, assentoNormalReserva);
                        break;
                    case 2:
                        AssentoCamarote assentoCamaroteReserva = new AssentoCamarote();
                        String[][] lugaresCamaroteReserva = assentoCamaroteReserva.getLugares();
                        OperacoesAssentos.visualizarAssentos(lugaresCamaroteReserva);

                        System.out.println("Digite o assento que deseja reservar:");
                        String assentoCamaroteEscolhido = scanner.nextLine();
                        operacoesAssentos.reservarAssento(assentoCamaroteEscolhido, connection, assentoCamaroteReserva);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
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

        int opcao = lerInteiro(scanner);

        switch (opcao) {
            case 1:
                OperacoesProgramacao.inserirProg(scanner, repositorio);
                break;
            case 2:
                OperacoesProgramacao.pesquisarProgMensal(scanner, repositorio);
                break;
            case 3:
                // OperacoesEspetaculo.alterarEspetaculo(scanner, repositorio);
                break;
            case 4:
                OperacoesProgramacao.excluirProg(scanner, repositorio);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static int lerInteiro(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int numero = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer de nova linha
                return numero;
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.nextLine(); // Limpa o buffer antes de tentar ler novamente
            }
        }
    }


}
