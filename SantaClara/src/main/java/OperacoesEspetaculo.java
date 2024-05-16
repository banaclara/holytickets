import java.util.List;
import java.util.Scanner;

public class OperacoesEspetaculo {
    public static void inserirEspetaculo(Scanner scanner, EspetaculoRepositorio repositorio) {
        System.out.println("Inserir novo espetáculo:");

        System.out.println("Título:");
        String titulo = scanner.nextLine();

        System.out.println("Diretor:");
        String diretor = scanner.nextLine();

        System.out.println("Elenco:");
        String elenco = scanner.nextLine();

        System.out.println("Descrição:");
        String descricao = scanner.nextLine();

        Espetaculo novoEspetaculo = new Espetaculo(0, titulo, diretor, elenco, descricao);

        repositorio.inserir(novoEspetaculo);

        System.out.println("Espetáculo inserido com sucesso!");
    }

    public static void excluirEspetaculo(Scanner scanner, EspetaculoRepositorio repositorio) {
        System.out.println("Excluir espetáculo:");

        List<Espetaculo> todosEspetaculos = repositorio.buscarTodos();
        System.out.println("Espetáculos disponíveis para exclusão:");
        for (Espetaculo espetaculo : todosEspetaculos) {
            System.out.println("ID: " + espetaculo.getId() + ", Título: " + espetaculo.getTitulo());
        }

        System.out.println("Digite o ID do espetáculo que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Excluir do banco de dados
        repositorio.excluir(id);

        System.out.println("Espetáculo excluído com sucesso!");
    }

    public static void alterarEspetaculo(Scanner scanner, EspetaculoRepositorio repositorio) {
        System.out.println("Alterar espetáculo:");

        // Listar todos os espetáculos
        List<Espetaculo> todosEspetaculos = repositorio.buscarTodos();
        System.out.println("Espetáculos disponíveis para alteração:");
        for (Espetaculo espetaculo : todosEspetaculos) {
            System.out.println("ID: " + espetaculo.getId() + ", Título: " + espetaculo.getTitulo());
        }

        System.out.println("Digite o ID do espetáculo que deseja alterar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Selecione o campo que deseja alterar:");
        System.out.println("1. Título");
        System.out.println("2. Diretor");
        System.out.println("3. Elenco");
        System.out.println("4. Descrição");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        String novoValor = "";
        switch (opcao) {
            case 1:
                System.out.println("Novo título:");
                novoValor = scanner.nextLine();
                repositorio.alterarTitulo(id, novoValor);
                break;
            case 2:
                System.out.println("Novo diretor:");
                novoValor = scanner.nextLine();
                repositorio.alterarDiretor(id, novoValor);
                break;
            case 3:
                System.out.println("Novo elenco:");
                novoValor = scanner.nextLine();
                repositorio.alterarElenco(id, novoValor);
                break;
            case 4:
                System.out.println("Nova descrição:");
                novoValor = scanner.nextLine();
                repositorio.alterarDescricao(id, novoValor);
                break;
            default:
                System.out.println("Opção inválida. Nenhuma alteração realizada.");
                return;
        }

        System.out.println("Espetáculo alterado com sucesso!");
    }
    public static void listarEspetaculos(EspetaculoRepositorio repositorio) {
        System.out.println("Lista de Espetáculos:");

        List<Espetaculo> todosEspetaculos = repositorio.buscarTodos();
        if (todosEspetaculos.isEmpty()) {
            System.out.println("Não há espetáculos cadastrados.");
        } else {
            for (Espetaculo espetaculo : todosEspetaculos) {
                System.out.println("ID: " + espetaculo.getId() + ", Título: " + espetaculo.getTitulo());
            }
        }
    }
    public static void pesquisarEspetaculoPorNome(Scanner scanner, EspetaculoRepositorio repositorio) {
        System.out.println("Pesquisar espetáculo por nome:");
        System.out.println("Digite o nome do espetáculo:");
        String nome = scanner.nextLine();

        List<Espetaculo> espetaculosEncontrados = repositorio.buscarPorNome(nome);

        if (espetaculosEncontrados.isEmpty()) {
            System.out.println("Nenhum espetáculo encontrado com o nome informado.");
        } else {
            System.out.println("Espetáculo(s) encontrado(s):");
            for (Espetaculo espetaculo : espetaculosEncontrados) {
                System.out.println("ID: " + espetaculo.getId());
                System.out.println("Título: " + espetaculo.getTitulo());
                System.out.println("Diretor: " + espetaculo.getDiretor());
                System.out.println("Elenco: " + espetaculo.getElenco());
                System.out.println("Descrição: " + espetaculo.getDescricao());
                System.out.println("--------------------------");
            }
        }
    }

}
