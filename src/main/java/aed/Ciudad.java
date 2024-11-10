package aed;

public class Ciudad<T> {

    // Atributos privados de la clase.
    private int nombre ;
    private int gananciaNeta ;
    private int perdidaNeta ;
    private int superavit ;

    // Constructor.
    public Ciudad (int nombre_index) {

        this.nombre = nombre_index ;
        this.gananciaNeta = 0 ;
        this.perdidaNeta = 0 ;
        this.superavit = gananciaNeta - perdidaNeta ;

    }

    public void aumentarPerdida (int monto) {

        this.perdidaNeta += monto ;
        this.superavit = gananciaNeta - perdidaNeta ;

    }
    
    public void aumentarGanancia (int monto) {

        this.gananciaNeta += monto ;
        this.superavit = gananciaNeta - perdidaNeta ;

    }

    public int obtenerGananciaNeta() {

        return this.gananciaNeta ;

    }

    public int obtenerSuperavit() {

        return this.superavit ;

    }

}
