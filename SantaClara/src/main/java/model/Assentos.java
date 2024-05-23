package model;

import java.util.Scanner;


public class Assentos {
    Scanner sc = new Scanner(System.in);
    protected String lugares[][] = new String[10][10];
    protected String data;
    protected String espetaculo;
    protected TiposIngressos tipo;

    public void printLugares() {
        for (int i = 0; i < this.lugares.length; i++) {
            for (int j = 0; j < this.lugares.length; j++) {
                System.out.print(this.lugares[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void imprimir() {
        System.out.println(espetaculo);
        System.out.println(data);
        System.out.println(tipo);
        this.printLugares();
        System.out.println();
        System.out.println("X -> ASSENTO INDISPONÍVEL");
    }

    ;

    public void reservar(String r) {
        for (int i = 0; i < lugares.length; i++) {
            for (int j = 0; j < lugares.length; j++) {
                if (r.equalsIgnoreCase(this.lugares[i][j])) {
                    System.out.println("Deseja reservar " + this.lugares[i][j] + "? Sim(1) / Não (0)");
                    int confirma = sc.nextInt();
                    if (confirma == 1) {
                        this.lugares[i][j] = "X ";
                        this.printLugares();
                    }
                    return;
                } else {
                    System.out.println("Assento indisponível, por favor escolha outro");
                    return;
                }
            }
        }
    }

    ;
}
