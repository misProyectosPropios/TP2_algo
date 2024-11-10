package aed;

import java.util.Comparator;

public class Traslado {
    
    int id ;
    int origen ;
    int destino ;
    int gananciaNeta ;
    int timestamp ;
    int indiceAHeapGanancia ;
    int indiceAHeapAntiguedad ;

    // Constructor.
    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp, int indexAHeapGanancia, int indexAHeapAntiguedad) {

        this.id = id ;
        this.origen = origen ;
        this.destino = destino ;
        this.gananciaNeta = gananciaNeta ;
        this.timestamp = timestamp ;
        this.indiceAHeapGanancia = indexAHeapGanancia ;
        this.indiceAHeapAntiguedad = indexAHeapAntiguedad ;
        
    } 

    public int gananciaNeta() { return this.gananciaNeta;}

    public int timestamp() {return this.timestamp; }
}
