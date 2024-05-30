package operations;

import model.*;
import repositories.EspetaculoRepositorio;
import repositories.IngressosRepositorio;
import repositories.ProgramacaoRepositorio;

import java.math.BigDecimal;
import java.util.Scanner;

public class OperacoesVendas {

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
                    OperacoesAssentos.imprimir(n, java.sql.Date.valueOf(dataEspetaculo), TipoAssentos.COMUM, iRepositorio);
                    System.out.println("Qual assento deseja reservar?");
                    String reserva = scanner.next().toUpperCase();
                    Pagamento pagamento = OperacoesVendas.modoDePagamento(scanner, TipoAssentos.COMUM);
                    IngressoVendido ingressoComum = new IngressoVendido(pagamento, java.sql.Date.valueOf(dataEspetaculo), reserva);
                    iRepositorio.venderIngresso(ingressoComum);
                    OperacoesIngresso.imprimirIngressos(iRepositorio, ingressoComum, TipoAssentos.COMUM);
                    //imprimir ingresso aqui
                    System.out.println("Reservar outro? (true/false)");
                    outro = scanner.nextBoolean();
                    break;
                case 2:
                    OperacoesAssentos.imprimir(c, java.sql.Date.valueOf(dataEspetaculo), TipoAssentos.CAMAROTE, iRepositorio);
                    System.out.println("Qual assento deseja reservar?");
                    reserva = scanner.next().toUpperCase();
                    Pagamento pagamentoCa = OperacoesVendas.modoDePagamento(scanner, TipoAssentos.CAMAROTE);
                    IngressoVendido ingressoCamarote = new IngressoVendido(pagamentoCa, java.sql.Date.valueOf(dataEspetaculo), reserva);
                    iRepositorio.venderIngresso(ingressoCamarote);
                    OperacoesIngresso.imprimirIngressos(iRepositorio, ingressoCamarote, TipoAssentos.CAMAROTE);
                    //imprimir ingresso aqui
                    System.out.println("Reservar outro? (true/false)");
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
