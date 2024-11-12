package aed;

public class Ciudad {

    private int nombre ;
    private int gananciaNeta ;
    private int perdidaNeta ;
    private int superavit ;

    // Constructor.
    public Ciudad (int posicion) {

    this.nombre = posicion ;
    this.gananciaNeta = 0 ;
    this.perdidaNeta = 0 ;
    this.superavit = gananciaNeta - perdidaNeta ;

    }

    // Constructor por copia.
    public Ciudad (Ciudad copiado) {

        this.nombre = copiado.nombre ;
        this.gananciaNeta = copiado.gananciaNeta ;
        this.perdidaNeta = copiado.perdidaNeta ;
        this.superavit = copiado.superavit ;
        
    }

    public void aumnetarPerdida (int monto) {

        this.perdidaNeta += monto ;
        this.superavit = gananciaNeta - perdidaNeta ;

    }
    
    public void aumentarGanancia (int monto) {

        this.gananciaNeta += monto ;
        this.superavit = gananciaNeta - perdidaNeta ;

    }

    public int gananciaNeta () {

        return this.gananciaNeta ;

    }

    public int perdidaNeta () {

        return this.perdidaNeta ;
        
    }

    public int superavit() {

        return this.superavit ;

    }

    public int nombre() {

        return this.nombre ;

    }

}
