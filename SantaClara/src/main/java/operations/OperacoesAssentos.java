package operations;
import model.Assentos;
import model.TipoAssentos;
import repositories.IngressosRepositorio;

import java.sql.*;

import java.util.List;

public class OperacoesAssentos {

    public static void imprimir(Assentos assentos, Date data, TipoAssentos tipo, IngressosRepositorio repositorio) {
        System.out.println(data);
        System.out.println(tipo);
        System.out.println();
        printLugares(assentos, repositorio, data, tipo);
        System.out.println();
        System.out.println("X -> ASSENTO INDISPONÍVEL");
    }

    private static void printLugares(Assentos assentos, IngressosRepositorio repositorio, Date data, TipoAssentos tipo) {
        OperacoesAssentos.estadoAssento(repositorio, data, assentos, tipo);

        for (int i = 0; i < assentos.getLugares().length; i++) {
            for (int j = 0; j < assentos.getLugares()[i].length; j++) {
                System.out.print(assentos.getLugares()[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void estadoAssento(IngressosRepositorio repositorio, Date data, Assentos assentos, TipoAssentos tipo) {
        List<String> assentosVendidos = repositorio.consultarAssentosVendidos(data);

        for (int i = 0; i < assentos.getLugares().length; i++) {
            for (int j = 0; j < assentos.getLugares()[i].length; j++) {
                String assentoId = assentos.getLugares()[i][j];
                if (assentosVendidos.contains(assentoId)) {
                    if (tipo == TipoAssentos.COMUM) {
                        assentos.getLugares()[i][j] = " X ";
                    } else {
                        assentos.getLugares()[i][j] = "  X ";
                    }
                }
            }
        }
    }

}