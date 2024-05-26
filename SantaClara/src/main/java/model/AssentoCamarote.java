package model;

public class AssentoCamarote extends Assentos {
    protected TipoAssentos tipo = TipoAssentos.CAMAROTE;
    protected String lugares[][] = new String[10][10];

    public AssentoCamarote() {
        int l = 1;
        for (int i = 0; i < this.lugares.length; i++) {
            for (int j = 0; j < this.lugares.length; j++) {
                if (j == 0 || j == 9 || i == 9) {
                    if (l < 10) {
                        this.lugares[i][j] = "CA0" + l;
                    } else {
                        this.lugares[i][j] = "CA" + l;
                    }
                    l++;
                } else {
                    this.lugares[i][j] = "  X ";
                }
            }
        }
    }
    @Override
    public void printLugares() {
        for (int i  = 0; i < this.lugares.length; i++) {
            for (int j  = 0; j < this.lugares.length; j++){
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
    };
    @Override
    public void reservar(String r){
        Boolean disponivel = false;
        for (int i = 0; i < lugares.length; i++) {
            for (int j = 0; j < lugares.length; j++) {
                if (r.equalsIgnoreCase(this.lugares[i][j])) {
                        disponivel = true;
                        this.lugares[i][j] = "X ";

                }
            }
        }
        if (!disponivel) {
            System.out.println("Assento indisponível, por favor escolha outro");
        }
    };

}
