package operations;

import com.microsoft.sqlserver.jdbc.StringUtils;
import model.TipoAssentos;
import model.IngressoVendido;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
//import org.apache.commons.lang3.StringUtils;


public class OperacoesIngresso {


    public static String dataExtenso(Date d) {
        Locale local = new Locale("pt", "BR");
        DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
        String data = formato.format(d);
        return data;
    }


    public static void imprimirIngressos(IngressoVendido i, TipoAssentos tAssento) {
        String data = dataExtenso(i.getDataExibicao());
        //nome do teatro, nome do espetáculo, data, hora, tipo do ingresso, valor pago e assento
        System.out.println("-------------------------------------");
        System.out.println("Teatro Santa Clara");
        System.out.println("Espetáculo tal meu cu essa buceta");
        System.out.println(data + " | 18h");
        //ver como capitalize
        System.out.println(i.getTipoIngresso()+ " R$" + i.getValor());
        System.out.println("ASSENTO " + tAssento + ": " + i.getAssentoId());
        System.out.println("-------------------------------------");


    }
}
