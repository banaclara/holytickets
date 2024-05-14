import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    // Configurações do banco de dados
    private static final String URL = "jdbc:sqlserver://DESKTOP-G0B3QMS\\BD:1433;databaseName=SantaClara1_0;integratedSecurity=false;user=sa;password=SQLSERVER!;encrypt=false;";

    public static void main(String[] args) {
        try {
            // Configurar o caminho do sqljdbc_auth.dll
            System.setProperty("java.library.path", "C:\\Users\\breno\\IdeaProjects\\SantaClara\\lib\\mssql-jdbc_auth-12.6.1.x64.dll");

            System.out.println("Iniciando a conexão com o banco de dados...");
            //Teste
            System.out.println("String de conexão: " + URL);

            // Conectar ao banco de dados
            Connection connection = DriverManager.getConnection(URL);
            System.out.println("Conexão bem-sucedida!");

            System.out.println("Criando instâncias necessárias...");

            // Criação de instâncias das classes necessárias(teste)
            Cliente cliente = new Cliente(1, "Fulano de Tal", "12345678900", LocalDate.now(), "fulano@example.com");
            Vendas venda = new Vendas(1, LocalDate.now(), 100.0, "Cartão de Crédito", 1);
            Ingresso ingresso = new Ingresso(1, venda.getId(), "CG123", TipoIngresso.INTEIRO, LocalDate.now(), "Disponível");
            Assento assento = new Assento(1, 'A', 1, TipoAssento.PLATEIA, "Livre", 1);
            Compra compra = new Compra(ingresso, assento);

            System.out.println("Realizando a compra...");

            // Realiza a compra
            compra.realizarCompra();

            System.out.println("Compra realizada com sucesso!");

            // Fechamento da conexão
            connection.close();
            System.out.println("Conexão encerrada.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
