package operations;

import model.*;
import repositories.EspetaculoRepositorio;
import repositories.IngressosRepositorio;
import repositories.ProgramacaoRepositorio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class OperacoesVendas {

    public static void cadastroDaVenda(Scanner scanner, ProgramacaoRepositorio repositorio, EspetaculoRepositorio espetaculoRepositorio) {
        System.out.println("ID do espetáculo:");
        int espetaculo = scanner.nextInt();

        System.out.println("Data de exibição:");
        String dataExibicao = scanner.next();

        Programacao novaProgramacao = new Programacao(0, java.sql.Date.valueOf(dataExibicao), espetaculo);

        repositorio.inserir(novaProgramacao);

        System.out.println("Programação atualizada com sucesso!");
    }

    public static void comprarIngresso(Scanner scanner, ProgramacaoRepositorio pRepositorio, IngressosRepositorio iRepositorio) {
        AssentoCamarote c = new AssentoCamarote();
        AssentoNormal n = new AssentoNormal();
        boolean outro = true;

        OperacoesProgramacao.exibirProgramacaoMensal(pRepositorio);
        System.out.println("Digite a data do espetáculo que deseja assistir:");
        String dataEspetaculo = scanner.next();
        do {
            System.out.println("Plateia Comum (1) ou Camarote (2)?");
            int tipoAssento = scanner.nextInt();
            switch (tipoAssento) {
                case 1:
                    n.imprimir();
                    System.out.println("Qual assento deseja reservar?");
                    String reserva = scanner.next().toUpperCase();
                    n.reservar(reserva);
                    Pagamento pagamento = OperacoesVendas.modoDePagamento(scanner, TipoAssentos.COMUM);
                    iRepositorio.venderIngresso(new IngressosVendidos(pagamento, java.sql.Date.valueOf(dataEspetaculo), reserva));
                    System.out.println("Reservar outro?");
                    outro = scanner.nextBoolean();
                    break;
                case 2:
                    c.imprimir();
                    System.out.println("Qual assento deseja reservar?");
                    reserva = scanner.next().toUpperCase();
                    c.reservar(reserva);
                    Pagamento pagamentoCa = OperacoesVendas.modoDePagamento(scanner, TipoAssentos.CAMAROTE);
                    iRepositorio.venderIngresso(new IngressosVendidos(pagamentoCa, java.sql.Date.valueOf(dataEspetaculo), reserva));
                    System.out.println("Reservar outro?");
                    outro = scanner.nextBoolean();
                    break;
            }
        } while (outro);
    }

    public static void cancelarCompra(Scanner scanner, IngressosRepositorio repositorio) {
        System.out.println("Qual o dia da programação da compra?");
        String data = scanner.next();
        System.out.println("Qual assento foi reservado?");
        String assento = scanner.next();
        repositorio.cancelarIngresso(assento, java.sql.Date.valueOf(data));
    }

    public static Pagamento modoDePagamento(Scanner scanner, TipoAssentos tipoAssento) {
        System.out.println("Modo de pagamento:");
        System.out.println("1 - Meia; 2 - Social; 3 - Inteira");
        TipoIngressos tipo;
        BigDecimal preco;
        int pagamento = scanner.nextInt();

        switch (pagamento) {
            case 1:
                preco = valores(tipoAssento).divide(BigDecimal.valueOf(2));
                tipo = TipoIngressos.MEIA;
                break;
            case 2:
                preco = valores(tipoAssento).divide(BigDecimal.valueOf(2));
                tipo = TipoIngressos.SOCIAL;
                break;
            default:
                preco = valores(tipoAssento);
                tipo = TipoIngressos.INTEIRA;
                break;
        }

        return new Pagamento(tipo, preco);
    }

    public static BigDecimal valores(TipoAssentos tipoAssento) {
        if (tipoAssento == TipoAssentos.CAMAROTE) {
            return new BigDecimal("160.00");
        } else {
            return new BigDecimal("70.00");
        }
    }

}
