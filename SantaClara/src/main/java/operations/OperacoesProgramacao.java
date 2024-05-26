package operations;

import model.Programacao;
import repositories.EspetaculoRepositorio;
import repositories.ProgramacaoRepositorio;

import java.sql.Date;
import java.time.LocalDate;
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

    public static void exibirProgramacaoMensal(ProgramacaoRepositorio repositorio) {
        LocalDate dataAtual = LocalDate.now();
        int mes = dataAtual.getMonthValue();
        int ano = dataAtual.getYear();
        List<Programacao> progDoMes = repositorio.programacaoMensal(mes, ano);
        if (progDoMes.isEmpty()) {
            System.out.println("Nada programado para esse mês.");
        } else {
            System.out.println("Programação do mês atual:");
            String tituloAtual = "";
            for (Programacao programacao : progDoMes) {
                if (!programacao.getTituloEspetaculo().equals(tituloAtual)) {
                    tituloAtual = programacao.getTituloEspetaculo();
                    System.out.println("--------------------------");
                    System.out.println("Espetáculo: " + tituloAtual);
                    System.out.println("Datas em exibição:");
                }
                System.out.println(programacao.getDataExibicao());
            }
            System.out.println("--------------------------");
        }
    }

    public static void alterarProgramacao(Scanner scanner, ProgramacaoRepositorio repositorio, EspetaculoRepositorio espetaculoRepositorio) {
        System.out.println("Atualizar programação:");
        List<Programacao> progGeral = repositorio.buscarTodos();
        for (Programacao programacao : progGeral) {
            System.out.println("Espetáculo: " + programacao.getTituloEspetaculo() + ", Data de exibição: " + programacao.getDataExibicao() + " (ID: " + programacao.getId() + ")");
        }
        System.out.println("Digite o ID da programação que deseja atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Selecione o campo que deseja alterar:");
        System.out.println("1. Espetáculo");
        System.out.println("2. Data de exibição");
        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1:
                OperacoesEspetaculo.listarEspetaculos(espetaculoRepositorio);
                System.out.println("Digite o id do espetáculo que deseja cadastrar na data indicada anteriormente:");
                int novoEspetaculo = scanner.nextInt();
                repositorio.alterarEspetaculo(id, novoEspetaculo);
                break;
            case 2:
                System.out.println("Digite a nova data de exibição para o espetáculo indicado anteriormente:");
                String novaData = scanner.next();
                repositorio.alterarData(id, java.sql.Date.valueOf(novaData));
                break;
        }
    }

    public static void excluirProgramacao(Scanner scanner, ProgramacaoRepositorio repositorio) {
        System.out.println("Cancelar exibição na data:");
        List<Programacao> progGeral = repositorio.buscarTodos();
        for (Programacao programacao : progGeral) {
            System.out.println("Espetáculo: " + programacao.getTituloEspetaculo() + ", Data de exibição: " + programacao.getDataExibicao() + " (ID: " + programacao.getId() + ")");
        }

        System.out.println("Digite o ID da programação que deseja cancelar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        repositorio.excluir(id);

        System.out.println("Programação cancelada com sucesso!");
    }
}
