package aed;

public class Traslado {
    
    private int id ;
    private int origen ;
    private int destino ;
    private int gananciaNeta ;
    private int timestamp ;

    // Constructor.
    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp) {

        this.id = id ;
        this.origen = origen ;
        this.destino = destino ;
        this.gananciaNeta = gananciaNeta ;
        this.timestamp = timestamp ;
        
    } 

    // Constructor por copia.
    public Traslado(Traslado copiado) {

        this.id = copiado.id ;
        this.origen = copiado.origen ;
        this.destino = copiado.destino ;
        this.gananciaNeta = copiado.gananciaNeta ;
        this.timestamp = copiado.timestamp ;
        
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
/*
    public int obtenerIndiceAHeapGanancia() {

        return this.indiceAHeapGanancia ;

    }

    public int obtenerIndexAHeapAntiguedad() {

        return this.indiceAHeapAntiguedad ;

    }
*/
}
