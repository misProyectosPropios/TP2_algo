package aed;

import java.util.Comparator;

public class Traslado {
    
    private int id ;
    private int origen ;
    private int destino ;
    private int gananciaNeta ;
    private int timestamp ;
    private int indiceAHeapGanancia ;
    private int indiceAHeapAntiguedad ;

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

    public int obtenerId() {

        return this.id ;
        
    }

    public int obtenerCiudadOrigen() {

        return this.origen ;

    }

    public int obtenerCiudadDestino() {

        return this.destino ;

    }

    public int obtenerGananciaNeta() { 
        
        return this.gananciaNeta ;

    }

    public int obtenerTimestamp() {
        
        return this.timestamp ; 
    
    }

    public int obtenerIndiceAHeapGanancia() {

        return this.indiceAHeapGanancia ;

    }

    public int obtenerIndexAHeapAntiguedad() {

        return this.indiceAHeapAntiguedad ;

    }

}
