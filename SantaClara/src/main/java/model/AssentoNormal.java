package model;

public class AssentoNormal extends Assentos {
    protected TiposIngressos tipo = TiposIngressos.NORMAL;

    protected String lugares[][] = new String[10][10];

    public AssentoNormal() {

        for (int i = 0; i < this.lugares.length; i++) {
            int l = 1;
            for (int j = 0; j < this.lugares.length; j++) {
                if (j == 0 || j == 9 || i == 9) {
                    this.lugares[i][j] = " X ";
                } else{
                    if (i == 0) {
                        this.lugares[i][j] = "A0" + l;
                    }
                    if (i == 1) {
                        this.lugares[i][j] = "B0" + l;
                    }
                    if (i == 2) {
                        this.lugares[i][j] = "C0" + l;
                    }
                    if (i == 3) {
                        this.lugares[i][j] = "D0" + l;
                    }
                    if (i == 4) {
                        this.lugares[i][j] = "E0" + l;
                    }
                    if (i == 5) {
                        this.lugares[i][j] = "F0" + l;
                    }
                    if (i == 6) {
                        this.lugares[i][j] = "G0" + l;
                    }
                    if (i == 7) {
                        this.lugares[i][j] = "H0" + l;
                    }
                    if (i == 8) {
                        this.lugares[i][j] = "I0" + l;
                    }
                    l++;
                }
            }
        }
    }

    @Override
    public void printLugares() {
        for (int i = 0; i < this.lugares.length; i++) {
            for (int j = 0; j < this.lugares.length; j++) {
                System.out.print(this.lugares[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void imprimir() {
        System.out.println(espetaculo);
        System.out.println(data);
        System.out.println(tipo);
        System.out.println();
        this.printLugares();
        System.out.println();
        System.out.println("X -> ASSENTO INDISPONÍVEL");
    }

    ;

    @Override
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
