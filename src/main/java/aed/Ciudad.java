package aed;

public class Ciudad<T> {
<<<<<<< HEAD
    int nombre;
    int gananciaNeta;
    int perdidaNeta;
    int superavit;


    public Ciudad(int posicion){
    this.nombre=posicion;
    this.gananciaNeta=0;
    this.perdidaNeta=0;
    this.superavit=gananciaNeta-perdidaNeta;
}

   //Capaz hay un nombre para estos metodos que los haga menos confuso?
    public void compro(int gasto){
        this.perdidaNeta += gasto;
        this.superavit = gananciaNeta - perdidaNeta;
    }
    
    public void vendio(int ganancia){
        this.gananciaNeta += ganancia;
        this.superavit = gananciaNeta - perdidaNeta;
    }

    public int gananciaNeta() {
        return this.gananciaNeta;
    }
    public int superavit(){
        return this.superavit;
=======

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

    // Constructor por copia.
    public Ciudad (Ciudad copiado) {

        this.nombre = copiado.nombre ;
        this.gananciaNeta = copiado.gananciaNeta ;
        this.perdidaNeta = copiado.perdidaNeta ;
        this.superavit = copiado.superavit ;
        
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

>>>>>>> main
    }

}
