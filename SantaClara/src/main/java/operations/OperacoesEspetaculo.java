package operations;

import model.Espetaculo;
import repositories.EspetaculoRepositorio;

import java.util.List;
import java.util.Scanner;

public class OperacoesEspetaculo {
    //Inserção de uma nova tabela no BD
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

        // Pega todos as variaveis que foram captadas e passa como parametro
        Espetaculo novoEspetaculo = new Espetaculo(0, titulo, diretor, elenco, descricao);

        repositorio.inserir(novoEspetaculo);

        System.out.println("Espetáculo inserido com sucesso!");
    }

    //Método para excluir um espetaculo dentro do BD
    public static void excluirEspetaculo(Scanner scanner, EspetaculoRepositorio repositorio) {
        System.out.println("Excluir espetáculo:");

        // Listar todos os espetáculos
        List<Espetaculo> todosEspetaculos = repositorio.buscarTodos();
        System.out.println("Espetáculos disponíveis para exclusão:");
        for (Espetaculo espetaculo : todosEspetaculos) {
            //Exibindo os espetaculos por ID e Titulo, mas pode colocar os outros parametros
            System.out.println("ID: " + espetaculo.getId() + ", Título: " + espetaculo.getTitulo());
        }

        //Exclusão a partir do ID
        System.out.println("Digite o ID do espetáculo que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Excluir do banco de dados
        repositorio.excluir(id);

        System.out.println("Espetáculo excluído com sucesso!");
    }
    //Método para alteração de um espetaculo dentro do BD
    public static void alterarEspetaculo(Scanner scanner, EspetaculoRepositorio repositorio) {
        System.out.println("Alterar espetáculo:");

        // Lista todos os espetáculos
        List<Espetaculo> todosEspetaculos = repositorio.buscarTodos();
        System.out.println("Espetáculos disponíveis para alteração:");
        for (Espetaculo espetaculo : todosEspetaculos) {
            System.out.println("ID: " + espetaculo.getId() + ", Título: " + espetaculo.getTitulo());
        }
        //Alteração a partir do ID
        System.out.println("Digite o ID do espetáculo que deseja alterar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        //Menu para selecionar qual coluna vai ser alterada
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
                // Capta o novo titulo e atribui ao id correspondente
                System.out.println("Novo título:");
                novoValor = scanner.nextLine();
                repositorio.alterarTitulo(id, novoValor);
                break;
            case 2:
                // Capta o novo diretor e atribui ao id correspondente
                System.out.println("Novo diretor:");
                novoValor = scanner.nextLine();
                repositorio.alterarDiretor(id, novoValor);
                break;
            case 3:
                // Capta o novo elenco e atribui ao id correspondente
                System.out.println("Novo elenco:");
                novoValor = scanner.nextLine();
                repositorio.alterarElenco(id, novoValor);
                break;
            case 4:
                // Capta a nova descrição e atribui ao id correspondente
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
    // Método para listar os espetaculos criados no BD
    public static void listarEspetaculos(EspetaculoRepositorio repositorio) {
        System.out.println("Lista de Espetáculos:");
        // Lista todos os espetáculos
        List<Espetaculo> todosEspetaculos = repositorio.buscarTodos();
        if (todosEspetaculos.isEmpty()) {
            System.out.println("Não há espetáculos cadastrados.");
        } else {
            for (Espetaculo espetaculo : todosEspetaculos) {
                System.out.println("ID: " + espetaculo.getId() + ", Título: " + espetaculo.getTitulo());
            }
        }
    }
    // Método de pesquisa entre os espetaculos existentes no BD por nome
    public static void pesquisarEspetaculoPorNome(Scanner scanner, EspetaculoRepositorio repositorio) {
        System.out.println("Pesquisar espetáculo por nome:");
        System.out.println("Digite o nome do espetáculo:");
        String nome = scanner.nextLine();
        // Pega o metodo criado no repositorio
        List<Espetaculo> espetaculosEncontrados = repositorio.buscarPorNome(nome);
        // Cria uma exceção caso o nome do espetaculo não esteja presente no BD
        if (espetaculosEncontrados.isEmpty()) {
            System.out.println("Nenhum espetáculo encontrado com o nome informado.");
        } else {
            // Apresenta as informações completas do espetaculo
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