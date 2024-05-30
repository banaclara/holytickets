package operations;

import model.TipoAssentos;
import model.IngressoVendido;
import repositories.IngressosRepositorio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class OperacoesIngresso {


    public static String dataExtenso(Date d) {
        Locale local = new Locale("pt", "BR");
        DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
        String data = formato.format(d);
        return data;
    }


    public static void imprimirIngressos(IngressosRepositorio repo, IngressoVendido i, TipoAssentos tAssento) {
        IngressoVendido ing = repo.dadosDoIngresso(i.getDataExibicao());
        String data = dataExtenso(i.getDataExibicao());
        String tipo = i.getTipoIngresso().substring(0, 1).toUpperCase() + i.getTipoIngresso().substring(1);

        //nome do teatro, nome do espetáculo, data e hora, tipo do ingresso e valor pago, tipo de assento e assento
        System.out.println("-------------------------------------");
        System.out.println("Teatro Santa Clara");
        System.out.println(ing.getTituloEspetaculo());
        System.out.println(data + " | 18h");
        System.out.println(tipo + " | R$" + i.getValor());
        System.out.println("ASSENTO " + tAssento + ": " + ing.getAssentoId());
        System.out.println("-------------------------------------");


    }
}
