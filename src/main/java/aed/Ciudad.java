package aed;

public class Ciudad {

    private int nombre ;
    private int gananciaNeta ;
    private int perdidaNeta ;
    private int superavit ;
    private int posicionEnHeap;

    // Constructor.
    public Ciudad (int posicion) {        

    //Inicializar todos estos variables es O(1) para cada variable
    this.nombre = posicion ;
    this.gananciaNeta = 0 ;
    this.perdidaNeta = 0 ;
    this.superavit = gananciaNeta - perdidaNeta ;
    this.posicionEnHeap = posicion;
    }

    // Constructor por copia.
    public Ciudad (Ciudad copiado) {                                    //Inicializar todos estos variables es O(1) para cada variable
        this.nombre = copiado.nombre ;
        this.gananciaNeta = copiado.gananciaNeta ;
        this.perdidaNeta = copiado.perdidaNeta ;
        this.superavit = copiado.superavit ;
        
    }

    public void aumnetarPerdida (int monto) {

        this.perdidaNeta += monto ;                                     //O(1)
        this.superavit = gananciaNeta - perdidaNeta ;                   //O(1)

    }
    
    public void aumentarGanancia (int monto) {

        this.gananciaNeta += monto ;//O(1)
        this.superavit = gananciaNeta - perdidaNeta ;                   //O(1)

    }

    public int gananciaNeta () {

        return this.gananciaNeta ;                                      //O(1)

    }

    public int perdidaNeta () {

        return this.perdidaNeta ;                                       //O(1)
        
    }

    public int superavit() {

        return this.superavit ;                                         //O(1)

    }

    public int nombre() {

        return this.nombre ;                                            //O(1)

    }

    public int getPosicionEnHeap() {
        return this.posicionEnHeap;                                     //O(1)
    }

    public void setPosicionEnHeap(int posicion) {
        this.posicionEnHeap = posicion;                                 //O(1)
    }

}
