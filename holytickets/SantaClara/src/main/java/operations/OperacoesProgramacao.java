package operations;

import model.Programacao;
import repositories.ProgramacaoRepositorio;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class OperacoesProgramacao {

    public static void inserirProg(Scanner scanner, ProgramacaoRepositorio repositorio) {
        System.out.println("Registrar espetáculo em uma data:");

        System.out.println("Espetáculo:");
        int espetaculo = scanner.nextInt();

        System.out.println("Data de exibição:");
        String dataExibicao = scanner.next();

        Programacao novaProgramacao = new Programacao(0, java.sql.Date.valueOf(dataExibicao), espetaculo);

        repositorio.inserir(novaProgramacao);

        System.out.println("Programação atualizada com sucesso!");
    }

    public static void pesquisarProgMensal(Scanner scanner, ProgramacaoRepositorio repositorio) {
        System.out.println("Pesquisar programação por mês:");
        System.out.println("Digite o mês:"); // 01 janeiro, 02 fevereiro ... 12 dezembro
        int mes = scanner.nextInt();
        // Pega o metodo criado no repositorio
        List<Programacao> progDoMes = repositorio.progMensal(mes);
        // Cria uma exceção caso o nome do espetaculo não esteja presente no BD
        if (progDoMes.isEmpty()) {
            System.out.println("Nenhum espetáculo encontrado com o nome informado.");
        } else {
            // Apresenta as informações completas do espetaculo
            System.out.println("Espetáculo(s) encontrado(s):");
            for (Programacao programacao : progDoMes) {
                System.out.println("ID: " + programacao.getId());
                System.out.println("Título: " + programacao.getDataExibicao());
                System.out.println("Diretor: " + programacao.getEspetaculoID());
                System.out.println("--------------------------");
            }
        }
    }

    public static void excluirProg(Scanner scanner, ProgramacaoRepositorio repositorio) {
        System.out.println("Excluir espetáculo:");

        // Listar todos os espetáculos
        List<Programacao> progGeral = repositorio.buscarTodos();
        System.out.println("Espetáculos disponíveis para exclusão:");
        for (Programacao programacao : progGeral) {
            //Exibindo os espetaculos por ID e Titulo, mas pode colocar os outros parametros
            System.out.println("ID: " + programacao.getId() + ", Título: " + programacao.getEspetaculoID() + ", Data: " + programacao.getDataExibicao());
        }

        //Exclusão a partir do ID
        System.out.println("Digite o ID da programação que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Excluir do banco de dados
        repositorio.excluir(id);

        System.out.println("Programação cancelada com sucesso!");
    }
}
