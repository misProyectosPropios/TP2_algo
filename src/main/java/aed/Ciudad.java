package aed;
import java.lang.reflect.Array;
import java.util.ArrayList;



public class Ciudad<T>{
    int nombre;
    int gananciaNeta;
    int perdidaNeta;
    int superavit=gananciaNeta-perdidaNeta;


    public Ciudad(int posicion){
    this.nombre=posicion;
    this.gananciaNeta=0;
    this.perdidaNeta=0;}

   //Capaz hay un nombre para estos metodos que los haga menos confuso?
    public void compro(int gasto){
        this.perdidaNeta+=gasto;}
    
    public void vendio(int ganancia){
        this.gananciaNeta+=ganancia;
    }

}
