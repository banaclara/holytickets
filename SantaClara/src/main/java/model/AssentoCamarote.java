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
    public String[][] getLugares() {
        return lugares;
    }
}
