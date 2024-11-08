package aed;

public class Ciudad<T> {
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
    }

    public int nombre() { return this.nombre; }

}
