package operations;

import model.Programacao;
import repositories.EspetaculoRepositorio;
import repositories.ProgramacaoRepositorio;

import java.util.List;
import java.util.Scanner;

public class OperacoesProgramacao {

    public static void inserirProgramacao(Scanner scanner, ProgramacaoRepositorio repositorio, EspetaculoRepositorio espetaculoRepositorio) {
        System.out.println("Registrar espetáculo em uma data:");

        OperacoesEspetaculo.listarEspetaculos(espetaculoRepositorio);
        System.out.println("ID do espetáculo:");
        int espetaculo = scanner.nextInt();

        System.out.println("Data de exibição:");
        String dataExibicao = scanner.next();

        Programacao novaProgramacao = new Programacao(0, java.sql.Date.valueOf(dataExibicao), espetaculo);

        repositorio.inserir(novaProgramacao);

        System.out.println("Programação atualizada com sucesso!");
    }

    public static void pesquisarProgramacaoMensal(Scanner scanner, ProgramacaoRepositorio repositorio) {
        System.out.println("Pesquisar programação por mês:");
        System.out.println("Digite o mês:"); // 01 janeiro, 02 fevereiro ... 12 dezembro
        int mes = scanner.nextInt();
        System.out.println("E o ano:");
        int ano = scanner.nextInt();
        List<Programacao> progDoMes = repositorio.programacaoMensal(mes, ano);
        if (progDoMes.isEmpty()) {
            System.out.println("Nada programado para esse mês.");
        } else {
            System.out.println("Programação mensal " + mes + "/" + ano + ":");
            String tituloAtual = ""; // para o controle de exibição do título do espetáculo
            for (Programacao programacao : progDoMes) {
                if (!programacao.getTituloEspetaculo().equals(tituloAtual)) { // faz com que o título só apareça se for diferente do que foi exibido por último e a programação seja organizada semanalmente já que cada espetáculo fica em cartaz por pelo menos 1 final de semana
                    tituloAtual = programacao.getTituloEspetaculo(); // atualiza o título atual
                    System.out.println("--------------------------");
                    System.out.println("Espetáculo: " + tituloAtual);
                    System.out.println("Datas em exibição:");
                }
                System.out.println(programacao.getDataExibicao());
            }
            System.out.println("--------------------------");
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
